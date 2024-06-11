package screens;

import java.util.Random;
import processing.core.PApplet;
import processing.core.PConstants;
import tetronimos.*;

public class Game {
    private PApplet sketch;
    private Grid grid;
    private Block player;
    private int interval = 500; // Initial interval in milliseconds
    private long lastTime; // Variable to store the last time the block moved down

    public Game(PApplet sketch) {
        this.sketch = sketch;
        this.grid = new Grid(sketch);
        this.player = this.getRandomBlock();
        this.lastTime = System.currentTimeMillis(); // Initialize lastTime
    }

    public void render() {
        this.sketch.background(246, 241, 226);
        this.sketch.windowTitle("Tetrix - Game");   
        
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
        }
        if (keyCode == PConstants.RIGHT) {
            this.player.moveRight();
        }
        if (keyCode == PConstants.UP) {
            this.player.rotate();
        }
        if (keyCode == PConstants.DOWN) {
            this.handleMoveDown();
        }
        if (this.sketch.key == ' ') {
           while(handleMoveDown()); 
        }
    }

    private boolean handleMoveDown() {
        if (!this.player.moveDown()) {            
            this.player.placeBlock();
            this.grid.clearFullRows();
            
            this.player = this.getRandomBlock();
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
