package tulingRobotData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wtu.JDBC.JDBCUtil;

public class robotDao {
	// �ж����ݿ����Ƿ�����ͬ������
	public String matchDB(String text) throws Exception {
		JDBCUtil con = new JDBCUtil();
		PreparedStatement ps = con.createPst("select * from robotreturn where" + " info='" + text + "'");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString("detail");
		} else {
			return "";
		}
	}

	// �����ݲ�������
	public void insertDB(String info, String s) throws Exception {
		JDBCUtil con = new JDBCUtil();
		PreparedStatement ps = con.createPst("insert into robotreturn (info,detail) values(?,?)");
		ps.setString(1, info);
		ps.setString(2, s);
		int iCount = ps.executeUpdate();
		if (iCount > 0) {
			System.out.println("¼��ɹ���");
		} else {
			System.out.println("¼��ʧ�ܣ�");
		}
	}
	//û���������
	public String noInternet(String text) throws Exception {
		JDBCUtil con = new JDBCUtil();
		PreparedStatement ps = con.createPst("select * from robotreturn where" + " info='" + text + "'");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString("detail");
		} else{
			return "";
		}
	}
}