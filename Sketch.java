import processing.core.PApplet;

public class Sketch extends PApplet {

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);
  }

  public void draw() {
    background(0);
    fill(255);
    ellipse(mouseX, mouseY, 10, 10);

	Point a = new Point(0, 0);
	Point b = new Point(20, 30);
	Point c = new Point(60, 0);
	for ( int i = 0; i < 100; i++) {
		i /= 100;
		Point p = bezier(new Point[]{a, b, c}, i);
		ellipse((float) p.x, (float) p.y, 10F, 10F);

	}


  }

	Point bezier(Point[] points, double percent) {
		if ( points.length == 0) return null;
		if ( points.length == 1 ) return points[0];
		Point[] rpoints = new Point[points.length-1];
		for ( int i = 0; i<rpoints.length; i++) {
			rpoints[i] = lerp(points[i], points[i+1], percent);
		}
		bezier(rpoints, percent);
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
		this.x = x;
		this.y = y;
	}
	
}