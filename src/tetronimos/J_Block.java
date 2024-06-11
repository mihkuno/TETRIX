package tetronimos;

import processing.core.PApplet;

public class J_Block extends Block {
    public J_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 2, 0, 0 },
                { 2, 2, 2 },
                { 0, 0, 0 }
            },
            {
                { 0, 2, 2 },
                { 0, 2, 0 },
                { 0, 2, 0 }
            },
            {
                { 0, 0, 0 },
                { 2, 2, 2 },
                { 0, 0, 2 },
            },
            {
                {  0, 2, 0 },
                {  0, 2, 0 },
                {  2, 2, 0 }
            }
        };
    }

    @Override
    protected int setType() {
        return 2;
    }
}
