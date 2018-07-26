package Lesson1_Circles;

import java.awt.*;

public class Background extends Sprite {
    private Color color;
    private float time;

    Background() {
        color = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
        time = 0;
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        time += deltaTime;
        if (time > 0.5f) {
            color = new Color(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            time = 0;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        centerX = (float) canvas.getWidth() / 2f;
        centerY = (float) canvas.getHeight() / 2f;
        halfWidth = centerX;
        halfHeight = centerY;
        g.setColor(color);
        g.fillRect((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }
}