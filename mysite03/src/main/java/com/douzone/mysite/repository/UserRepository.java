package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository{
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
public UserVo findByEmailAndPassword(String email, String password) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserVo vo = null;
			
	try {
		conn = getConnection();
		
		String sql =
			"   select no, name from user where email=? and password=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			Long no = rs.getLong(1);
			String name = rs.getString(2);
	
			
			vo = new UserVo();
			vo.setNo(no);
			vo.setName(name);
			
		}
		
	} catch (SQLException e) {
		//throw new GuestbookRepositoryException(e.toString());
	} finally {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return vo;
}

	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert(vo)");
		return count ==1;
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}


	public UserVo findByNo(Long num) {
		
		//update시 번호를 통해 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"   select no, name, email, gender  "
				+ "from user "
				+ "where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, num);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
		
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
		
	}


	public void update(UserVo vo) {
		System.out.println("UserDao (update) : "+ vo);
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" UPDATE user "
					+ "SET name = ?, password = ?, gender = ? "
					+ "WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getEmail());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return ;
		
	}	
}
