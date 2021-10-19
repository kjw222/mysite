package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		BoardVo boardVo = null;
		
		long no = Long.parseLong(request.getParameter("no"));
		System.out.println("ModifyAction"+no);
			
		HttpSession session = request.getSession();
		
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		boardVo = new BoardVo();
		
		boardVo.setNo(no);
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		
		new BoardDao().update(boardVo);

		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=1", request, response);

	}

}
