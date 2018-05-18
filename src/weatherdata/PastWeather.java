package weatherdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class PastWeather {
	private Date day;

	PastWeather(String coordinates, String time, String location) throws Exception {

		String key = "0a6087d945da30fc5a6f5761cdac8b59/";
		String url = "https://api.darksky.net/forecast/";
		URLEncoder.encode(key, "UTF-8");
		URLEncoder.encode(coordinates, "UTF-8");
		URLEncoder.encode(time, "UTF-8");

		String fullUrl = url + "" + key + "" + coordinates + "," + time + "?units=si";
		
		URL urlObj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + fullUrl);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(response.toString());
		int temp = myResponse.getJSONObject("currently").getInt("temperature");
		int feels = myResponse.getJSONObject("currently").getInt("apparentTemperature");
		String summary = myResponse.getJSONObject("currently").getString("summary");
		String icon = myResponse.getJSONObject("currently").getString("icon");
		int humidity = myResponse.getJSONObject("currently").getInt("humidity");
		int pressure = myResponse.getJSONObject("currently").getInt("pressure");
		int visibility = myResponse.getJSONObject("currently").getInt("visibility");

		day = new Date(location, temp, feels, summary, icon, humidity, pressure, visibility);

	}

	public Date getDate() {
		return day;
	}

}
