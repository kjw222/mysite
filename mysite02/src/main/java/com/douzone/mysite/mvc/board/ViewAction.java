package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo boardVo = null;
		
		long no = Long.parseLong(request.getParameter("no"));
		
			
		HttpSession session = request.getSession();
		boardVo = new BoardDao().findByNo(no);
		new BoardDao().updateHit(no);
		request.setAttribute("boardByNo", boardVo);
		session.setAttribute("boardVo", boardVo);

		MvcUtil.forward("/board/view", request, response);

	}

	

}
