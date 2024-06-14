package screens;

import components.Button;
import components.TitleText;
import processing.core.PApplet;
import processing.core.PConstants;

public class GameOver {
    
    private PApplet sketch;
    private TitleText title;
    private Button menuButton;
    private Button rankButton;

    public GameOver(PApplet sketch) {
        this.sketch = sketch;
        this.menuButton = new Button(this.sketch);
        this.menuButton.setContent("Back to Menu");
        this.menuButton.setBackground(100,100,100);
        this.menuButton.setTextColor(250, 250, 250);
        this.menuButton.setTextSize(16);
        this.menuButton.setWidth(250);
        this.menuButton.setHeight(40);
        this.menuButton.setX(this.sketch.width / 2 - this.menuButton.getWidth() / 2 - 15);
        this.menuButton.setY(this.sketch.height / 2 - this.menuButton.getHeight() / 2 + 35);

        this.rankButton = new Button(this.sketch);
        this.rankButton.setContent("View Leaderboard");
        this.rankButton.setBackground(100,100,100);
        this.rankButton.setTextColor(250, 250, 250);
        this.rankButton.setTextSize(16);
        this.rankButton.setWidth(250);
        this.rankButton.setHeight(40);
        this.rankButton.setX(this.sketch.width / 2 - this.rankButton.getWidth() / 2 - 15);
        this.rankButton.setY(this.sketch.height / 2 - this.rankButton.getHeight() / 2 + 100);

        this.title = new TitleText(this.sketch);
        this.title.setTextColor(100,100,100);
        this.title.setContent("Game Over");
        this.title.setTextSize(60);
        this.title.setY(this.title.getY() + 75);
        this.title.setX(this.title.getX() - 15);
    }

    public void handleMousePress() {
        if (this.menuButton.isMouseHovering()) {
            Main.SCREEN = "entering_menu";
        }
        else if (this.rankButton.isMouseHovering()) {
            Main.SCREEN = "ranking_edit";
        }
    }

    public void handleMouseHover() {
        if (this.menuButton.isMouseHovering()) {
            this.sketch.cursor(PConstants.HAND);
        }
        else if (this.rankButton.isMouseHovering()) {
            this.sketch.cursor(PConstants.HAND);
        }
        else {
            this.sketch.cursor(PConstants.ARROW);
        }
    }

    public void render() {
        this.sketch.fill(209, 204, 192, 5);
        this.sketch.rect(30*6, 10*3, 30*10, 30*18);
        this.title.render();
        this.menuButton.render();
        this.rankButton.render();
    }
}
