package Lesson1_Circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private static final int POS_X = 450;
    private static final int POS_Y = 150;
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private Sprite[] sprites = new Sprite[10];
    private int count = 0;

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
        sprites[count++] = new Background();
        while (count < sprites.length) {
            sprites[count++] = new Circle();
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addCircle();
                if (e.getButton() == MouseEvent.BUTTON3)
                    delCircle();
            }
        });
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < count; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < count; i++) {
            sprites[i].render(canvas, g);
        }
    }

    private void growArray() {
        Sprite[] newSprites = new Sprite[(sprites.length) * 2];
        System.arraycopy(sprites, 0, newSprites, 0, sprites.length);
        sprites = newSprites;
    }

    private void addCircle() {
        if (count >= sprites.length) {
            growArray();
        }
        sprites[count++] = new Circle();
    }

    private void delCircle() {
        if (count > 1) {
            sprites[--count] = null;
        }
    }
}