package weatherdata;

public class Date {
	private String location, summary, icon;
	private int temp, feelsLike, pressure, windSpeed, visibility;
	private double humidity;
	private String image = "/resources/sunny.gif";
	
	public Date(String location, int temp, int feelsLike, String summary, String icon, double humidity, int pressure,
			int windSpeed, int visibility) {

		this.location = location;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.summary = summary;
		this.icon = icon;
		this.humidity = humidity;
		this.pressure = pressure;
		this.windSpeed = windSpeed;
		this.visibility = visibility;

	}

	public String getLocation() {
		return location;
	}

	public String getSummary() {
		return summary;
	}

	public int getTemp() {
		return temp;
	}

	public int getFeelsLike() {
		return feelsLike;
	}

	public double getHumidity() {
		return humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	public int getVisibility() {
		return visibility;
	}

	public String getIcon () {
		switch (icon) {
         case "clear-day":
         image  = "/resources/sunny.gif";
         break;
         case "clear-night":
             image = "/resources/sunny.gif";
             break;
         case "rain":
             image = "/resources/rain.gif";
             break;
         case "snow":
             image = "/resources/snow.gif";
             break;
         case "sleet":
             image = "/resources/showers.gif";
             break;
         case "wind":
             image = "/resources/fog.gif";
             break;
         case "cloudy":
             image = "/resources/cloudy.gif";
             break;
         case "partly-cloudy-day":
             image = "/resources/cloudy.gif";
             break;
         case "partly-cloudy-night":
             image = "/resources/cloudy.gif";
             break;
         case "hail":
             image = "/resources/snow.gif";
             break;
         case "thunderstorm":
             image = "/resources/thunder.gif";
             break;
         case "tornado":
             image = "/resources/fog.gif";
             break;
		 }
		return image;
	}


}
