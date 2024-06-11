package screens;

import components.StartButton;
import components.TitleText;
import processing.core.PApplet;
import processing.core.PImage;

public class Menu {
    public StartButton startButton;
    public PImage backgroundImage;
    public TitleText titleText;

    private PApplet sketch;
    private boolean backgroundAnimation = true;
    private float backgroundImageY = -75.0f;
    private boolean backgroundImageDirection = true;
    

    public Menu(PApplet sketch) {
        this.sketch = sketch;
        this.sketch.windowTitle("Tetrix - Menu");
        this.startButton = new StartButton(sketch); 
        this.titleText = new TitleText(sketch);
        this.backgroundImage = this.sketch.loadImage("assets/images/bg_menu.png");
    }

    public void stopBackgroundAnimation() {
        this.backgroundAnimation = false;
    }

    public void startBackgroundAnimation() {
        this.backgroundAnimation = true;
    }

    public void render() {

        if (this.backgroundAnimation) {

            if (this.backgroundImageY >= 0) {
                this.backgroundImageDirection = false;
            }
            else if (this.backgroundImageY <= -150) {
                this.backgroundImageDirection = true;
            }
    
            if (this.backgroundImageDirection) {
                this.backgroundImageY += 0.24f;
            }
            else {
                this.backgroundImageY -= 0.24f;
            }    
        }
        
        this.sketch.background(246, 241, 226);
        this.sketch.image(this.backgroundImage, -125, this.backgroundImageY, this.sketch.width + 250, this.sketch.height + 150);
        this.titleText.render();
        this.startButton.render();
    }
}
