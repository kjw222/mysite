package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println("BoardActionFactory ======>("+actionName+") ");
		
		if("list".equals(actionName)) {	
			action = new ListAction();
		}else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();	
		}else if("view".equals(actionName)) {
			action = new ViewAction();	
		}else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();	
		}else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if("rewriteform".equals(actionName)) {
			System.out.println("BoardActionFactory ("+actionName+") in");
			action = new ReWriteFormAction();
			System.out.println("BoardActionFactory ("+actionName+") out");
		}else if("rewrite".equals(actionName)) {
			System.out.println("BoardActionFactory ("+actionName+") in");
			action = new ReWriteAction();
			System.out.println("BoardActionFactory ("+actionName+") out");
		}else if("search".equals(actionName)) {
			
			//action = new SearchAction();	
			
		}else {
			action = new ListAction();
		}
		
		
		return action;
	}

}
