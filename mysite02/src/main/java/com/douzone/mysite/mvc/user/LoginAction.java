package com.douzone.mysite.mvc.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserVo userVo = new UserDao().findByEmailAndPassword(email, password); //email과 pw를 입력해서 no값과 name을 받아올 수 있음. session에 no값과 name값이 저장되어있으며 해당값을 이 클래스에서는 userVo에 저장하였음. 따라서 no와 name을 제외한 나머지 값은 null이 됨.
		
		
		if(userVo == null) {
			/* 로그인 실패 */
			request.setAttribute("result", "fail");
			MvcUtil.forward("/user/loginform", request, response);
			return;
		}
		
		/* 인증처리(세션처리) */
		HttpSession session = request.getSession(true);
		
		session.setAttribute("authUser", userVo);
		
		System.out.println("LoginAction (authUser) : "+ (UserVo)session.getAttribute("authUser"));
		System.out.println("LoginAction (userVo) : "+userVo);
		
		
		MvcUtil.redirect("/mysite02/", request, response);
	}

}
