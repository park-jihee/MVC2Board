package kr.hs.sdh.board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import kr.hs.sdh.board.vo.BoardVO;
import kr.hs.sdh.utility.DBConnector;

public class BoardDAO {
	
	//db에서 가져와 vo에 담는 일을 처리한다
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	//하나의 게시물 board 가져오기
	public BoardVO getBoard(int seq) {
		BoardVO board = null;
		
		try {
			conn = DBConnector.getConnector();
			
			String sql;
			
			sql = "update board set cnt = cnt + 1 where seq   ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setNickname(rs.getString("nickname"));
				board.setContent(rs.getString("content"));
				board.setCnt(rs.getInt("cnt"));
				board.setRegDate(rs.getDate("regdate"));
				board.setUserId(rs.getString("userid"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(rs, stmt, conn);
		}
		return board;
	}
	
	
	//board list 가져오기
	public ArrayList<BoardVO> getBoardList(String SearchCondition, String SearchKeyword){
		ArrayList<BoardVO> boardVO = null;
		
		if(SearchCondition == null) {
			SearchCondition = "TITLE";
		}
		
		if(SearchKeyword == null) {
			SearchKeyword = "";
		}
		
		try {
			conn = DBConnector.getConnector();
			
			String sql = "";
			
			if(SearchCondition.equals("TITLE")) {
				sql = "select * from board where title like '%'||?||'%' order by seq desc";
			} else {
				sql = "select * from board where cotnent like '%'||?||'%' order by seq desc";
			}
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, SearchKeyword);
			
			rs = stmt.executeQuery();
			
			boardVO = new ArrayList<>();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String nickname = rs.getString("nickname");
				String content = rs.getString("content");
				Date regDate = rs.getDate("regdate");
				int cnt = rs.getInt("cnt");
				String userId = rs.getString("userid");
				
				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setNickname(nickname);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setCnt(cnt);
				vo.setUserId(userId);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return boardVO;
	
	}
	
	//Board 수정하기
	public void updateBoard(BoardVO vo) {
		try {
			conn = DBConnector.getConnector();
			
			String sql = "update board set title = ?, content = ?, userId = ? where seq = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getUserId());
			stmt.setInt(4, vo.getSeq());
			
			stmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public void addBoard(BoardVO vo) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}
