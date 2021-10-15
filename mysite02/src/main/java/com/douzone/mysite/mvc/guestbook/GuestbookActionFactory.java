package com.douzone.mysite.mvc.guestbook;

import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println("GuestbookActionFactory actionName ==> "+ actionName);
		
		if("deleteform".equals(actionName)) {
		action = new DeleteFormAction();
		}else if("delete".equals(actionName)) {
		action = new DeleteAction();
		}else if("list".equals(actionName)) {
		action = new ListAction();
		}else if("add".equals(actionName)) {
		action = new AddAction();
		}else {
			action = new MainAction();
		}
		
		return action;
	}

}
