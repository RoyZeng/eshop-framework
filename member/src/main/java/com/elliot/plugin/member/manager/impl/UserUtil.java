package com.elliot.plugin.member.manager.impl;

import com.elliot.framework.exception.EopException;
import com.elliot.plugin.member.manager.IUserService;
import com.elliot.plugin.member.manager.UserServiceFactory;


/**
 * @author lzf
 *         <p>
 *         2009-12-16 上午10:46:24
 *         </p>
 * @version 1.0
 */
public final class UserUtil {

	private static IUserService userService;
	
	public static void validUser(Integer userid) {
		
		userService = UserServiceFactory.getUserService();
		
		if (!userid.equals(userService.getCurrentUserId())) {
			throw new EopException("非法操作");
		}
		
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
