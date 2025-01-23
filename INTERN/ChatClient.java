package INTERN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

@SuppressWarnings("unused")
public class ChatClient extends JFrame {
    private String serverAddress;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextField messageField;
    private JTextArea messageArea;
    private JButton sendButton;
    private String name;

    public ChatClient() {
        super("Chat Application");

        // Ask for username
        serverAddress = "localhost";
        name = JOptionPane.showInputDialog(
                this,
                "Enter your name:",
                "Name Selection",
                JOptionPane.PLAIN_MESSAGE
        );

        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty. Exiting...");
            System.exit(1);
        }

        // Set up the UI
        setTitle("Chat Application - " + name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));

        // Message area (display incoming messages)
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);

        // Input area (to type and send messages)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        
        messageField = new JTextField();
        messageField.setEditable(false); // Disable until the connection is established
        sendButton = new JButton("Send");
        sendButton.setEnabled(false); // Disable until the connection is established

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Send message on button click or Enter key
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        // Establish connection
        try {
            connectToServer();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage());
            System.exit(1);
        }

        // Set frame properties
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connectToServer() throws IOException {
        socket = new Socket(serverAddress, 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process messages from server
        new Thread(() -> {
            try {
                while (true) {
                    String line = in.readLine();
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        messageField.setEditable(true);
                        sendButton.setEnabled(true);
                    } else if (line.startsWith("MESSAGE")) {
                        messageArea.append(line.substring(8) + "\n");
                        messageArea.setCaretPosition(messageArea.getDocument().getLength());
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
            out.println(message);
            messageField.setText(""); // Clear the message field
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}
