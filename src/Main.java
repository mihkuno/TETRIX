import java.util.ArrayList;
import processing.core.PApplet;

public class Main extends PApplet{
	
	private ArrayList<Ball> balls = new ArrayList<>();
	
    @Override
	public void settings(){
		size(500, 500);
		balls.add(new Ball(this, width/2, height/2));
	}
	
    @Override
	public void draw(){
		background(64);
		for(Ball b : balls){
			b.step();
			b.render();
		}
	}
	
    @Override
	public void mouseDragged(){
		balls.add(new Ball(this, mouseX, mouseY));
	}
	
	public static void main(String[] args){
		String[] processingArgs = {"MySketch"};
		Main mySketch = new Main();
		PApplet.runSketch(processingArgs, mySketch);
	}
}