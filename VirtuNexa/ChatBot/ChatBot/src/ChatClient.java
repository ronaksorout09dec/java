import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class ChatClient extends JFrame {
    private String serverAddress = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextField messageField;
    private JTextArea messageArea;
    private JButton sendButton, fileButton;
    private String name;

    public ChatClient() {
        super("Chat Application");
        name = JOptionPane.showInputDialog(this, "Enter your name:", "Name Selection", JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty. Exiting...");
            System.exit(1);
        }
        setTitle("Chat Application - " + name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        messageField = new JTextField();
        sendButton = new JButton("Send");
        fileButton = new JButton("Send File");
        sendButton.setEnabled(false);
        fileButton.setEnabled(false);
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(fileButton, BorderLayout.WEST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        fileButton.addActionListener(e -> selectAndSendFile());

        try {
            connectToServer();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage());
            System.exit(1);
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connectToServer() throws IOException {
        socket = new Socket(serverAddress, 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {
            try {
                while (true) {
                    String line = in.readLine();
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        messageField.setEditable(true);
                        sendButton.setEnabled(true);
                        fileButton.setEnabled(true);
                    } else if (line.startsWith("MESSAGE")) {
                        messageArea.append(line.substring(8) + "\n");
                    } else if (line.startsWith("FILE_TRANSFER")) {
                        receiveFile(line.substring(13));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Connection lost: " + e.getMessage());
                System.exit(1);
            }
        }).start();
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            out.println("MESSAGE " + name + ": " + message);
            messageField.setText("");
        }
    }

    private void selectAndSendFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                sendFile(fileChooser.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error sending file: " + e.getMessage());
            }
        }
    }

    private void sendFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            String encodedFile = Base64.getEncoder().encodeToString(buffer);
            out.println("FILE_TRANSFER " + file.getName() + ";" + encodedFile);
        }
    }

    private void receiveFile(String data) {
        String[] parts = data.split(";", 2);
        if (parts.length < 2) return;
        String fileName = parts[0];
        byte[] fileBytes = Base64.getDecoder().decode(parts[1]);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(fileName));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile())) {
                fos.write(fileBytes);
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}
