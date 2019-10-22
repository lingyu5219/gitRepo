package com.center.service.impl.system;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.center.mapper.system.UserMapper;
import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.po.system.UserQuery;
import com.center.service.system.UserService;
import com.google.code.kaptcha.Constants;

import net.sf.json.JSONObject;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	 
	@Override
	public boolean staffIsRelatedByUser(int staffId) throws Exception {
		long count = userMapper.staffIsRelatedByUser(staffId);
		return count > 0;
	}
	
	@Override
	public boolean staffIsRelatedByUserNotSelf(UserQuery userQuery) throws Exception {
		long count = userMapper.staffIsRelatedByUserNotSelf(userQuery);
		return count > 0;
	}

	@Override
	public boolean userNameExist(String userName) throws Exception {
		long count = userMapper.userNameExist(userName);
		return count > 0;
	}

	@Override
	public boolean userNameExistNotSelf(UserQuery userQuery) throws Exception {
		long count = userMapper.userNameExistNotSelf(userQuery);
		return count > 0;
	}
	
	@Override
	public DatatablesView<User> queryUserList(UserQuery userQuery) throws Exception {
		DatatablesView<User> dataView = new DatatablesView<User>();
		List<User> list = userMapper.queryUserList(userQuery);
        long Count = userMapper.queryUserCount(userQuery);
        dataView.setData(list);
        dataView.setRecordsTotal(Count);
		return dataView;
	}

	@Override
	public User checkUser(UserQuery userQuery) throws Exception {
		User user = userMapper.checkUser(userQuery);
		return user;
	}

	 private boolean Judge(int num){
		 if(num>0)
			 return true;
		 else
			 return false;
	 }
	
	
	@Override
	public boolean addUser(User user) throws Exception {
		//检查用户名是否已经存在
		//检查员工是否已经关联其他系统用户
		if(userMapper.userNameExist(user.getUserName()) <= 0 && userMapper.staffIsRelatedByUser(user.getStaffId()) <= 0){
			userMapper.addUser(user);
		}
		return user.getUserId() > 0;
	}

	@Override
	public boolean delUser(int userId) throws Exception {
		return Judge(userMapper.delUser(userId));
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		return Judge(userMapper.updateUser(user));
	}

	@Override
	public String updatePassword(User user, HttpServletRequest request) throws Exception {
		HashMap<String,String> rsMap = new HashMap<String,String>();
		
		String verifyCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
		
		if (!verifyCode.equals(user.getVerifyCode().toUpperCase())) {
			rsMap.put("status", "0");
			rsMap.put("info", "验证码错误");
			return JSONObject.fromObject(rsMap).toString();
		}
		if (userMapper.checkUserPassword(user) <= 0) {
			rsMap.put("status", "0");
			rsMap.put("info", "原密码错误");
			return JSONObject.fromObject(rsMap).toString();
		}
		
		user.setUserPassword(user.getNewPassword());
		if(userMapper.updateUser(user) > 0){
			rsMap.put("status", "1");
			rsMap.put("info", "修改密码成功");
			return JSONObject.fromObject(rsMap).toString();
		} else {
			rsMap.put("status", "0");
			rsMap.put("info", "修改密码失败");
			return JSONObject.fromObject(rsMap).toString();
		}
	}
	
}
