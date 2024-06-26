package tetronimos;

import processing.core.PApplet;


public class Grid {
    
    public PApplet sketch;
    public int rows = 19;
    public int cols = 10;
    public int cell = 30;
    public int offsetX = 6;
    public int offsetY = 0;
    public int[][] colors;
    public int[][] cells;

    public Grid(PApplet sketch) {
        this.sketch = sketch;
        this.cells = new int[this.rows][this.cols];
        this.colors = new int[][] {
            { 209, 204, 192 }, // Empty 
            { 52, 152, 219},   // I
            { 243, 156, 18 },  // J
            { 22, 160, 133 },  // L
            { 241, 196, 15 },  // O
            { 46, 204, 113 },  // S
            { 155, 89, 182 },  // T
            { 231, 76, 60 },   // Z
            { 132, 129, 122 }  // Ghost
        };
    }

    public void render() {
        this.sketch.stroke(247, 241, 221);
        for (int y = 1 /*hide the first top rows*/; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                final int type = cells[y][x];
                this.sketch.fill(this.colors[type][0], this.colors[type][1], this.colors[type][2]);
                this.sketch.square((x * this.cell) + (this.offsetX * this.cell), (y * this.cell) + (this.offsetY * this.cell), this.cell);
            }
        }
    }

    public int clearFullRows() {
        int[] fullRows = findFullRows();
        int clearedRowsCount = 0;
        for (int row = 0; row < fullRows.length; row++) {
            if (fullRows[row] == 1) {
                clearedRowsCount++;
                shiftRowsDown(row);
            }
        }
        return clearedRowsCount;
    }

    private void shiftRowsDown(int row) {
        for (int y = row; y > 0; y--) {
            for (int x = 0; x < cells[y].length; x++) {
                cells[y][x] = cells[y - 1][x];
            }
        }
    }

    private int[] findFullRows() {
        int[] fullRows = new int[rows];

        final int rowLength = cells.length;
        for (int row = 0; row < rowLength; row++) {
            
            boolean full = true;

            final int colLength = cells[row].length;
            for (int col = 0; col < colLength; col++) {
                if (cells[row][col] == 0) {
                    full = false;
                    break;
                }
            }

            if (full) fullRows[row] = 1;
        }
        return fullRows;
    }

    public void resetCells() {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                this.cells[i][j] = 0;
            }
        }
    }
}
