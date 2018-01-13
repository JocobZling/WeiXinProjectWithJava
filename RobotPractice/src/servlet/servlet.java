package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsonUtil.JsonUtil;
import tulingRobotData.TulingRobotUtil;
import tulingRobotData.robotDao;

public class servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决编码问题
		response.setContentType("text/html;charset=utf-8");
		//解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		request.setCharacterEncoding("utf-8");
		String info = request.getParameter("info");
		// System.out.println(request);
		// 不联网数据库匹配
		robotDao dao = new robotDao();
		PrintWriter out = null;
		try {
			String findString = dao.matchDB(info);
			System.out.println("有数据吗：" + findString);
			if (findString != "") {
				out = response.getWriter();
				out.write(findString);
				System.out.println(findString);

			} else {
				// 如果没有只有调用网络资源数据，且把数据存在数据库中
				TulingRobotUtil tuling = new TulingRobotUtil();
				tuling.setInfo(info);
				String s = tuling.SendPost();
				if (s.equals("404")) {
					out = response.getWriter();
					out.write(dao.noInternet(s));
					System.out.println(s);
				} else {
					dao.insertDB(info, s);
					out = response.getWriter();
					out.write(s);
					System.out.println(s);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
