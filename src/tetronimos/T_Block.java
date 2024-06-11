package tetronimos;

import processing.core.PApplet;

public class T_Block extends Block {

    public T_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 0, 6, 0 },
                { 6, 6, 6 },
                { 0, 0, 0 }
            },
            {
                { 0, 6, 0 },
                { 0, 6, 6 },
                { 0, 6, 0 }
            },
            {
                { 0, 0, 0 },
                { 6, 6, 6 },
                { 0, 6, 0 }
            },
            {
                { 0, 6, 0 },
                { 6, 6, 0 },
                { 0, 6, 0 }
            }
        };
    }

    @Override
    protected int setType() {
        return 6;
    }
}
