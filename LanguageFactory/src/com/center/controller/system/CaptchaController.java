
package com.center.controller.system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.sf.json.JSONObject;

/**
* ClassName: CaptchaController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年7月1日 下午8:29:13 <br/>
*
* @author Tony
* @version 
*/

@Controller
public class CaptchaController {
	private Producer kaptchaProducer=null;
	 
    @Autowired
    public void setCaptchaProducer(Producer kaptchaProducer) {
        this.kaptchaProducer = kaptchaProducer;
    }
 
    @RequestMapping("/kaptcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
    	int width = 80;
        int height = 40;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, strCode);
        
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
        
        return null;
    }
    
    //创建颜色
    Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
    
    @RequestMapping("/kaptcha2")
	public ModelAndView handleRequest2(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = kaptchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        //ImageIO.write(bi, "jpg", out);
        
        // 将图像输出到客户端  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());  
        encoder.encode(bi); 
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
    
    @ResponseBody
	@RequestMapping(value="/kaptchaValid",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String kaptchaValid(@ModelAttribute("verifyCodeIn") String verifyCodeIn,HttpServletRequest request) throws Exception {
    	boolean result = false;
    	HashMap<String,Object> rsMap = new HashMap<String,Object>();
    	String verifyCode = null;
    	if(null != request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)){
    		verifyCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
    	};
		if(verifyCode != null && verifyCode.equals(verifyCodeIn.toUpperCase())){
			result = true;
		}
		rsMap.put("status", result);
		String data = JSONObject.fromObject(rsMap).toString();
		return data;
	}

}

