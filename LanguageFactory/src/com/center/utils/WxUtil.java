package com.center.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.center.po.wx.MyX509TrustManager;
import com.center.po.wx.WxXml;

import net.sf.json.JSONObject;

@Component
public class WxUtil {
	private static Logger log = LoggerFactory.getLogger(WxUtil.class);
	
	private static String token;
	
	private static String appID;
	
	private static String appsecret;

	/**
	 * 存储公众号的全局唯一接口调用凭据access_token
	 */
	private static String accessToken;
	
	/**
	 * 存储获取access_token的时间
	 */
	private static long accessTokenTime;
	
	/**
	 * access_token的有效时间，单位：秒
	 */
	private static long effectiveAccessTokenTime;
	
	/**
	 * 消息类型
	 */
	public static final String MSGTYPE_TEXT = "text";//文本
	public static final String MSGTYPE_IMAGE = "image";//图片
	public static final String MSGTYPE_VOICE = "voice";//音频
	public static final String MSGTYPE_VIDEO = "video";//视频
	public static final String MSGTYPE_SHORTVIDEO = "shortvideo";//小视频
	public static final String MSGTYPE_LOCATION = "location";//地理位置消息
	public static final String MSGTYPE_LINK = "link";//链接消息
	public static final String MSGTYPE_EVENT = "event";//事件推送
	
	/**
	 * 事件类型
	 */
	public static final String EVENTTYPE_SUBSCRIBE = "subscribe";//关注
	public static final String EVENTTYPE_UNSUBSCRIBE = "unsubscribe";//取消关注
	public static final String EVENTTYPE_LOCATION = "LOCATION";//上报地理位置事件
	public static final String EVENTTYPE_CLICK = "CLICK";//点击菜单
	public static final String EVENTTYPE_SCAN = "SCAN";//扫描二维码事件
	
	//用户列表
	private static List<HashMap<String,String>> userList = new ArrayList<HashMap<String,String>>();
	
	
	public static List<HashMap<String, String>> getUserList() {
	
		return userList;
	}

	/**
	 * 
	* getUserMap:(根据openID获取用户). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @param OpenID
	* @return
	 */
	public static HashMap<String,String> getUserMap(String OpenID){
		HashMap<String,String> rsUserMap = null;
		for(HashMap<String,String> userMap : userList){
			if(OpenID.equals(userMap.get("OpenID"))){
				rsUserMap = userMap;
				break;
			}
		}
		return rsUserMap;
	}
	
	
	public static String getToken() {
		return token;
	}
	
	@Value("#{prop['wx.token']}")
	public void setToken(String token) {
		WxUtil.token = token;
	}

	public static String getAppID() {
		return appID;
	}

	@Value("#{prop['wx.appID']}")
	public void setAppID(String appID) {
		WxUtil.appID = appID;
	}

	public static String getAppsecret() {
		return appsecret;
	}

	@Value("#{prop['wx.appsecret']}")
	public void setAppsecret(String appsecret) {
		WxUtil.appsecret = appsecret;
	}

	/**
	 * 
	* getAccessToken:(检查当前系统时间与获取access_token时的时间，如果超过有效时间，则重新获取access_token). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @return
	 */
	public static String getAccessToken() {
		Date now = new Date();
		if(((now.getTime() - WxUtil.accessTokenTime) / 1000) > WxUtil.effectiveAccessTokenTime){
			WxUtil.refreshAccessTokenFromWx();
		}
		
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		WxUtil.accessToken = accessToken;
	}

	public static long getAccessTokenTime() {
		return accessTokenTime;
	}

	public static void setAccessTokenTime(long accessTokenTime) {
		WxUtil.accessTokenTime = accessTokenTime;
	}
	
	public static long getEffectiveAccessTokenTime() {
		return effectiveAccessTokenTime;
	}

	public static void setEffectiveAccessTokenTime(long effectiveAccessTokenTime) {
		WxUtil.effectiveAccessTokenTime = effectiveAccessTokenTime;
	}

	/**
	 * 微信平台接入，开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
		1）将token、timestamp、nonce三个参数进行字典序排序
		2）将三个参数字符串拼接成一个字符串进行sha1加密
		3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		List<String> list = new ArrayList<String>();
		list.add(WxUtil.token);
		list.add(timestamp);
		list.add(nonce);
		
		Collections.sort(list);
		
		StringBuffer buffer = new StringBuffer();
		for(String str : list){
			buffer.append(str);
		}
		
		String sha1Str = DigestUtils.sha1Hex(buffer.toString());
		
		return sha1Str != null ? sha1Str.equals(signature) : false;
	}
	
	/**
	 * 
	* refreshAccessTokenFromWx:(调用微信平台access_token接口，刷新access_token). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>
	* TODO(这里描述这个方法的执行流程 – 可选).<br/>
	* TODO(这里描述这个方法的使用方法 – 可选).<br/>
	* TODO(这里描述这个方法的注意事项 – 可选).<br/>
	*
	* @author Tony
	* @return
	 */
	public static boolean refreshAccessTokenFromWx(){
		
		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appsecret;
		
		JSONObject jsonObject = httpRequest(accessTokenUrl, "GET", null);
		// 如果请求成功  
	    if (null != jsonObject && jsonObject.getString("access_token") != null) {
	    	WxUtil.accessToken = jsonObject.getString("access_token");
	    	WxUtil.effectiveAccessTokenTime = jsonObject.getLong("expires_in");
	    	//记录当前获取access_token的时间
	    	Date now = new Date();
			WxUtil.accessTokenTime = now.getTime();
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	
	/** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }
    
    public static WxXml getWxXml(String strXml){
		WxXml msg = null;
		try {
			if (strXml.length() <= 0 || strXml == null)
				return null;
			 
			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(strXml);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 遍历根节点下所有子节点
			Iterator<?> iter = root.elementIterator();
			
			// 遍历所有结点
			msg = new WxXml();
			//利用反射机制，调用set方法
			//获取该实体的元类型
			Class<?> c = Class.forName("com.center.po.wx.WxXml");
			msg = (WxXml)c.newInstance();//创建这个实体的对象
			
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				//获取set方法中的参数字段（实体类的属性）
				Field field = c.getDeclaredField(ele.getName());
				//获取set方法，field.getType())获取它的参数数据类型
				Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
				//调用set方法
				method.invoke(msg, ele.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("xml 格式异常: "+ strXml);
			e.printStackTrace();
		}
		return msg;
	}
    
    public static String formatXmlAnswer(String to, String from, String content) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}
}
