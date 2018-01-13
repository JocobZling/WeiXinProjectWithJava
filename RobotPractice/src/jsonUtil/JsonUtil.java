package jsonUtil;

import com.google.gson.Gson;

public class JsonUtil {

	public static String createJsonString(String string) {
		Gson gson = new Gson();
        String jsonStr = gson.toJson(string);
        return jsonStr;
	}
}
