package tetronimos;

import processing.core.PApplet;

public class S_Block extends Block {

    public S_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 0, 5, 5 },
                { 5, 5, 0 },
                { 0, 0, 0 }
            },
            {
                { 0, 5, 0 },
                { 0, 5, 5 },
                { 0, 0, 5 }
            },
            {
                { 0, 0, 0 },
                { 0, 5, 5 },
                { 5, 5, 0 }
            },
            {
                { 5, 0, 0 },
                { 5, 5, 0 },
                { 0, 5, 0 }
            }
        };
    }

    @Override
    protected int setType() {
        return 5;
    }
}
