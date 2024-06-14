package screens;

import java.util.Random;

import components.Mechanic;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.sound.SoundFile;
import tetronimos.*;

public class Game {
    private PApplet sketch;
    private Grid grid;
    private Block player;
    private int interval = 700; // Initial interval in milliseconds
    private long lastTime; // Variable to store the last time the block moved down

    private SoundFile moveSound;
    private SoundFile rotateSound;
    private SoundFile dropSound;
    private SoundFile firstComboSound;
    private SoundFile secondComboSound;
    private SoundFile thirdComboSound;
    private SoundFile fourthComboSound;

    // score mechanics
    private Mechanic mechanic;

    public Game(PApplet sketch) {
        this.sketch   = sketch;
        this.grid     = new Grid(sketch);
        this.mechanic = new Mechanic(sketch);
        this.player   = this.getRandomBlock();
        this.lastTime = System.currentTimeMillis(); // Initialize lastTime
        
        this.moveSound        = new SoundFile(this.sketch, "assets/audios/fx_move.mp3");
        this.rotateSound      = new SoundFile(this.sketch, "assets/audios/fx_rotate.mp3");
        this.dropSound        = new SoundFile(this.sketch, "assets/audios/fx_lnd01.mp3");
        this.firstComboSound  = new SoundFile(this.sketch, "assets/audios/fx_lne01.mp3");
        this.secondComboSound = new SoundFile(this.sketch, "assets/audios/fx_lne02.mp3");
        this.thirdComboSound  = new SoundFile(this.sketch, "assets/audios/fx_lne03.mp3");
        this.fourthComboSound = new SoundFile(this.sketch, "assets/audios/fx_lne04.mp3");   

        this.mechanic.setScore(0);
        this.mechanic.setLevel(1);
        this.mechanic.setCombo(0);
        this.mechanic.setLines(0);
        this.mechanic.setGainUp(5);
    }

    public void render() {
        this.sketch.background(246, 241, 226);
        this.sketch.windowTitle("Tetrix - Game");   
        this.mechanic.render(
            this.mechanic.getScore(), 
            this.mechanic.getLevel(), 
            this.mechanic.getCombo(), 
            this.mechanic.getLines(), 
            this.mechanic.getGainUp()
        );
        this.grid.render();
        this.player.render();        
        this.update();
    }

    public void update() {
        // Check if it's time to move the block down based on the interval
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > interval) {
            this.handleMoveDown();
            lastTime = currentTime; // Update lastTime
        }
    }

    public void handleKeyPress(int keyCode) {
        // Handle key presses for moving the block
        if (keyCode == PConstants.LEFT) {
            this.player.moveLeft();
            this.moveSound.play();
        }
        if (keyCode == PConstants.RIGHT) {
            this.player.moveRight();
            this.moveSound.play();
        }
        if (keyCode == PConstants.UP) {
            this.player.rotate();
            this.rotateSound.play();
        }
        if (keyCode == PConstants.DOWN) {
            this.handleMoveDown();
        }
        if (this.sketch.key == ' ') {
           while(handleMoveDown()) { 
               // increase score for hard drop
               this.mechanic.setScore(
                (int)(this.mechanic.getScore() + 0.01 * this.mechanic.getLevel()));
           }
        }
    }

    private boolean handleMoveDown() {
        
        boolean cannotMoveDown = !this.player.moveDown();

        if (cannotMoveDown) {            
            this.player.placeBlock();
            
            int clearedRowsCount = this.grid.clearFullRows();
            
            // clear row drop
            if (clearedRowsCount > 0) {
                this.mechanic.setCombo(this.mechanic.getCombo() + 1);

                if (this.mechanic.getCombo() == 1) this.firstComboSound.play();
                else if (this.mechanic.getCombo() == 2) this.secondComboSound.play();
                else if (this.mechanic.getCombo() == 3) this.thirdComboSound.play();
                else if (this.mechanic.getCombo() >= 4) this.fourthComboSound.play();
                
                // Update score based on combo and level
                
                this.mechanic.setScore(
                    (int)(this.mechanic.getScore() + (3 * this.mechanic.getLevel()) * this.mechanic.getCombo() * clearedRowsCount));

                // Update lines cleared
                this.mechanic.setLines(this.mechanic.getLines() + clearedRowsCount);
                    

                // Level up logic
                if (this.mechanic.getLines() >= this.mechanic.getGainUp()) {
                    this.mechanic.setLevel(this.mechanic.getLevel() + 1);
                    this.mechanic.setGainUp(this.mechanic.getGainUp() + 5 + this.mechanic.getLevel());
                    interval = Math.max(100, interval - 100); // Decrease interval by 100ms per level, min 100ms
                }
            }
            // regular drop
            else { 
                this.mechanic.setCombo(0);
                this.dropSound.play(); 
            }

            // Create a new block
            this.player = this.getRandomBlock();

            // Increase score for new block creation
            this.mechanic.setScore(
                (int)(this.mechanic.getScore() + 0.5 * this.mechanic.getLevel()));

            // Check if block can still be placed, otherwise the game is over
            if (this.player.canBePlaced() == false) {
                Mechanic.save(this.mechanic.getScore());
                this.grid.resetCells();
                this.resetMechanics();
                Main.SCREEN = "game_over";
            }

            return false;    
        }
        return true;
    }

    private Block getRandomBlock() {
        Random random = new Random();
        
        Block[] blocks = new Block[] {
            new I_Block(sketch, this.grid),
            new J_Block(sketch, this.grid),
            new L_Block(sketch, this.grid),
            new O_Block(sketch, this.grid),
            new S_Block(sketch, this.grid),
            new T_Block(sketch, this.grid),
            new Z_Block(sketch, this.grid)
        };

        return blocks[random.nextInt(blocks.length)];
    }

    private void resetMechanics() {
        this.mechanic.setScore(0);
        this.mechanic.setCombo(0);
        this.mechanic.setLevel(1);
        this.mechanic.setLines(0);
        this.mechanic.setGainUp(5);
        this.interval = 700;
    }
}
