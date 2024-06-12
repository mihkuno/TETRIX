package components;

import processing.core.PApplet;

public class TitleText extends Text {
    public TitleText(PApplet sketch) {
        super(sketch);
        this.setTextSize(125);
        this.setContent("Tetrix");
        this.setTextColor(0, 0, 0);
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
        this.setX(this.sketch.width/2 - this.getTextWidth()/2);
        this.setY(this.sketch.height/2 + this.getTextHeight()/2 - 200 + 50);
    }

    @Override
    public void setTextSize(float size) {
        this.setFont("assets/fonts/JoystickBold-62LA.ttf", size);
        this.setX(this.sketch.width/2 - this.getTextWidth()/2);
        this.setY(this.sketch.height/2 + this.getTextHeight()/2 - 200 + 50);
    }
}
