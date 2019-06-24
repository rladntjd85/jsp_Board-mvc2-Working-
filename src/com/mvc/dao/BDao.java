package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.dto.BDto;

public class BDao {

	DataSource dataSource;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		
		try {
			InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/bbs");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public void write(String bName, String bTitle, String bContent, String userID) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "INSERT INTO mvc_board ( bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, userID ) VALUES ( (SELECT IFNULL(MAX(bId) + 1, 1) FROM mvc_board b), ?, ?, ?, now(), 0, (SELECT IFNULL(MAX(bGroup) + 1, 1) FROM mvc_board b), 0, 0, ?)";
			//String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, userID) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ? )";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4, userID);
			
			//System.out.println(query);
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BDto> list(String pageNumber) {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		System.out.println(":::::list는"+pageNumber);
		
		try {
			//10개씩 끊기 위한 쿼리문
			String SQL = "select * from mvc_board where bGroup > (select max(bGroup) from mvc_board) - ? and bGroup <= (select max(bGroup) from mvc_board) - ? order by bGroup desc, bStep asc";
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, Integer.parseInt(pageNumber) * 10);
			ps.setInt(2, (Integer.parseInt(pageNumber) -1) * 10);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String userID = rs.getString("userID");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, userID);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public boolean nextPage(String pageNumber) {
		// TODO Auto-generated method stub
		System.out.println(":::::nextPage는"+pageNumber);
		try {
			conn = dataSource.getConnection();
			
			String sql = "select * from mvc_board where bGroup = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pageNumber) * 10);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public int targetPage(String pageNumber) {
		// TODO Auto-generated method stub
		System.out.println(":::::targetPage는"+pageNumber);
		try {
			conn = dataSource.getConnection();
			String sql = "select count(bGroup) from mvc_board where bGroup > ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (Integer.parseInt(pageNumber) -1) * 10);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1) / 10;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public BDto contentView(String strId) {
		// TODO Auto-generated method stub
		
		upHiT(strId);
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			
			String sql = "select * from mvc_board where bId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(strId));
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String userID = rs.getString("userID");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, userID);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}

	private void upHiT(String bId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvc_board set bHit = bHit + 1 where bId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bId);
			
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public void delete(String bId) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = dataSource.getConnection();
			String sql = "delete from mvc_board where bId =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public BDto reply_view(String str) {
		// TODO Auto-generated method stub
		BDto dto = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			conn = dataSource.getConnection();
			String sql = "select * from mvc_board where bId = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(str));
			rs = ps.executeQuery();
			
			//System.out.println(ps);
			//System.out.println(rs);
			
			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String userID = rs.getString("userID");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,userID);
			
			}
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dto;
	}

	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent, String userID, BDto parent) {
		// TODO Auto-generated method stub
		
		replyShape(bGroup, bStep);
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = dataSource.getConnection();
			String sql = "insert into mvc_board (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, userID) values ( (SELECT IFNULL(MAX(bId) + 1, 1) FROM mvc_board b), ?, ?, ?, now(), 0, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, parent.getbGroup());
			ps.setInt(5, Integer.parseInt(bStep) + 1);
			ps.setInt(6, Integer.parseInt(bIndent) + 1);
			ps.setString(7, userID);
			
			int rn = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	/*public int replyUpdate(BDto parent) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parent.getbGroup());
			ps.setInt(2, parent.getbStep());
			
			return ps.executeUpdate();
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1; //db오류
	}*/
	

	private void replyShape(String strGroup, String strStep) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(strGroup));
			ps.setInt(2, Integer.parseInt(strStep));
			
			int rn = ps.executeUpdate();
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
}