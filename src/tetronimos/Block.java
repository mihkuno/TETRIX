package tetronimos;

import java.util.Random;
import processing.core.PApplet;

public abstract class Block {
    protected PApplet sketch;
    protected Random random;
    protected Grid grid;
    protected int faceIndex;
    protected int[][][] faces;
    protected int[][] block;
    protected int size;
    protected int type;
    protected int x, y;

    public Block(PApplet sketch, Grid grid) {
        this.random = new Random();
        this.sketch = sketch;
        this.grid   = grid;
        this.size   = this.grid.cell;
        this.x      = (int) this.grid.cols / 2 - 1;
        this.y      = 0;
        this.type   = this.setType();
        this.faces  = this.setFaces();
        this.faceIndex = this.random.nextInt(this.faces.length);  
        this.block     = this.faces[this.faceIndex];
    }

    public boolean rotate() {
        int[][] nextBlock = this.faces[(this.faceIndex + 1) % this.faces.length];
        if (!canMoveTo(nextBlock, this.x, this.y)) {
            return false;
        }
        this.faceIndex = (this.faceIndex + 1) % this.faces.length;
        this.block = this.faces[this.faceIndex];
        return true;
    }

    public void render() {
        
        // repeatedly move the ghost block down until it collides with something
        int yGhost = this.y;
        while (canMoveTo(this.block, this.x, yGhost + 1)) {
            yGhost++;
        }

        for (int row = 0; row < this.block.length; row++) {
            for (int col = 0; col < this.block[row].length; col++) {
                if (this.block[row][col] > 0) {

                    // ghost block
                    // render the block if its below the hidden row
                    if (row + yGhost > 0) {
                        int[] ghostColor = this.grid.colors[8];
                        this.sketch.fill(ghostColor[0], ghostColor[1], ghostColor[2]);
                        this.sketch.square(
                            (col * this.size) + (this.grid.offsetX * this.size) + (this.x * this.size),
                            (row * this.size) + (this.grid.offsetY * this.size) + (yGhost * this.size),  
                            this.size
                        );
                        
                    }             

                    // player block
                    // render the block if its below the hidden row
                    if (row + this.y > 0) {
                        int[] playerColor = this.grid.colors[this.type];
                        this.sketch.fill(playerColor[0], playerColor[1], playerColor[2]);
                        this.sketch.square(
                            (col * this.size) + (this.grid.offsetX * this.size) + (this.x * this.size),
                            (row * this.size) + (this.grid.offsetY * this.size) + (this.y * this.size),  
                            this.size
                        );
                    }

                }
            }
        }
    } 

    public boolean moveLeft() {
        return moveTo(this.block, this.x - 1, this.y);
    }

    public boolean moveRight() {
        return moveTo(this.block, this.x + 1, this.y);
    }

    public boolean moveDown() {
        return moveTo(this.block, this.x, this.y + 1);
    }

    public boolean canBePlaced() {
        return canMoveTo(this.block, this.x, this.y);
    }

    private boolean moveTo(int[][] block, int newX, int newY) {
        if (canMoveTo(block, newX, newY)) {
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }

    private boolean canMoveTo(int[][] block, int newX, int newY) {
        for (int row = 0; row < block.length; row++) {
            for (int col = 0; col < block[row].length; col++) {
                if (block[row][col] > 0) {
                    int newGlobalX = col + newX;
                    int newGlobalY = row + newY;

                    // Check if out of bounds
                    if (newGlobalX < 0 || newGlobalX >= this.grid.cols || newGlobalY >= this.grid.rows) {
                        return false;
                    }

                    // Check for collision
                    if (newGlobalY >= 0 && this.grid.cells[newGlobalY][newGlobalX] > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placeBlock() {
        for (int row = 0; row < this.block.length; row++) {
            for (int col = 0; col < this.block[row].length; col++) {
                if (this.block[row][col] > 0) {
                    this.grid.cells[row + this.y][col + this.x] = this.block[row][col];
                }
            }
        }
    }

    protected abstract int[][][] setFaces();

    protected abstract int setType();
}
