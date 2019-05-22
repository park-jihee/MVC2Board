package kr.hs.sdh.board.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.hs.sdh.board.dao.BoardDAO;
import kr.hs.sdh.board.vo.BoardVO;

public class getBoardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			response.sendRedirect("login.jsp");
		} else {
			// DB에서 한 개의 자료 가져오기
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			// DB처리는 DAO(모델)에서
			BoardDAO dao = new BoardDAO();
			BoardVO board = dao.getBoard(seq);
			
			// board를 전달해서 새로운 화면 열기
			request.setAttribute("board", board);
			RequestDispatcher view = request.getRequestDispatcher("getBoard.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
