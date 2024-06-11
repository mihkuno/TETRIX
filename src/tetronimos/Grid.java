package tetronimos;

import processing.core.PApplet;

public class Grid {
    
    public PApplet sketch;
    public int rows = 20;
    public int cols = 10;
    public int cell = 30;
    public int offsetX = 6;
    public int offsetY = 0;
    public int[][] block;

    public Grid(PApplet sketch) {
        this.sketch = sketch;
        this.block = new int[this.rows][this.cols];
    }

    public void render() {
        this.sketch.fill(200);
        this.sketch.stroke(0);
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[y].length; x++) {
                this.sketch.square((x * this.cell) + (this.offsetX * this.cell), (y * this.cell) + (this.offsetY * this.cell), this.cell);
            }
        }
    }
}
