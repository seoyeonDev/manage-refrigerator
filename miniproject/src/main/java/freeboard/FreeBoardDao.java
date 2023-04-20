package freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class FreeBoardDao {
	private DBConnect dbconn;
	
	public FreeBoardDao() {
		dbconn = DBConnect.getInstance();
	}
	
	// 글번호로 검색
	public FreeBoardVo select(int num) {
		Connection conn = dbconn.conn();
		String sql = "select * from free_board where fb_num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();// select 실행
			if (rs.next()) {
				return new FreeBoardVo(rs.getInt(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	//원글 모두 검색
	public ArrayList<FreeBoardVo> selectAll() {
		Connection conn = dbconn.conn();
		String sql = "select * from free_board where parent=0 order by num desc";
		ArrayList<FreeBoardVo> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new FreeBoardVo(rs.getInt(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	//댓글 검색
	public ArrayList<FreeBoardVo> selectReps(int parent) {
		Connection conn = dbconn.conn();
		String sql = "select * from free_board where parent=? order by num desc";
		ArrayList<FreeBoardVo> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parent);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new FreeBoardVo(rs.getInt(1), rs.getString(2),rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//원글, 댓글 작성
	public void insert(FreeBoardVo vo) {
		Connection conn = dbconn.conn();

		String sql = "insert into free_board values(seq_fbnum.nextval, ?, sysdate, ?, ?, ?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getLikes());
			pstmt.setInt(5, vo.getCnt());
			pstmt.setInt(6, vo.getParent());
			int num = pstmt.executeUpdate();
			System.out.println(num + " 줄이 추가되었다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// update: 글번호로 찾아서 title, content를 새 값으로 수정
	public void update(FreeBoardVo vo) {
		Connection conn = dbconn.conn();
		String sql = "update free_board set title=?, content=? where fb_num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getFb_num());
			int num = pstmt.executeUpdate();
			System.out.println(num + " 줄이 수정됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// delete: 글번호로 찾아서 삭제
	public void delete(int num) {
		Connection conn = dbconn.conn();
		String sql = "delete from free_board where fb_num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int num2 = pstmt.executeUpdate();
			System.out.println(num2 + " 줄이 삭제됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}