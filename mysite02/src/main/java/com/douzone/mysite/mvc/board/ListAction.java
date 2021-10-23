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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		List<BoardVo> boardVo = null;
		HttpSession session = request.getSession();
	
		long p = Long.parseLong(request.getParameter("p")); //선택 페이지
		long startBoard = (p*10)-9;	//리스트가 시작되는 보드 count 숫자
		
		long boardLength = new BoardDao().countBoardNum(); //db에 저장된 보드 갯수
		
		long startPageNum = ((int)(((p-1)/5)))*5+1; //페이징 스타트 페이지.
		long endPageNum = startPageNum+4;
		long maxPageNum = ((int)(boardLength/10)+((boardLength%10)==0?0:1));
		
		if(endPageNum > maxPageNum) {endPageNum=maxPageNum;}
		
		System.out.println("p : "+p+" startBoard : "+startBoard+" boardLength : "+ boardLength+" startPageNum : "+startPageNum+" endPageNum : "+endPageNum+" maxPageNum : "+maxPageNum);
		String kwd = request.getParameter("kwd");
		System.out.println("kwd : "+kwd);
		if(kwd == null) {
		boardVo = new BoardDao().findPage(startBoard);
		}else {boardVo = new BoardDao().Search(kwd, p);}
		request.setAttribute("p", p);
		request.setAttribute("startBoard", startBoard);
		request.setAttribute("maxPageNum", maxPageNum);
		request.setAttribute("maxBoardNum", boardLength);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("boardList", boardVo);
		
		
		
		System.out.println("p : "+p+" startBoard : "+startBoard+" boardLength : "+ boardLength+" startPageNum : "+startPageNum+" endPageNum : "+endPageNum+" maxPageNum : "+maxPageNum);
		
		System.out.println("boardList : "+boardVo);
		
		
		MvcUtil.forward("board/list", request, response);

	}

}
