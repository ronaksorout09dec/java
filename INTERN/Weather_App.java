package INTERN;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather_App extends JFrame {
    private JTextField cityField;
    private JLabel temperatureLabel;
    private JLabel descriptionLabel;
    private JLabel humidityLabel;
    private final String API_KEY = "e23027e5bd52e2272f0f284c902a66f6"; // Your valid API key

    @SuppressWarnings("unused")
    public Weather_App() {
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create input panel
        JPanel inputPanel = new JPanel();
        cityField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> getWeather());

        inputPanel.add(new JLabel("City: "));
        inputPanel.add(cityField);
        inputPanel.add(searchButton);

        // Create display panel
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(3, 1, 5, 5));

        temperatureLabel = new JLabel("Temperature: --");
        descriptionLabel = new JLabel("Description: --");
        humidityLabel = new JLabel("Humidity: --");

        displayPanel.add(temperatureLabel);
        displayPanel.add(descriptionLabel);
        displayPanel.add(humidityLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);

        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("deprecation")
    private void getWeather() {
        try {
            String city = cityField.getText().trim();
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a city name.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Encode city name to handle spaces and special characters
            String encodedCity = URLEncoder.encode(city, "UTF-8");

            String urlString = String.format(
                "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                encodedCity, API_KEY
            );

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP Error: " + responseCode);
            }

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

            // Check for error codes in the JSON response
            if (jsonObject.containsKey("cod") && !"200".equals(jsonObject.get("cod").toString())) {
                throw new Exception("Error: " + jsonObject.get("message").toString());
            }

            JSONObject main = (JSONObject) jsonObject.get("main");
            JSONObject weather = (JSONObject) ((java.util.List<?>) jsonObject.get("weather")).get(0);

            // Update labels
            double temp = Double.parseDouble(main.get("temp").toString());
            long humidity = Long.parseLong(main.get("humidity").toString());
            String description = (String) weather.get("description");

            temperatureLabel.setText(String.format("Temperature: %.1f°C", temp));
            humidityLabel.setText(String.format("Humidity: %d%%", humidity));
            descriptionLabel.setText("Description: " + description);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error fetching weather data: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Weather_App().setVisible(true);
        });
    }
}
