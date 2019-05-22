package kr.hs.sdh.board.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.hs.sdh.board.dao.BoardDAO;
import kr.hs.sdh.board.vo.BoardVO;

public class getBoardListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			response.sendRedirect("login.jsp");
		} else {
			String SearchCondition = request.getParameter("SearchCondition");
			String SearchKeyword = request.getParameter("SearchKeyword");
			
			BoardDAO dao = new BoardDAO();
			
			ArrayList<BoardVO> boardList = dao.getBoardList(SearchCondition, SearchKeyword);
			request.setAttribute("boardList", boardList);
			RequestDispatcher view = request.getRequestDispatcher("getBoardList.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
