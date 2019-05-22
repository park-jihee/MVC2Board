package kr.hs.sdh.board.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import kr.hs.sdh.board.dao.BoardDAO;
import kr.hs.sdh.board.vo.BoardVO;

public class addBoardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO vo = new BoardVO();
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userId = (String) session.getAttribute("id");
		String nickname = (String) session.getAttribute("name");
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserId(userId);
		vo.setNickname(nickname);
		
		BoardDAO dao = new BoardDAO();
		
		dao.addBoard(vo);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
