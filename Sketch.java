import processing.core.PApplet;

public class Sketch extends PApplet {

	public void settings() {
		size(400, 400);
	}

	public void setup() { 
		background(0);
	}

	public void draw() {
		background(255);
		fill(255);
		ellipse(mouseX, mouseY, 10, 10);

		Point a = new Point(0, 0);
		Point b = new Point(20, 30);
		Point c = new Point(60, 0);


		for ( int i = 0; i < 100; i++) {
			float p = i / 100F;
			Point bp = bezier(new Point[]{a, b, c}, p);
			point(bp.x, bp.y);

		}
	}

	Point bezier(Point[] points, float percent) {
		if ( points.length == 0) return null;
		if ( points.length == 1 ) return points[0];
		Point[] rpoints = new Point[points.length-1];
		for ( int i = 0; i<rpoints.length; i++) {
			rpoints[i] = lerp(points[i], points[i+1], percent);
		}
		return bezier(rpoints, percent);
	}

	Point lerp(Point a, Point b, float percent) {
		float cx = a.x + (b.x-a.x) * percent;
		float cy = a.y + (b.y -a.y) * percent;	
		return new Point(cx, cy);
	}
}

class Point {
	public float x;
	public float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void print() {
		System.out.println("x: " + x + " y: " + y);
	}
	
}