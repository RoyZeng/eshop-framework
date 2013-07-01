package com.elliot.plugin.member.manager;

import com.elliot.plugin.member.manager.IUserService;
import com.elliot.plugin.member.manager.impl.UserServiceImpl;

/**
 * 用户服务工厂，返回服务服务
 * @author andy
 *
 */
public final class UserServiceFactory {
	public static int  isTest= 0;
	private static IUserService userService;
	public static void set(IUserService _userService){
		 userService =_userService;
	}
	
	public static IUserService getUserService(){
		if(userService!=null)
		 return userService;
		 
		 return new UserServiceImpl();
	 
	}

	 
	
	
}



