package components;

import processing.core.PApplet;

public class StartButton extends Button {

    private float scale = 0.15f;
    private boolean animation = true;
    private boolean direction = true;
    private final float w = 220;
    private final float h = 65;

    public StartButton(PApplet sketch) {
        super(sketch);
        this.radius = 40;
        this.width = this.w;
        this.height = this.h;
        this.x = (this.sketch.width / 2 - this.width / 2);
        this.y = (this.sketch.height / 2 - this.height / 2 + 50);
        this.content = "Start";
        this.setBackground(46, 204, 113);        
        this.setTextColor(250, 250, 250);
        this.setFont("assets/fonts/TrulyMadlyDpad-a72o.ttf", 30);
    }
    
    @Override
    public void render() { 

        if (this.animation) {
            this.animate();
        }

        super.render();

    }

    public void animate() {
        this.x = (this.sketch.width / 2 - this.width / 2);
        this.y = (this.sketch.height / 2 - this.height / 2 + 50);

        if (direction == true) {
            this.scale = PApplet.lerp(this.scale, 0.2f, 0.05f);
        }
        else {
            this.scale = PApplet.lerp(this.scale, 0.05f, 0.05f);
        }

        if (this.scale >= 0.18) {
            direction = false;
        }
        else if (this.scale <= 0.07) {
            direction = true;
        }
        
        this.width = this.w * scale + this.w;
        this.height = this.h * scale +this.h;
    }
    
    public void startAnimation() {
        this.animation = true;
    }

    public void stopAnimation() {
        this.animation = false;
    }

    public boolean isAnimation() {
        return this.animation;
    }
}
