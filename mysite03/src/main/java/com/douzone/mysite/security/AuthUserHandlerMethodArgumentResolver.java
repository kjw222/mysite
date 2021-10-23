package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver extends HandlerMethodArgumentResolverComposite {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		if(authUser ==null) {//authUser가 안 붙어있다!
			return false;
		}
		//authUser가 붙어있으면 이 다음 줄로 넘어감. 
		if(parameter.getParameterType().equals(UserVo.class) ==false) { //파라미터 타ㅣㅇㅂ이 UserVo가 아님. 로그인 해있는 사람과 유저 정보가 다름. 
			return false;
		}
		return true; 
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		//여기까지왔다는 건 세션이 살아있다는 것이므로 세션에 있는 어스 유저를 리턴해준다.
		return session.getAttribute("authUser");
	}

}
