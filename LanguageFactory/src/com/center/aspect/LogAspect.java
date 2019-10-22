package com.center.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.center.mapper.system.LogMapper;
import com.center.po.system.Log;
import com.center.po.system.User;

@Aspect
@Component
public class LogAspect {
	
	public LogAspect(){
		System.out.println("*********************************LogAspect*****************");
	}
	
	@Autowired
	private LogMapper logMapper;
	
	/*  
	 * AspectJ类型匹配的通配符：
	   *：匹配任何数量字符；
       ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。
       +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。 
	 	
	   execution：用于匹配方法执行的连接点；注解？ 修饰符? 返回值类型 类型声明?方法名(参数列表) 异常列表？
       within：用于匹配指定类型内的方法执行；
       this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
       target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
       args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
	 */
	
	@Pointcut("execution(public * com.center.controller..*.*(..))")
	public void pointCutMethod(){}
	
	//在退出登录前，记录日志
	@Before("execution(public * com.center.controller.system.UserController.logout(..))")
	public void beforMethod(JoinPoint jp){
		Log log = createLog(jp);
		try {
			logMapper.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//后置通知，在操作后记录日志
	@After("pointCutMethod()")
	public void afterMethod(JoinPoint jp){
		Log log = createLog(jp);
		try {
			logMapper.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Log createLog(JoinPoint jp){
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//获取目标对象执行方法
		String methodName = jp.getSignature().getName();
		//获取当前请求的URL
		String path = request.getServletPath();
		String param = request.getQueryString();
		if (null != param) {
			path = path + "?" + param;
		}
		
		Log log = new Log();
		if(null == user){
			log.setLogUserId(0);
		} else {
			log.setLogUserId(user.getUserId());
		}
		log.setLogOperation(methodName);
		log.setLogRequest(path);
		
		return log;
	}
}
