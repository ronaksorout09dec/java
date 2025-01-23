import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;

public class VirtualEcosystem extends JPanel implements Runnable {
    private CopyOnWriteArrayList<Organism> organisms = new CopyOnWriteArrayList<>();
    private NeuralNetwork weatherSystem = new NeuralNetwork(4, 8, 4);
    private double[] environmentFactors = new double[4]; // temperature, humidity, radiation, resources

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Evolving Ecosystem");
        VirtualEcosystem ecosystem = new VirtualEcosystem();
        frame.add(ecosystem);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(ecosystem).start();
    }

    @Override
    public void run() {
        while (true) {
            updateEnvironment();
            updateOrganisms();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Organism {
        Vector2D position, velocity;
        NeuralNetwork brain;
        DNA genetics;
        double energy = 100;
        Color color;

        Organism(Point location) {
            this.position = new Vector2D(location.x, location.y);
            this.velocity = new Vector2D(0, 0);
            this.brain = new NeuralNetwork(12, 16, 8);
            this.genetics = new DNA();
            this.color = new Color(
                    (int)(genetics.genes[0] * 255),
                    (int)(genetics.genes[1] * 255),
                    (int)(genetics.genes[2] * 255)
            );
        }

        void update() {
            // Gather inputs: environment, nearby organisms, current state
            double[] inputs = gatherInputs();
                        double[] outputs = brain.compute(inputs);
            
                        // Update velocity based on neural network outputs
                        velocity.x += outputs[0] - outputs[1];
                        velocity.y += outputs[2] - outputs[3];
                        velocity.limit(genetics.genes[3] * 10); // Max speed based on genetics
            
                        // Update position
                        position.add(velocity);
                        position.wrap(WIDTH, HEIGHT);
            
                        // Metabolism and energy consumption
                        energy -= genetics.genes[4] * 0.1;
            
                        // Reproduction
                        if (energy > 150 && Math.random() < genetics.genes[5] * 0.1) {
                            reproduce();
                        }
            
                        // Death
                        if (energy <= 0) {
                            organisms.remove(this);
                        }
                    }
            
                    void reproduce() {
                        Organism child = new Organism(new Point((int) position.x, (int) position.y));
                        child.genetics = genetics.mutate();
                        child.energy = energy * 0.3;
                        energy *= 0.7;
                        organisms.add(child);
                    }
                }
            
                private class NeuralNetwork {
                    double[][] weights1, weights2;
                    double[] bias1, bias2;
            
                    NeuralNetwork(int inputs, int hidden, int outputs) {
                        weights1 = new double[hidden][inputs];
                        weights2 = new double[outputs][hidden];
                        bias1 = new double[hidden];
                        bias2 = new double[outputs];
                        randomize();
                    }
            
                    void randomize() {
                        Random r = new Random();
                        for (int i = 0; i < weights1.length; i++) {
                            for (int j = 0; j < weights1[0].length; j++) {
                                weights1[i][j] = r.nextGaussian();
                            }
                        }
                        for (int i = 0; i < weights2.length; i++) {
                            for (int j = 0; j < weights2[0].length; j++) {
                                weights2[i][j] = r.nextGaussian();
                            }
                        }
                        for (int i = 0; i < bias1.length; i++) {
                            bias1[i] = r.nextGaussian();
                        }
                        for (int i = 0; i < bias2.length; i++) {
                            bias2[i] = r.nextGaussian();
                        }
                    }
            
                    double[] compute(double[] inputs) {
                        // Hidden layer
                        double[] hidden = new double[weights1.length];
                        for (int i = 0; i < hidden.length; i++) {
                            double sum = bias1[i];
                            for (int j = 0; j < inputs.length; j++) {
                                sum += weights1[i][j] * inputs[j];
                            }
                            hidden[i] = tanh(sum);
                        }
            
                        // Output layer
                        double[] outputs = new double[weights2.length];
                        for (int i = 0; i < outputs.length; i++) {
                            double sum = bias2[i];
                            for (int j = 0; j < hidden.length; j++) {
                                sum += weights2[i][j] * hidden[j];
                            }
                            outputs[i] = tanh(sum);
                        }
            
                        return outputs;
                    }
            
                    double tanh(double x) {
                        return Math.tanh(x);
                    }
                }
            
                private class DNA {
                    double[] genes;
            
                    DNA() {
                        genes = new double[10];
                        Random r = new Random();
                        for (int i = 0; i < genes.length; i++) {
                            genes[i] = r.nextDouble();
                        }
                    }
            
                    DNA mutate() {
                        DNA child = new DNA();
                        for (int i = 0; i < genes.length; i++) {
                            child.genes[i] = genes[i];
                            if (Math.random() < 0.1) {
                                child.genes[i] += (Math.random() - 0.5) * 0.2;
                                child.genes[i] = Math.max(0, Math.min(1, child.genes[i]));
                            }
                        }
                        return child;
                    }
                }
            
                private class Vector2D {
                    double x, y;
            
                    Vector2D(double x, double y) {
                        this.x = x;
                        this.y = y;
                    }
            
                    void add(Vector2D v) {
                        x += v.x;
                        y += v.y;
                    }
            
                    void limit(double max) {
                        double mag = Math.sqrt(x * x + y * y);
                        if (mag > max) {
                            x *= max / mag;
                            y *= max / mag;
                        }
                    }
            
                    void wrap(int width, int height) {
                        x = (x + width) % width;
                        y = (y + height) % height;
                    }
                }
            
                private void updateEnvironment() {
                    // Neural network controls environmental changes
                    double[] inputs = {
                            Math.sin(System.currentTimeMillis() / 10000.0),
                            Math.cos(System.currentTimeMillis() / 15000.0),
                            organisms.size() / 100.0,
                            Math.random()
                    };
                    environmentFactors = weatherSystem.compute(inputs);
                }
            
                public double[] gatherInputs() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'gatherInputs'");
                }
            
                private void updateOrganisms() {
        if (organisms.size() < 10 && Math.random() < 0.1) {
            organisms.add(new Organism(new Point(
                    (int)(Math.random() * WIDTH),
                    (int)(Math.random() * HEIGHT)
            )));
        }

        for (Organism o : organisms) {
            o.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(
                (float)Math.max(0, Math.min(1, environmentFactors[0])),
                (float)Math.max(0, Math.min(1, environmentFactors[1])),
                (float)Math.max(0, Math.min(1, environmentFactors[2]))
        ));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (Organism o : organisms) {
            g.setColor(o.color);
            g.fillOval(
                    (int)o.position.x - 5,
                    (int)o.position.y - 5,
                    10, 10
            );
        }

        g.setColor(Color.BLACK);
        g.drawString("Organisms: " + organisms.size(), 10, 20);
    }
}