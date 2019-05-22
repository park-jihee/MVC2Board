package kr.hs.sdh.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {

	// DB의 연결설정하는 Static 메소드
	public static Connection getConnector() {
		Connection conn = null;

		try {
			// 1. 드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결설정
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//여기서는 conn을 닫으면 안됨
		//DB에서 데이터를 가져와서 사용후 닫아야 함.
			return conn;
		}
	
	public static void close(PreparedStatement stmt, Connection conn){
		if(stmt != null) {
			try {
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		if(stmt != null) {
			try {
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}	
	}
}
