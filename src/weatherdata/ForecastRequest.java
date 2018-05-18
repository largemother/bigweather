package weatherdata;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ForecastRequest {
	private Date day;

	public ForecastRequest (String coordinates, String location) {
		try {
			String url = "https://api.darksky.net/forecast/";

			String key = "0a6087d945da30fc5a6f5761cdac8b59/";
			// String key = "test/";

			String fullUrl = url + "" + key + "" + coordinates + "?units=si";

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
			double humidity = myResponse.getJSONObject("currently").getDouble("humidity");
			int pressure = myResponse.getJSONObject("currently").getInt("pressure");
			int windSpeed = myResponse.getJSONObject("currently").getInt("windSpeed");
			int visibility = myResponse.getJSONObject("currently").getInt("visibility");
			
			day = new Date(location, temp, feels, summary, icon, humidity, pressure, windSpeed, visibility);

		} catch (Exception e) {
			System.out.println("");
		}
	}

	public Date getDate() {
		return day;
	}
}