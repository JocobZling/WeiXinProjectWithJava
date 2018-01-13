package tulingRobotData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wtu.JDBC.JDBCUtil;

public class robotDao {
	// 判断数据库中是否有相同的数据
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

	// 把数据插入进表格
	public void insertDB(String info, String s) throws Exception {
		JDBCUtil con = new JDBCUtil();
		PreparedStatement ps = con.createPst("insert into robotreturn (info,detail) values(?,?)");
		ps.setString(1, info);
		ps.setString(2, s);
		int iCount = ps.executeUpdate();
		if (iCount > 0) {
			System.out.println("录入成功！");
		} else {
			System.out.println("录入失败！");
		}
	}
	//没有网的情况
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