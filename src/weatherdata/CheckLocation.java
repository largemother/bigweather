package weatherdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class CheckLocation {
	private String coordinates = "null";
	private String formattedAddress;

	public CheckLocation(String address) {
		try {
			String key = "AIzaSyDeBq4FfKSZhXUdvSmMgGE2WQ3BSuJ6fqo";

			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address, "UTF-8") + "?key=" + URLEncoder.encode(key, "UTF-8");

			// String key = "test/";
			
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
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
		
			double laditude = ((JSONArray)myResponse.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");
			double longitude = ((JSONArray)myResponse.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");
			formattedAddress = ((JSONArray)myResponse.get("results")).getJSONObject(0)
                    .getString("formatted_address");
			coordinates = (laditude + "," + longitude);
	
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
	}   

	public String getCoordinates() {
		return coordinates;
	}
	
	public String getFormattedAddress () {
		return formattedAddress;
	}

}
