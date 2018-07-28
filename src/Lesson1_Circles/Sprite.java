package Lesson1_Circles;

abstract class Sprite {
    protected float centerX;
    protected float centerY;
    protected float halfWidth;
    protected float halfHeight;

    protected float getLeft() {
        return centerX - halfWidth;
    }
    protected void setLeft(float left) {
        centerX = left + halfWidth;
    }
    protected float getRight() {
        return centerX + halfWidth;
    }
    protected void setRight(float right) {
        centerX = right - halfWidth;
    }
    protected float getTop() {
        return centerY - halfHeight;
    }
    protected void setTop(float top) {
        centerY = top + halfHeight;
    }
    protected float getBottom() {
        return centerY + halfHeight;
    }
    protected void setBottom(float bottom) {
        centerY = bottom - halfHeight;
    }
    protected float getWidth() {
        return halfWidth * 2;
    }
    protected float getHeight() {
        return halfHeight * 2;
    }

}