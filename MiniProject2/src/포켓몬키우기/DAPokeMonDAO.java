package 포켓몬키우기;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAPokeMonDAO {
	static Connection conn = null;
	static PreparedStatement pstm = null;
	static ResultSet rs = null;

	public void getConn() { // 접속용
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String id = "campus_k_0417_1";
			String pw = "smhrd1";
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() { // 종료
		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int join(String id, String pw) {
		getConn();
		int result = 0;

		try {
			String sql = "INSERT INTO user_info (id, pw) VALUES (?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);

			result = pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;

	}

	public int login(String id, String pw) {
		getConn();
		int nick = -1;
		try {
			String sql = "select * from user_info where id = ? and pw = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);

			rs = pstm.executeQuery();
			if (rs.next()) {
				if(rs.getInt("live")> 0) nick = rs.getInt("live");
				else nick = 0;
			} 
		} catch (SQLException e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		close();
		return nick;
	}

	public int update(PKDTO pk) {
		if(pk.getSTT() <= 0 || pk.getFTG() <= 0) {
			pk.setLIVE(0);
		}
	    getConn();
	    int result = 0;

	    try {
	        String sql = "UPDATE user_info SET LIVE=?, ATK=?, DEF=?, LV=?, HP=?, XP=?, STT=?, FTG=?, CNDTN=?, PKNAME=? WHERE id=? AND pw=?";
	        pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, pk.getLIVE());
	        pstm.setInt(2, pk.getATK());
	        pstm.setInt(3, pk.getDEF());
	        pstm.setInt(4, pk.getLV());
	        pstm.setInt(5, pk.getHP());
	        pstm.setInt(6, pk.getXP());
	        pstm.setInt(7, pk.getSTT());
	        pstm.setInt(8, pk.getFTG());
	        pstm.setInt(9, pk.getCNDTN());
	        pstm.setString(10, pk.getPKNAME());
	        pstm.setString(11, pk.getID());
	        pstm.setString(12, pk.getPW());

	        result = pstm.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    close();
	    return result;
	}
	
	public PKDTO select(String id, String pw) {
	    getConn();
	    PKDTO pk = null;
	    try {
	        String sql = "SELECT * FROM user_info WHERE id=? AND pw=?";
	        pstm = conn.prepareStatement(sql);
	        pstm.setString(1, id);
	        pstm.setString(2, pw);
	        rs = pstm.executeQuery();

	        if (rs.next()) {
	            pk = new PKDTO(0, 0, 0, 0, 0, 0, 0, 0, 0, sql, sql, sql);
	            pk.setID(rs.getString("id"));
	            pk.setPW(rs.getString("pw"));
	            pk.setLIVE(rs.getInt("LIVE"));
	            pk.setATK(rs.getInt("ATK"));
	            pk.setDEF(rs.getInt("DEF"));
	            pk.setLV(rs.getInt("LV"));
	            pk.setHP(rs.getInt("HP"));
	            pk.setXP(rs.getInt("XP"));
	            pk.setSTT(rs.getInt("STT"));
	            pk.setFTG(rs.getInt("FTG"));
	            pk.setCNDTN(rs.getInt("CNDTN"));
	            pk.setPKNAME(rs.getString("PKNAME"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    close();
	    return pk;
	}

}
