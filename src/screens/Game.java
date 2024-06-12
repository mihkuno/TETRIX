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
    private int score = 0;
    private int combo = 0;
    private int level = 1;
    private int linesCleared = 0;
    private int gainUp = 5;

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
    }

    public void render() {
        this.sketch.background(246, 241, 226);
        this.sketch.windowTitle("Tetrix - Game");   
        this.mechanic.render(
            this.score, 
            this.level, 
            this.combo, 
            this.linesCleared, 
            this.gainUp
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
               score += 0.01 * level;
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
                this.combo++;
                if (this.combo == 1) this.firstComboSound.play();
                else if (this.combo == 2) this.secondComboSound.play();
                else if (this.combo == 3) this.thirdComboSound.play();
                else if (this.combo == 4) this.fourthComboSound.play();
                
                // Update score based on combo and level
                score += (3 * level) * combo * clearedRowsCount;
                linesCleared += clearedRowsCount;

                // Level up logic
                if (linesCleared >= gainUp) {
                    level++;
                    gainUp += 5 + level;
                    interval = Math.max(100, interval - 100); // Decrease interval by 100ms per level, min 100ms
                }
            }
            // regular drop
            else { 
                this.combo = 0;
                this.dropSound.play(); 
            }

            // Create a new block
            this.player = this.getRandomBlock();

            // Check if block can still be placed, otherwise the game is over
            if (this.player.canBePlaced() == false) {
                Main.SCREEN = "entering_menu";
            }

            // Increase score for new block creation
            score += 0.5 * level;
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
}
