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

public class ReWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserVo userVo = (UserVo)session.getAttribute("authUser");	//로그인한 사용자의 user no를 세션에 "authUser"에서 받아오기 위해 가져옴.
		BoardVo boardVo = (BoardVo)session.getAttribute("boardVo");		//답글을 달기 위해 원글 정보를 가져옴.boardVo에 넣어서!
		
		System.out.println("rewrite user ====>"+userVo);
		System.out.println("rewrite board ====>"+boardVo);
		
		//답글의 고유 정보
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		//이전 글(답글이 달릴 글)의 정보
		Long groupNo = boardVo.getGroupNo();
		Long orderNo = boardVo.getOrderNo();
		Long depth = boardVo.getDepth();
		Long userNo = userVo.getNo();
		
		
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setGroupNo((groupNo));
		vo.setOrderNo(orderNo+1);
		vo.setDepth(depth+1);
		vo.setUserNo(userNo);
		
		new BoardDao().updateOrderNo(vo.getOrderNo());		//같은 그룹에서 자신이 가질 orderNo와 수가 같거나 큰 것들의 orderNo의 수를 1씩 증가시킨다. 
		new BoardDao().insert(vo);
		
		System.out.println("rewriteaction : "+ vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=1", request, response);

	}

}
