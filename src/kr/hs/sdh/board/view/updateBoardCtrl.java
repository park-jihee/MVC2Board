package kr.hs.sdh.board.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.hs.sdh.board.dao.BoardDAO;
import kr.hs.sdh.board.vo.BoardVO;

public class updateBoardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			response.sendRedirect("login.jsp");
		}
		
		//수정할 내용의 값 받기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		//DAO를 이용하여 데이터 처리
		BoardDAO dao = new BoardDAO();
		
		BoardVO vo = new BoardVO();
				
		vo.setTitle(title);
		vo.setContent(content);
		vo.setSeq(seq);
		
		dao.updateBoard(vo);
		
		response.sendRedirect("getBoardListCtrl");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
