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

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long no = Long.parseLong(request.getParameter("no"));
		BoardVo boardVo = null;
		
		System.out.println("ModifyAction"+no);
			
		HttpSession session = request.getSession();
		boardVo = new BoardDao().findByNo(no);
		
		request.setAttribute("boardByNo", boardVo);
		
		MvcUtil.forward("/board/modifyform", request, response);


	}

}
