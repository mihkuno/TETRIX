package screens;

import screens.Main;
import components.Button;
import components.Mechanic;
import components.Text;
import components.TitleText;
import processing.core.PApplet;
import processing.core.PConstants;

public class Ranking {
    
    private PApplet sketch;
    private TitleText title;
    private Button menuButton;
    private String input;
    private String[][] leaderboard; 
    private Text[] nameTexts;
    private Text[] scoreTexts;
    private boolean editing;
    private boolean viewing;
    private boolean typing;

    public Ranking(PApplet sketch) {
        this.sketch = sketch;
        this.editing = false;
        this.viewing = false;
        this.typing = false;
        this.input = "type your name...";
        this.leaderboard = Mechanic.read();

        // Initialize Menu Button
        this.menuButton = new Button(this.sketch);
        this.menuButton.setContent("Back to Menu");
        this.menuButton.setBackground(100, 100, 100);
        this.menuButton.setTextColor(250, 250, 250);
        this.menuButton.setTextSize(16);
        this.menuButton.setWidth(250);
        this.menuButton.setHeight(40);
        this.menuButton.setX(this.sketch.width / 2 - this.menuButton.getWidth() / 2);
        this.menuButton.setY(this.sketch.height - this.menuButton.getHeight() - 20);
        
        // Initialize Title
        this.title = new TitleText(this.sketch);
        this.title.setTextColor(100, 100, 100);
        this.title.setContent("Leaderboard");
        this.title.setTextSize(60);
        this.title.setX(this.sketch.width / 2 - this.title.getTextWidth() / 2);
        this.title.setY(75);

        // Initialize Text arrays for names and scores
        initTextArrays();
    }

    private void initTextArrays() {
        int numEntries = leaderboard.length;
        nameTexts = new Text[numEntries];
        scoreTexts = new Text[numEntries];

        for (int i = 0; i < numEntries; i++) {
            nameTexts[i] = new Text(this.sketch);
            nameTexts[i].setContent(leaderboard[i][0]);
            nameTexts[i].setTextSize(24);
            nameTexts[i].setTextColor(0, 0, 0);
            nameTexts[i].setX(this.sketch.width / 4);
            nameTexts[i].setY(200 + i * 30);

            scoreTexts[i] = new Text(this.sketch);
            scoreTexts[i].setContent(leaderboard[i][1]);
            scoreTexts[i].setTextSize(24);
            scoreTexts[i].setTextColor(0, 0, 0);
            scoreTexts[i].setX(this.sketch.width / 2 + 100);
            scoreTexts[i].setY(200 + i * 30);
        }
    }

    public void handleMousePress() {
        if (this.menuButton.isMouseHovering()) {
            Main.SCREEN = "entering_menu";
        }
    }

    public void handleKeyPress() {

        // clear the placeholder when typing
        if (!this.typing) {
            this.input = "";
            this.typing = true;
        }

        if (this.editing) {

            // enter is pressed
            if (this.sketch.key == PConstants.ENTER) {
                this.typing = false;

                // if empty put the placeholder back
                if (this.input.length() == 0) {
                    this.input = "type your name...";
                } 
                else {
                    Mechanic.updateLastPlayerName(this.input);
                    Main.SCREEN = "ranking_view";
                }
            } 

            // backspace is pressed
            else if (this.sketch.key == PConstants.BACKSPACE) {
                if (this.input.length() > 0) { // remove the last character
                    this.input = this.input.substring(0, this.input.length() - 1);
                }
            } 
            

            // any other key is pressed
            else {
                this.input += this.sketch.key;
            }
        }
    }

    public void handleMouseHover() {
        if (this.menuButton.isMouseHovering()) {
            this.sketch.cursor(PConstants.HAND);
        } else {
            this.sketch.cursor(PConstants.ARROW);
        }
    }

    public void view() {
        if (!this.viewing) {
            this.editing = false;
            Mechanic.filterTopTen();
            this.leaderboard = Mechanic.read();
            initTextArrays(); // reinitialize the text arrays
        }
        this.viewing = true;
    }

    public void edit() {
        if (!this.editing) {
            this.viewing = false;
            this.leaderboard = Mechanic.read();
            initTextArrays(); // reinitialize the text arrays
        }
        this.editing = true;
    }

    public void render() {
        this.title.render();
        this.menuButton.render();

        for (int i = 0; i < nameTexts.length; i++) {
            if (this.editing && i == nameTexts.length - 1) {
                nameTexts[i].setContent(this.input);
                nameTexts[i].setTextColor(150, 150, 150);
            } else {
                nameTexts[i].setTextColor(0, 0, 0); // set the text color to black when viewing
            }
            nameTexts[i].render();
        }

        for (Text scoreText : scoreTexts) {
            scoreText.render();
        }
    }
}
