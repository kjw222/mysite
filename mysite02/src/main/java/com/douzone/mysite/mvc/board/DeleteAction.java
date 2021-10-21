package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		Long boardNo = Long.parseLong(request.getParameter("no"));
		long userNo = authUser.getNo();
		
		System.out.println("delete board_no : "+boardNo);
		System.out.println("delete authUser : "+userNo);
		
		
		new BoardDao().delete(userNo, boardNo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=1", request, response);
		
		
		
	}

}
