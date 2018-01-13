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
		//�����������
		response.setContentType("text/html;charset=utf-8");
		//�����������
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		request.setCharacterEncoding("utf-8");
		String info = request.getParameter("info");
		// System.out.println(request);
		// ���������ݿ�ƥ��
		robotDao dao = new robotDao();
		PrintWriter out = null;
		try {
			String findString = dao.matchDB(info);
			System.out.println("��������" + findString);
			if (findString != "") {
				out = response.getWriter();
				out.write(findString);
				System.out.println(findString);

			} else {
				// ���û��ֻ�е���������Դ���ݣ��Ұ����ݴ������ݿ���
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
