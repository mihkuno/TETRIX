package tetronimos;

import processing.core.PApplet;

public class Z_Block extends Block {

    public Z_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 7, 7, 0 },
                { 0, 7, 7 },
                { 0, 0, 0 }
            },
            {
                { 0, 0, 7 },
                { 0, 7, 7 },
                { 0, 7, 0 }
            },
            {
                { 0, 0, 0 },
                { 7, 7, 0 },
                { 0, 7, 7 }
            },
            {
                { 0, 7, 0 },
                { 7, 7, 0 },
                { 7, 0, 0 }
            }
        };
    }

    @Override
    protected int setType() {
        return 7;
    }
}
