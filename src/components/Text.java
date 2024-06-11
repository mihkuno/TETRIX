package components;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class Text {
    protected PApplet sketch;
    protected float x, y;
    protected float r, g, b;
    protected float opacity;  // Opacity attribute
    protected float textSize;
    protected String content;
    protected PFont font;
    protected int hAlign;
    protected int vAlign;

    public Text(PApplet sketch) {
        this.sketch = sketch;
        this.x = 0;
        this.y = 0;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.opacity = 255;  // Default to fully opaque
        this.textSize = 12;
        this.content = "Text";
        this.font = sketch.createFont("Arial", textSize);  // Default font
        this.hAlign = PConstants.LEFT;
        this.vAlign = PConstants.TOP;
    }

    public void render() {
        sketch.fill(r, g, b, opacity);  // Use opacity
        sketch.textFont(font, textSize);  // Set the font and size
        sketch.textAlign(hAlign, vAlign);
        sketch.text(content, x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setTextColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        this.font = sketch.createFont(font.getName(), textSize);  // Update the font size
    }

    public void setFont(String fontPath, float textSize) {
        this.font = sketch.createFont(fontPath, textSize);
        this.textSize = textSize;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTextAlign(int hAlign, int vAlign) {
        this.hAlign = hAlign;
        this.vAlign = vAlign;
    }

    public void setOpacity(float opacity) {
        if (opacity < 0) {
            opacity = 0;
        } else if (opacity > 255) {
            opacity = 255;
        }
        this.opacity = opacity;
    }

    // Method to get the width of the text string
    public float getTextWidth() {
        sketch.textFont(font, textSize);  // Ensure the correct font and size are set
        return sketch.textWidth(content);
    }

    // Method to get the height of the text string
    public float getTextHeight() {
        sketch.textFont(font, textSize);  // Ensure the correct font and size are set
        return sketch.textAscent() + sketch.textDescent();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
