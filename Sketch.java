import java.util.ArrayList;

import processing.core.PApplet;

public class Sketch extends PApplet {
	ArrayList<Point> points = new ArrayList<>();

	public void settings() {
		size(600, 600);
	}

	public void setup() { 
		background(0);
	}

	@Override
	public void mouseClicked() {
		Position clickPos = new Position(mouseX, mouseY);
		points.add(new Point(clickPos, 5, this));
	}

	@Override
	public void keyPressed() {
		positions.clear();
	}
	
	public void draw() {
		background(255);
		fill(255);
		ellipse(mouseX, mouseY, 10, 10);


		if ( positions.size() != 0) {
			for ( int i = 0; i < 1000; i++) {
				float p = i / 1000F;
				Position bp = bezier(positions, p);
				point(bp.x, bp.y);
			}

			for ( Point p : points) {
				float d = distance(p, new Position(mouseX, mouseY));	
				
				ellipse(p.x, p.y, 10, 10);
				fill(255);
				}
		}
	}

	/**
	 * use for make silly smooth curve on a list of positions
	 * @param positions
	 * @param percent
	 * @return
	 */
	Position bezier(Position[] positions, float percent) {
		if ( positions.length == 1 ) return positions[0];
		Position[] rpositions = new Position[positions.length-1];
		for ( int i = 0; i<rpositions.length; i++) {
			rpositions[i] = lerp(positions[i], positions[i+1], percent);
		}
		return bezier(rpositions, percent);
	}

	/**
	 * use for make silly smooth curve on a list of positions
	 * @param positions
	 * @param percent
	 * @return
	 */
	Position bezier(ArrayList<Position> positions, float percent) {
		if ( positions.size() == 0) return null;
		if ( positions.size() == 1 ) return positions.get(0);
		Position[] rpositions = new Position[positions.size()-1];
		for ( int i = 0; i<rpositions.length; i++) {
			rpositions[i] = lerp(positions.get(i), positions.get(i+1), percent);
		}
		return bezier(rpositions, percent);
	}

	Position lerp(Position a, Position b, float percent) {
		float cx = a.x + (b.x-a.x) * percent;
		float cy = a.y + (b.y -a.y) * percent;	
		return new Position(cx, cy);
	}
	float distance(Position a, Position b) {
		float d = (float) Math.sqrt(Math.pow((b.x-a.x),2) + Math.pow((b.y-a.y),2));
		return d;
	}
}

class Position {
	public float x;
	public float y;
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void print() {
		System.out.println("x: " + x + " y: " + y);
	}
}

class Point {
	public Position position;
	public float radius;
	public Sketch sketch;
	public boolean held;

	Point(Position position, float radius, Sketch sketch ) {
		this.position = position;
		this.radius = radius;
		this.sketch = sketch;
		this.held = false;
	}

	public void draw() 
	{
		sketch.fill(255);
		if ( held ) sketch.fill(255,0,0);

		
		sketch.ellipse(position.x, position.y, radius, radius);
	}
}