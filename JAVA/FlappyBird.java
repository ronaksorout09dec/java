import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

// Explicitly import the Rectangle class from java.awt
import java.awt.Rectangle;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private int birdY = 300;
    private int birdVelocity = 0;
    private final int GRAVITY = 1;
    private final int JUMP_FORCE = -15;
    private final int BIRD_SIZE = 40;
    private boolean isGameOver = false;
    private int score = 0;
    
    private ArrayList<Rectangle> pipes = new ArrayList<>();
    private final int PIPE_WIDTH = 70;
    private final int PIPE_GAP = 200;
    private final int PIPE_SPEED = 5;
    
    public FlappyBird() {
        Timer timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        
        // Initialize first pipe
        addPipe();
    }
    
    private void addPipe() {
        Random rand = new Random();
        int height = rand.nextInt(300) + 100;
        pipes.add(new Rectangle(800, 0, PIPE_WIDTH, height)); // Top pipe
        pipes.add(new Rectangle(800, height + PIPE_GAP, PIPE_WIDTH, 600 - height - PIPE_GAP)); // Bottom pipe
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw sky
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw bird
        g.setColor(Color.YELLOW);
        g.fillOval(100, birdY, BIRD_SIZE, BIRD_SIZE);
        
        // Draw pipes
        g.setColor(Color.GREEN);
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }
        
        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Score: " + score, 20, 50);
        
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Game Over!", 250, 300);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            // Bird physics
            birdVelocity += GRAVITY;
            birdY += birdVelocity;
            
            // Move pipes
            for (Rectangle pipe : pipes) {
                pipe.x -= PIPE_SPEED;
            }
            
            // Add new pipes
            if (pipes.size() > 0 && pipes.get(pipes.size() - 1).x < 600) {
                addPipe();
            }
            
            // Remove off-screen pipes
            pipes.removeIf(pipe -> pipe.x + pipe.width < 0);
            
            // Check collisions
            Rectangle bird = new Rectangle(100, birdY, BIRD_SIZE, BIRD_SIZE);
            for (Rectangle pipe : pipes) {
                if (bird.intersects(pipe)) {
                    isGameOver = true;
                }
            }
            
            // Check if bird is out of bounds
            if (birdY < 0 || birdY > getHeight() - BIRD_SIZE) {
                isGameOver = true;
            }
            
            // Update score
            if (!pipes.isEmpty() && pipes.get(0).x == 95) {
                score++;
            }
        }
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver) {
            birdVelocity = JUMP_FORCE;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FlappyBird());
        frame.setVisible(true);
    }
}     