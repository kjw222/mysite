package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) { //
			return true;
		}
		
			//2. casting 
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class); //main으로 들어가면 null이 아님.
		
		//4. handler method에 @auth가 없으면 type에 붙어있는지 확인. 
		if(auth == null) {
			//과제
		}
		//5. type과 method에 @Auth가 없으면 적용이 안되어 있는 경우.
		if(auth ==null) {
			return true; //인증이 필요없느 ㄴ경우느 true를 반환하여 그냥 실행시킴.
		}
		//6.@Auth가 적용이 되어있기 때문에 인증(Authenfication)여부를 확인
		HttpSession session = request.getSession();
		if(session == null) { //경로를 따라온 게 아니라 주소를 복사 붙여넣기 하여 들어온 경우 바로 로그인 창으로 보내준다.
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { //
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//7. 권한(authorization) 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
		String role = auth.role();
		
		//8. 권한 체크
		//과제..
		
		return true;	//false는 막아놓는 거..
	}

}
