import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("unused")
public class WeatherGUI extends JFrame implements ActionListener {

    private JTextField cityInput;
    private JTextPane weatherDisplay;
    private JButton fetchButton;
    private JLabel weatherIconLabel;
    private JLabel loadingLabel;
    private JPanel mainPanel;

    //Weather Icons Paths
    private final String SUN_ICON = "https://openweathermap.org/img/wn/01d@2x.png";
    private final String CLOUDS_ICON = "https://openweathermap.org/img/wn/03d@2x.png";
    private final String RAIN_ICON = "https://openweathermap.org/img/wn/09d@2x.png";
    private final String SNOW_ICON = "https://openweathermap.org/img/wn/13d@2x.png";
    private final String MIST_ICON = "https://openweathermap.org/img/wn/50d@2x.png";

    public WeatherGUI() {
        setTitle("Weather Information App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel() {
            @SuppressWarnings("deprecation")
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new URL("https://i.pinimg.com/originals/54/9c/91/549c91c984a8c34a93052404d3009833.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Input panel (centered)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setOpaque(false);
        cityInput = new JTextField(15);
        cityInput.setFont(new Font("Arial", Font.PLAIN, 16));
        fetchButton = new JButton("Get Weather");
        fetchButton.setFont(new Font("Arial", Font.BOLD, 16));
        fetchButton.addActionListener(this);
        inputPanel.add(new JLabel("Enter City:"));
        inputPanel.add(cityInput);
        inputPanel.add(fetchButton);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Weather display (using JTextPane for HTML rendering)
        weatherDisplay = new JTextPane();
        weatherDisplay.setContentType("text/html");
        weatherDisplay.setEditable(false);
        weatherDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        weatherDisplay.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(weatherDisplay);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(null); 
        
        // Weather icon label
        weatherIconLabel = new JLabel();
        weatherIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Loading Label
        loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setVisible(false);

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(weatherIconLabel, BorderLayout.EAST);
        mainPanel.add(loadingLabel, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchButton) {
            String city = cityInput.getText();
            loadingLabel.setVisible(true);
            fetchButton.setEnabled(false);

            SwingWorker<WeatherData, Void> worker = new SwingWorker<WeatherData, Void>() {
                @Override
                protected WeatherData doInBackground() throws Exception {
                    return WeatherService.getWeatherData(city);
                }

                @Override
                protected void done() {
                    loadingLabel.setVisible(false);
                    fetchButton.setEnabled(true);
                    try {
                        WeatherData weatherData = get();

                        if (weatherData != null) {
                            String temperature = String.format("%.1f", weatherData.getTemperature());
                            String humidity = String.format("%.1f", weatherData.getHumidity());
                            String windSpeed = String.format("%.1f", weatherData.getWindSpeed());

                            String formattedText = "<html><body style='color: black; text-align: center;'>" +  //Change color here
                                    "<h2 style='font-weight: bold;'>City: " + weatherData.getCity() + "</h2>" +
                                    "<p><b>Description:</b> " + weatherData.getDescription() + "</p>" +
                                    "<p><b>Temperature:</b> " + temperature + " °C</p>" +
                                    "<p><b>Humidity:</b> " + humidity + "%</p>" +
                                    "<p><b>Wind Speed:</b> " + windSpeed + " m/s</p>" +
                                    "</body></html>";
                            weatherDisplay.setText(formattedText);

                            String description = weatherData.getDescription().toLowerCase();
                            String iconUrl = null;
                            if (description.contains("sun")) {
                                iconUrl = SUN_ICON;
                            } else if (description.contains("cloud")) {
                                iconUrl = CLOUDS_ICON;
                            } else if (description.contains("rain")) {
                                iconUrl = RAIN_ICON;
                            } else if (description.contains("snow")) {
                                iconUrl = SNOW_ICON;
                            } else if (description.contains("mist")) {
                                iconUrl = MIST_ICON;
                            }
                            if(iconUrl!=null) {
                                setWeatherIcon(iconUrl);
                            } else {
                                weatherIconLabel.setIcon(null);
                            }


                        } else {
                            weatherDisplay.setText("Could not retrieve weather data for " + city);
                            weatherIconLabel.setIcon(null);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        weatherDisplay.setText("Error fetching weather data.");
                        weatherIconLabel.setIcon(null);
                    }
                }
            };
            worker.execute();
        }
    }

    private void setWeatherIcon(String iconUrl) {
        try {

            SwingWorker<ImageIcon, Void> imageLoader = new SwingWorker<ImageIcon, Void>() {
                @SuppressWarnings("deprecation")
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    URL url = new URL(iconUrl);
                    BufferedImage image = ImageIO.read(url);
                    return new ImageIcon(image);
                }

                @Override
                protected void done() {
                    try {
                        ImageIcon icon = get();
                        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        weatherIconLabel.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                        weatherIconLabel.setText("Error loading icon");
                    }
                }
            };
            imageLoader.execute();
        } catch (Exception e) {
            e.printStackTrace();
            weatherIconLabel.setText("Error loading icon");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherGUI::new);
    }
}
