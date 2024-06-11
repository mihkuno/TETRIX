package screens;

import processing.core.PApplet;
import processing.core.PConstants;
import tetronimos.Grid;
import tetronimos.I_Block;

public class Game {
    PApplet sketch;
    Grid grid;
    I_Block i_block;

    public Game(PApplet sketch) {
        this.sketch = sketch;
        this.grid = new Grid(sketch);
        
        this.i_block = new I_Block(sketch, this.grid);
    }

    public void render() {
        this.sketch.background(246, 241, 226);
        this.sketch.windowTitle("Tetrix - Game");   
        this.grid.render();
        this.i_block.render();
    }


    public void handleKeyPress(int keyCode) {
        // Handle key presses for moving the block

        if (keyCode == PConstants.LEFT) {
            this.i_block.moveLeft();
        }

        if (keyCode == PConstants.RIGHT) {
            this.i_block.moveRight();
        }

        if (keyCode == PConstants.UP) {
            this.i_block.rotate();
        }

        if (keyCode == PConstants.DOWN) {
            this.i_block.moveDown();
        }
        
    }
}
