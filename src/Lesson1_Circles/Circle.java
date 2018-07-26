package Lesson1_Circles;

import java.awt.*;

public class Circle extends Sprite {
    private final Color color;
    private float vx;
    private float vy;

    Circle() {
        halfHeight = 20 + (float) (Math.random() * 50);
        halfWidth = halfHeight;
        vx = 150 + (float) (Math.random() * 200);
        vy = 150 + (float) (Math.random() * 200);
        color = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        centerX += vx * deltaTime;
        centerY += vy * deltaTime;

        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vx = -vx;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vx = -vx;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vy = -vy;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vy = -vy;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }
}