package tetronimos;

import processing.core.PApplet;

public class O_Block extends Block {

    public O_Block(PApplet sketch, Grid grid) {
        super(sketch, grid);
    }

    @Override
    protected int[][][] setFaces() {
        return new int[][][] {
            {
                { 4, 4 },
                { 4, 4 }
            }
        };
    }

    @Override
    protected int setType() {
        return 4;
    }
}
