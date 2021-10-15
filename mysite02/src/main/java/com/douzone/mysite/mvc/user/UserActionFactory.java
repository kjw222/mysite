package com.douzone.mysite.mvc.user;

import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println("actionName  == >> " + actionName);
		
		
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
			System.out.println("UserActionFactory actionName ==> "+ actionName+"   in   //   JoinFormAction call");
		}else if("join".equals(actionName)) {
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		}else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		}else if("login".equals(actionName)) {
			action = new LoginAction();
		}else if("logout".equals(actionName)) {
			action = new LogoutAction();
		}else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		}else if("update".equals(actionName)) {
			action = new UpdateAction();
		}else if("updatesuccess".equals(actionName)) {
			action = new UpdateAction();
		} else{
			action = new MainAction();
		}
		return action;
	}

}
