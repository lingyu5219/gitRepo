package com.center.service.system;

import javax.servlet.http.HttpServletRequest;

import com.center.po.query.DatatablesView;
import com.center.po.system.User;
import com.center.po.system.UserQuery;

public interface UserService {
	public DatatablesView<User> queryUserList(UserQuery userQuery) throws Exception;
	public User checkUser(UserQuery userQuery) throws Exception;
	public boolean addUser(User user) throws Exception;
	public boolean delUser(int userId) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public boolean staffIsRelatedByUser(int staffId) throws Exception;
	public boolean staffIsRelatedByUserNotSelf(UserQuery userQuery) throws Exception;
	public boolean userNameExist(String userName) throws Exception;
	public boolean userNameExistNotSelf(UserQuery userQuery) throws Exception;
	public String updatePassword(User user, HttpServletRequest request) throws Exception;
}
