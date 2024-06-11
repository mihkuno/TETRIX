package screens;

import processing.core.PApplet;
import processing.sound.SoundFile;


public class Main extends PApplet {

    private Menu menu;
    private Game game;
    private SoundFile clickSound;
    private SoundFile menuSound;

    private int clearAnimation = 255;
    public static String SCREEN = "menu";
 
    @Override
	public void settings(){
		this.size(700, 600);
        this.smooth();
	}

    @Override
    public void setup() {
        this.menu = new Menu(this);
        this.game = new Game(this);

        this.clickSound = new SoundFile(this, "assets/audios/m_click.mp3");
        this.menuSound = new SoundFile(this, "assets/audios/bg_wires.mp3");
        
        // this.menuSound.play();
    }

	
    @Override
	public void draw(){
        this.clickSound.amp(1.0f);
        
        switch(SCREEN) {
            // static screens
            case "menu":
                this.menu.render();
                break;
            case "game":
                this.game.render();
                break;
        
            // animated transition between screens
            case "exiting_menu":
                this.exiting_menu();
                break;
            case "entering_menu":
                this.entering_menu();
                break;
        }
	}
	    
    @Override
	public void mousePressed(){
        if (SCREEN == "menu") {
            if (this.menu.startButton.isMouseHovering()) {
                this.clickSound.play();
                SCREEN = "exiting_menu";
            }
        }
	}

    @Override
    public void keyPressed() {
        this.game.handleKeyPress(this.keyCode);
    }

    @Override
    public void mouseMoved() {

    }

    @Override
    public void mouseReleased() {
        
    }

    private void exiting_menu() {
        this.menu.startButton.stopAnimation();
        this.menu.titleText.setY(this.menu.titleText.getY() - 1);
        this.menu.startButton.setY(this.menu.startButton.getY() - 1);
        
        this.clearAnimation -= 10;
        this.menu.titleText.setOpacity(this.clearAnimation);
        this.menu.startButton.setOpacity(this.clearAnimation);
        this.menuSound.amp(this.clearAnimation / 255.0f);
        this.tint(255, this.clearAnimation);

        if (this.clearAnimation <= -10) {
            this.menuSound.pause();
            Main.SCREEN = "game";
        }
        else {
            this.menu.render();
        }
    }


    private void entering_menu() {
        this.menu.titleText.setY(this.menu.titleText.getY() + 1);
        this.menu.startButton.setY(this.menu.startButton.getY() + 1);
        
        this.clearAnimation += 10;
        this.menu.titleText.setOpacity(this.clearAnimation);
        this.menu.startButton.setOpacity(this.clearAnimation);
        this.menuSound.amp(this.clearAnimation / 255.0f);
        this.tint(255, this.clearAnimation);

        if (this.clearAnimation >= 255) {
            this.menuSound.play();
            this.menu.startButton.startAnimation();
            Main.SCREEN = "menu";
        }
        else {
            this.menu.render();
        }
    }


	public static void main(String[] args){
		String[] processing = {"Main"};
		Main main = new Main();
		PApplet.runSketch(processing, main);
	}
}