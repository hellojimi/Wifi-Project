package wifi;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import wifi.WifiDAO;
import wifi.WifiDTO;

public class WifiService {
	
	private static final String API_KEY = "4541746348726a73313232427456437a";
	private static OkHttpClient client;
	private static Request.Builder builder;
	private static int size;
	private static String totalCount;
	
	public WifiService() {
		client = new OkHttpClient();
		builder = new Request.Builder();
	}
	
	public int insertWifi(int start, int end) throws IOException {
		String url = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/" + start + "/" + end + "/";
		
		Request request = builder.url(url).get().build();
		Response response = client.newCall(request).execute();
		
		if(response.isSuccessful()) {
			ResponseBody body = response.body();
			
			if(body != null) {
				List<WifiDTO> list = getWifiList(body.string());
				
				WifiDAO wifi = new WifiDAO();
				wifi.insertWifi(list);
				
				size += list.size();
				if(size < Integer.parseInt(totalCount)) {
					insertWifi(start + 1000, end + 1000);
				}
			}
		}
		
		return size;
	}
	
	private List<WifiDTO> getWifiList(String body) {
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(body);
		
		String rowString = jsonObject.get("TbPublicWifiInfo").getAsJsonObject().get("row").toString();
		totalCount = jsonObject.get("TbPublicWifiInfo").getAsJsonObject().get("list_total_count").toString();
		
		Gson gson = new Gson();
		WifiDTO[] array = gson.fromJson(rowString, WifiDTO[].class);
		List<WifiDTO> list = Arrays.asList(array);
		
		return list;
	}

}
