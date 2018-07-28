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
    private GameObject[] gameObjects = new GameObject[4];
    private int gameObjectsCount;

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles on the water");

        GameCanvas gameCanvas = new GameCanvas(this);
        gameCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addGameObject(new Circle(e.getX(), e.getY()));
                if (e.getButton() == MouseEvent.BUTTON3)
                    delGameObject();
            }
        });

        add(gameCanvas, BorderLayout.CENTER);
        initGame();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }

    private void initGame() {
        addGameObject(new Background());
        while (gameObjectsCount < gameObjects.length) {
            gameObjects[gameObjectsCount++] = new Circle();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].render(canvas, g);
        }
    }

    private void addGameObject(GameObject ob) {
        if (gameObjectsCount >= gameObjects.length) {
            GameObject[] newGameObjects = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects, 0, newGameObjects, 0, gameObjects.length);
            gameObjects = newGameObjects;
        }
        gameObjects[gameObjectsCount++] = ob;
    }

    private void delGameObject() {
        if (gameObjectsCount > 1) {
            gameObjects[--gameObjectsCount] = null;
        }
    }

}