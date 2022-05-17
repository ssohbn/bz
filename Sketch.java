import java.util.ArrayList;

import processing.core.PApplet;

public class Sketch extends PApplet {
	ArrayList<Point> points = new ArrayList<>();

	public void settings() {
		size(400, 400);
	}

	public void setup() { 
		background(0);
		// points.add( new Point(0, 50) );
		// points.add( new Point(30, 20) );
		// points.add( new Point(90, 300) );
		// points.add( new Point(200, 30) );
	}

	@Override
	public void mouseClicked() {
		Point clickPos = new Point(mouseX, mouseY);
		points.add(clickPos);
	}

	@Override
	public void keyPressed() {
		points.clear();
	}
	
	public void draw() {
		background(255);
		fill(255);
		ellipse(mouseX, mouseY, 10, 10);


		if ( points.size() != 0) {
			for ( int i = 0; i < 100; i++) {
				float p = i / 100F;
				Point bp = bezier(points, p);
				point(bp.x, bp.y);
			}

			for ( Point p : points) {
				ellipse(p.x, p.y, 10, 10);
			}
		}
	}

	/**
	 * use for make silly smooth curve on a list of points
	 * @param points
	 * @param percent
	 * @return
	 */
	Point bezier(Point[] points, float percent) {
		if ( points.length == 0) return null;
		if ( points.length == 1 ) return points[0];
		Point[] rpoints = new Point[points.length-1];
		for ( int i = 0; i<rpoints.length; i++) {
			rpoints[i] = lerp(points[i], points[i+1], percent);
		}
		return bezier(rpoints, percent);
	}

	/**
	 * use for make silly smooth curve on a list of points
	 * @param points
	 * @param percent
	 * @return
	 */
	Point bezier(ArrayList<Point> points, float percent) {
		if ( points.size() == 0) return null;
		if ( points.size() == 1 ) return points.get(0);
		Point[] rpoints = new Point[points.size()-1];
		for ( int i = 0; i<rpoints.length; i++) {
			rpoints[i] = lerp(points.get(i), points.get(i+1), percent);
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