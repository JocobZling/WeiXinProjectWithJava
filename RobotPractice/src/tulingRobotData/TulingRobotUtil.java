package tulingRobotData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

public class TulingRobotUtil {
	private String info;
	public static final String url = "http://www.tuling123.com/openapi/api";

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public TulingRobotUtil() {

	}

	public String SendPost() throws UnsupportedEncodingException {
		String myKey = "?key=86f59dab7924470ca1c60b7ac7933c1d";
		info = URLEncoder.encode(info, "utf-8");
		String urlPost = url + myKey + "&info=" + info;
		try {
			URL getUrl = new URL(urlPost);
			HttpURLConnection connection;
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer robotStringBuffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				robotStringBuffer.append(line);
			}
			reader.close();
			connection.disconnect();
			System.out.println(robotStringBuffer);
			return robotStringBuffer.toString();

		} catch (IOException e) {
			String result = "404";
			return result;
		}
	}
}
