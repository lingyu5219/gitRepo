package com.center.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.center.po.common.UploadFile;
import com.center.utils.UuidUtil;

import net.sf.json.JSONObject;

/**
 * 
* ClassName: UploadController <br/>
* Function: TODO ADD FUNCTION. <br/>
* date: 2017年5月9日 下午2:34:04 <br/>
*
* @author Tony
* @version
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	@ResponseBody
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addProject(@ModelAttribute("upFile") UploadFile upFile, @RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ServletContext sContext = session.getServletContext();
		//获取当前登录用户
		//User loginUser = (User)session.getAttribute("user");
		//获取当前上下文的绝对路径
		String root = sContext.getRealPath("/");
		//当前日期 
		Date date = new Date(); 
		//生成的文件存储路径
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		upFile.setFileType(fileExt);
		//生成文件存储的路径，使用年月日进行文件夹命名
		String uploadPath = upFile.getUploadPath().replace("/", "\\") + File.separator + new SimpleDateFormat("yyyyMMdd").format(date) + File.separator;
		//创建文件夹 
		File f = new File(root + uploadPath); 
		if(!f.exists()){
			f.mkdirs();
		}
        if(null != file) {
        	String newFileName = UuidUtil.getUUID() + fileExt;
            //上传
//        	String relativePath = uploadPath + loginUser.getUserId() + "_" + System.currentTimeMillis() + fileExt;
//        	String relativePath = uploadPath + file.getOriginalFilename();
        	String relativePath = uploadPath + newFileName;
            File nfile = new File(root + relativePath);
            file.transferTo(nfile);
            
            upFile.setFileName(file.getOriginalFilename());
//          upFile.setFileName(newFileName);
            upFile.setFileSize(file.getSize());
            upFile.setFileRelativePath(relativePath);
            
            String data = JSONObject.fromObject(upFile).toString();
    		return data;
        }
        
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/delFile",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delFile(@ModelAttribute("upFile") UploadFile upFile,HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		File file = new File(root + upFile.getFileRelativePath());
		if(file.delete()){
			rsMap.put("status", "1");
			rsMap.put("info", "删除成功");
		} else {
			rsMap.put("status", "2");
			rsMap.put("info", "删除失败");
		}
		
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/getFile",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getFile(@RequestParam String path,HttpServletRequest request) throws Exception {
		HashMap<String,Object> rsMap = new HashMap<String,Object>();
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		File file = new File(root + path);
		if(file.exists() && file.isFile()){
			rsMap.put("status", "1");
			rsMap.put("info", "文件存在");
			rsMap.put("fileName", file.getName());
			rsMap.put("fileSize", file.length());
		} else {
			rsMap.put("status", "2");
			rsMap.put("info", "文件不存在");
		}
		
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/downloadFile",method=RequestMethod.POST)
	public ResponseEntity<byte[]> downloadFile(@RequestParam String fileRelativePath,HttpServletRequest request) throws Exception {
		ServletContext sContext = request.getSession().getServletContext();
		String root = sContext.getRealPath("/");
		File file = new File(root + fileRelativePath);
		if(!file.exists() || file.isDirectory()){
			return null;
		}
		
	    InputStream is = new FileInputStream(file);
	    byte[] body = new byte[is.available()];
	    is.read(body);
	    is.close();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("gb2312"), "iso8859-1"));
	    //headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	}
}
