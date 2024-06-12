package components;

import processing.core.PApplet;
import processing.core.PConstants;
import screens.Main;

public class Button extends Text {
    protected float width, height, radius;
    protected float backgroundRed, backgroundGreen, backgroundBlue;
    protected float backgroundOpacity;  // Opacity attribute
    protected float padding = 10; // Padding for hover detection

    public Button(PApplet sketch) {
        super(sketch);
        this.width = 100;
        this.height = 50;
        this.radius = 10;
        this.backgroundRed = 255;
        this.backgroundGreen = 255;
        this.backgroundBlue = 255;
        this.backgroundOpacity = 255; // Default to fully opaque
        this.setTextAlign(PConstants.CENTER, PConstants.CENTER);
        this.setContent("Button");
    }

    @Override
    public void render() {

        // Draw the button
        sketch.noStroke();
        sketch.fill(backgroundRed, backgroundGreen, backgroundBlue, backgroundOpacity);  // Use opacity
        sketch.rect(x, y, width, height, radius);

        // Adjust text position to center within the button
        float textX = x + width / 2;
        float textY = y + height / 2;

        // Draw the text
        sketch.fill(r, g, b, backgroundOpacity);
        sketch.textFont(font, textSize);
        sketch.textAlign(hAlign, vAlign);
        sketch.text(content, textX, textY);
    }

    public boolean isMouseHovering() {
        // Check if the mouse is within the rectangular bounds plus padding
        if (this.sketch.mouseX >= x - padding && this.sketch.mouseX <= x + width + padding && this.sketch.mouseY >= y - padding && this.sketch.mouseY <= y + height + padding) {
            // Further check for rounded corners
            if (this.sketch.mouseX < x + radius && this.sketch.mouseY < y + radius) {
                // Top-left corner
                return dist(this.sketch.mouseX, this.sketch.mouseY, x + radius, y + radius) <= radius + padding;
            } else if (this.sketch.mouseX > x + width - radius && this.sketch.mouseY < y + radius) {
                // Top-right corner
                return dist(this.sketch.mouseX, this.sketch.mouseY, x + width - radius, y + radius) <= radius + padding;
            } else if (this.sketch.mouseX < x + radius && this.sketch.mouseY > y + height - radius) {
                // Bottom-left corner
                return dist(this.sketch.mouseX, this.sketch.mouseY, x + radius, y + height - radius) <= radius + padding;
            } else if (this.sketch.mouseX > x + width - radius && this.sketch.mouseY > y + height - radius) {
                // Bottom-right corner
                return dist(this.sketch.mouseX, this.sketch.mouseY, x + width - radius, y + height - radius) <= radius + padding;
            } else {
                // Within the rectangle but not in the rounded corners
                return true;
            }
        }
        return false;
    }

    private float dist(float x1, float y1, float x2, float y2) {
        return PApplet.sqrt(PApplet.pow(x2 - x1, 2) + PApplet.pow(y2 - y1, 2));
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setBackground(float r, float g, float b) {
        this.backgroundRed = r;
        this.backgroundGreen = g;
        this.backgroundBlue = b;
    }

    public void setOpacity(float opacity) {
        if (opacity < 0) {
            opacity = 0;
        } else if (opacity > 255) {
            opacity = 255;
        }
        this.backgroundOpacity = opacity;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getRadius() {
        return this.radius;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

}
