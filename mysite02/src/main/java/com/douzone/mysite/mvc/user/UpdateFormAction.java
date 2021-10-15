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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Access Constrol( 보안.인증체크하기.. 
		HttpSession session = request.getSession();  //세션값..
		UserVo a = (UserVo)session.getAttribute("authUser"); //로그인한 사람 확인 
		
	//	a.setName("바뀜");
		
		if(a == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		Long no = a.getNo();
		UserVo userVo = new UserDao().findByNo(no);
		request.setAttribute("userVo", userVo); //session의 map에 userVo라는 이름으로 userVo를 저장

		System.out.println("updateformAction (authUser) : "+ a);  //login시 들어갔던 session 값(key가 authUser 인 session의 value값이므로 authUser은 no와 name밖에 없음.)
		System.out.println("updateformAction (userVo) : "+userVo);  //no, name, email, gender모두 출력되는데 왜.. 값이.. 안 빠지지..
		
		MvcUtil.forward("user/updateform", request, response);  //updateform.jsp 호출

	}

}
