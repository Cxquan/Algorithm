package closestpair;

import java.util.Comparator;

public class Point implements Comparable<Point>{
	private final int x;
	
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	// no sqrt
	public double distWith(Point point) {
		return Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2);
	}
	
	@Override
	public int compareTo(Point o) {
		if(x == o.getX() && y == o.getY())
			return 0;
		else 
			return 1;
	}
	
	@Override
	public int hashCode() {
		return this.x;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			return this.x==((Point)obj).getX() ? true : false;
		}
		return false;
	}
}

class SortByX implements Comparator<Point>{
	 public int compare(Point point1,Point point2){
	  if(point1.getX() < point2.getX())
	   return 1;
	  else
	   return 0;
	 }
}

class SortByY implements Comparator<Point>{
	 public int compare(Point point1,Point point2){
	  if(point1.getY() < point2.getY())
	   return 1;
	  else
	   return 0;
	 }
}
