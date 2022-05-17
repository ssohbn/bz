import processing.core.PApplet;

public class Sketch extends PApplet {

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);
  }

  public void draw() {
    fill(255);
    ellipse(mouseX, mouseY, 25, 25);
  }

	Point sus(Point[] points, double percent) {
		if ( points.length == 0) return null;
		if ( points.length == 1 ) return points[0];
		Point[] rpoints = new Point[points.length-1];
		for ( int i = 0; i<rpoints.length; i++) {
			rpoints[i] = lerp(points[i], points[i+1], percent);
		}
		sus(rpoints, percent);
		return null;
	}

	Point lerp(Point a, Point b, double percent) {
		double cx = a.x + (b.x-a.x) * percent;
		double cy = a.y + (b.y -a.y) * percent;	
		return new Point(cx, cy);
	}
}

class Point {
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this. x = x;
		this.y = y;
	}
	
}