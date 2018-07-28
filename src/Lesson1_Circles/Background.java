package Lesson1_Circles;

import java.awt.*;

public class Background implements GameObject {
    private Color color;
    private float time;
    private final float AMPLITUDE = 255f / 2f;

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE * (float)(1 + Math.sin(time)));
        int green = Math.round(AMPLITUDE * (float)(1 + Math.sin(time * 2)));
        int blue = Math.round(AMPLITUDE * (float)(1 + Math.sin(time * 3)));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }
}