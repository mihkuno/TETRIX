package components;

import processing.core.PApplet;

public class Mechanic extends Text {

    public Mechanic(PApplet sketch) {
        super(sketch);
    }

    public void render(int score, int level, int combo, int lines, int gainUp) {
        
        this.sketch.fill(209, 204, 192);
        this.sketch.rect(20, 30, 130, 240, 5);
        this.sketch.fill(0);
        this.sketch.textSize(16);
        this.sketch.textAlign(PApplet.LEFT, PApplet.TOP);
        this.sketch.text("Score: " + score, 40, 20+40);
        this.sketch.text("Level: " + level, 40, 60+40);
        this.sketch.text("Combo: " + combo, 40, 100+40);
        this.sketch.text("Lines: " + lines, 40, 140+40);
        this.sketch.text("Gain Up: " + gainUp, 40, 180+40);
    }
        
}
