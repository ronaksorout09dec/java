import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "e23027e5bd52e2272f0f284c902a66f6";

    public static WeatherData getWeatherData(String city) {
        try {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(result.toString());

            String cityName = json.getString("name");
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = json.getJSONObject("main").getDouble("temp");
            double humidity = json.getJSONObject("main").getDouble("humidity");
            double windSpeed = json.getJSONObject("wind").getDouble("speed");

            return new WeatherData(cityName, description, temperature, humidity, windSpeed);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
