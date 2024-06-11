package tetronimos;

import processing.core.PApplet;

public class L_Block extends Block {

    public L_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 0, 0, 3 },
                { 3, 3, 3 },
                { 0, 0, 0 }
            },
            {
                { 0, 3, 0 },
                { 0, 3, 0 },
                { 0, 3, 3 }
            },
            {
                { 0, 0, 0 },
                { 3, 3, 3 },
                { 3, 0, 0 }
            },
            {
                { 3, 3, 0 },
                { 0, 3, 0 },
                { 0, 3, 0 }
            }
        };
    }

    @Override
    protected int setType() {
        return 3;
    }
}
