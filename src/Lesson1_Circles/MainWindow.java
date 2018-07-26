package Lesson1_Circles;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final int POS_X = 450;
    private static final int POS_Y = 150;
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private Sprite[] sprites = new Sprite[10];

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles on the water");

        GameCanvas gameCanvas = new GameCanvas(this);

        add(gameCanvas, BorderLayout.CENTER);
        initGame();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    private void initGame() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Circle();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (Sprite sprite : sprites) {
            sprite.update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(canvas, g);
        }
    }
}