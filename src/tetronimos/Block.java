package tetronimos;

import java.util.Random;
import processing.core.PApplet;

public abstract class Block {
    protected PApplet sketch;
    protected Random random;
    protected Grid grid;
    protected int size;
    protected int faceIndex;
    protected int[][][] faces;
    protected int[][] block;
    protected int r, g, b;
    protected int x, y;

    public Block(PApplet sketch, Grid grid) {
        this.random = new Random();
        this.sketch = sketch;
        this.grid   = grid;
        this.size   = this.grid.cell;
        this.x      = 0;
        this.y      = 0;
        this.r      = this.setFill()[0];
        this.g      = this.setFill()[1];
        this.b      = this.setFill()[2];
        this.faces     = this.setFaces();
        this.faceIndex = this.random.nextInt(4);  
        this.block     = this.faces[this.faceIndex];
    }

    public boolean rotate() {

        // check parts of the next rotation if its colliding
        final int rowLength = this.block.length;
        for (int row = 0; row < rowLength; row++) {

            final int colLength = this.block[row].length;
            for (int col = 0; col < colLength; col++) {

                // get the next face
                int[][] nextBlock = this.faces[(this.faceIndex + 1) % this.faces.length];

                // check if next face out of bounds
                if (col + this.x < 0 || col + this.x >= this.grid.cols || row + this.y >= this.grid.rows) {
                    return false;
                }
                    
                // check if next face is colliding with a block on the grid
                if (nextBlock[row][col] > 0 && this.grid.block[row + this.y][col + this.x] > 0) {
                    return false;
                }
            }
        }
        
        this.faceIndex = (this.faceIndex + 1) % this.faces.length;
        this.block = this.faces[this.faceIndex];
        return true;
    }
    
    public void render() {
        this.sketch.fill(this.r, this.g, this.b);
        
        final int rowLength = this.block.length;
        for (int row = 0; row < rowLength; row++) {
            
            final int colLength = this.block[row].length;
            for (int col = 0; col < colLength; col++) {
                
                if (this.block[row][col] > 0) {
                    this.sketch.square(
                        (col * this.size) + (this.grid.offsetX * this.size) + (this.x * this.size),
                        (row * this.size) + (this.grid.offsetY * this.size) + (this.y * this.size),  
                        this.size
                    );
                }
            }
        }
    } 

    public boolean moveLeft() {
                
        // check parts of the block if its colliding
        final int rowLength = this.block.length;
        for (int row = 0; row < rowLength; row++) {

            final int colLength = this.block[row].length;
            for (int col = 0; col < colLength; col++) {
                    

                // check if the block is at the left edge
                if (this.block[row][col] > 0 && col + this.x == 0) {
                    return false;
                }

                // check if the block will collide with another block on the grid
                if (this.block[row][col] > 0 && this.grid.block[row + this.y][col + this.x - 1] > 0) {
                    return false;
                }
                
            }
        }
        
        this.x--;
        return true;
    }

    public boolean moveRight() {
                
        // check parts of the block if its colliding
        final int rowLength = this.block.length;
        for (int row = 0; row < rowLength; row++) {

            final int colLength = this.block[row].length;
            for (int col = 0; col < colLength; col++) {
                    
                // check if the block is at the left edge
                if (this.block[row][col] > 0 && col + this.x == this.grid.cols - 1) {
                    return false;
                }

                // check if the block will collide with another block on the grid
                if (this.block[row][col] > 0 && this.grid.block[row + this.y][col + this.x + 1] > 0) {
                    return false;
                }

            }
        }
        
        this.x++;
        return true;
    }

    public boolean moveDown() {
        // check parts of the block if its colliding
        final int rowLength = this.block.length;
        for (int row = 0; row < rowLength; row++) {

            final int colLength = this.block[row].length;
            for (int col = 0; col < colLength; col++) {
                    
                // check if the block is at the bottom edge
                if (this.block[row][col] > 0 && row + this.y == this.grid.rows - 1) {
                    return false;
                }

                // check if the block will collide with another block on the grid
                if (this.block[row][col] > 0 && this.grid.block[row + this.y + 1][col + this.x] > 0) {
                    return false;
                }

            }
        }
        
        this.y++;
        return true;
    }

    protected abstract int[][][] setFaces();
    
    protected abstract int[/*r, g, b*/] setFill();
    
}
