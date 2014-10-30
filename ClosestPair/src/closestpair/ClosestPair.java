package closestpair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ClosestPair {

	public static void main(String[] args) {
		int num = 5; //numbers of points
		int max = 100; //max value of x and y
		
		//randomly generate points set
		Point[] points = genRandomPoints(num, max);
		
		Point[] pair = getClosestPair(points);
		
		doExportResult(pair);
		
	}
	
	public static Point[] getClosestPair(Point[] points) {
		if (points.length <=2) {
			return points;
		}
		
		int splitX = getSplitX(points);
		
		//split into 2 sets by x-coordinate
		ArrayList<Point> leftList = new ArrayList<>();
		ArrayList<Point> rightList = new ArrayList<>();
		for(int i = 0; i < points.length; i++){
			if (points[i].getX() <= splitX) {		
				leftList.add(points[i]);
			} else {
				rightList.add(points[i]);
			}
		}
		Point[] leftPoints = leftList.toArray(new Point[leftList.size()]);
		Point[] rightPoints = rightList.toArray(new Point[rightList.size()]);
		
		//get closest pair
		Point[] leftPair = getClosestPair(leftPoints);
		Point[] rightPair = getClosestPair(rightPoints);
		double distLeft =  getPairDist(leftPair);
		double distRight =  getPairDist(rightPair);
		Point[] pair = new Point[2];
		int delta = 0;
		if (distLeft < distRight) {
			delta = (int)Math.sqrt(distLeft);
			pair = leftPair;
		} else {
			delta = (int)Math.sqrt(distRight);
			pair = rightPair;
		}
		
		//get points within 2delta strip
		Point[] deltaLeft = getSortedDeltaPoints(leftPoints, splitX, delta);
		Point[] deltaRight = getSortedDeltaPoints(rightPoints, splitX, delta);
		if (pair.length != 2) {
			System.err.printf("pair has less 2 points!: %s", pair.toString());
			return null;
		}
		pair = compareDist2Delta(deltaLeft, deltaRight, delta, pair);		
		return pair;
	}
	
	public static int getSplitX(Point[] points) {
		int minX = (int) Double.POSITIVE_INFINITY;
		int maxX = (int) Double.NEGATIVE_INFINITY;
		for(int i = 0; i < points.length; i++){
			if(points[i].getX() < minX)
				minX = points[i].getX();
			if(points[i].getX() > maxX)
				maxX = points[i].getX();
		}
		return (minX + maxX)/2;
	}
	
	public static double getPairDist(Point[] pair) {
		if (pair.length <= 1) {
			return Double.MAX_VALUE;
		}
		return pair[0].distWith(pair[1]);
	}
	
	public static Point[] compareDist2Delta(Point[] deltaLeft, Point[] deltaRight, int delta, Point[] pair) {
		for (int i = 0; i < deltaLeft.length; i++) {
			for (int j = 0; j < deltaRight.length; j++) {
				if (Math.abs(deltaRight[j].getY()-deltaLeft[i].getY()) < delta
						&& deltaRight[j].getX()-deltaLeft[i].getX() < delta) {
					int dist = (int)Math.sqrt(deltaLeft[i].distWith(deltaRight[j]));
					if (dist < delta) {
						delta = dist;
						pair[0] = deltaLeft[i];
						pair[1] = deltaRight[j];						
					}
				}				
			}
		}
		return pair;
	}
	
	public static Point[] getSortedDeltaPoints(Point[] points, int splitX, int delta) {
		ArrayList<Point> dPoints = new ArrayList<>();
		for(int i = 0; i < points.length; i++){
			if(Math.abs(splitX - points[i].getX()) < delta)
				dPoints.add(points[i]);
		}
		Collections.sort(dPoints, new SortByY());
		return dPoints.toArray(new Point[dPoints.size()]);
	}
	
	public static void doExportResult(Point[] pair) {
		System.out.print("{("+pair[0].getX()+","+pair[0].getY()
				+")-("+pair[1].getX()+","+pair[1].getY()+")}");
	}
	
	public static Point[] genRandomPoints(int num, int max) {
		Set<Point> pointsSet = new HashSet<Point>();
		Random random = new Random();
		int x,y;
		while (num-- > 0) {
			x = random.nextInt(max);
			y = random.nextInt(max);
			if (!pointsSet.add(new Point(x, y))) {
				continue;
			}
			System.out.println("("+x+","+y+")");
		}
		return pointsSet.toArray(new Point[pointsSet.size()]);
	}
}
