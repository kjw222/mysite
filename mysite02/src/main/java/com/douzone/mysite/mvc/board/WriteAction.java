package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HttpSession session = request.getSession(true);
		UserVo userVo = (UserVo)session.getAttribute("authUser");	//로그인한 사용자의 user no를 세션에 "authUser"에서 받아오기 위해 가져옴.
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		long groupNo = new BoardDao().maxGroupNo();
		Long userNo = userVo.getNo();
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setGroupNo((groupNo+1));
		vo.setOrderNo(0);
		vo.setDepth(0);
		vo.setUserNo(userNo);

		new BoardDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=1", request, response);
		
		

	}

}
