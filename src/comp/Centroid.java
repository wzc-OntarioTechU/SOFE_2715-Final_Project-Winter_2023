package comp;

import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

/**
 * Centroid class to represent the k-means centroid type points. Effectively a
 * point with a list of owned points and some methods to recalculate which
 * points it should own and to recalculate its own position.
 */
public class Centroid extends Point {
	// Linked List used since it will frequently be emptied and refilled and be of
	// mutable size
	private LinkedList<Point> owned;

	/**
	 * Construct a new Centroid object with given ID and 2D location.
	 * 
	 * @param id A String of the identifier assigned to this Centroid.
	 * @param x  A double of the x coordinate of this Centroid.
	 * @param y  A double of the y coordinate of this Centroid.
	 */
	public Centroid(String id, double x, double y) {
		super(id, x, y);
		owned = new LinkedList<Point>();
	}

	/**
	 * This method recalculates the centroid of the points owned by this Centroid
	 * object. If there are no owned points then the Centroid object will not move.
	 * After calculating and moving the Centroid object the owned points list will
	 * be emptied.
	 */
	public void recenter() {
		if (owned == null || !owned.isEmpty()) {
			// Calculated the mean of the cluster
			double [] pointsMean = {0,0};
			for(Point i : owned) {
				pointsMean[0] += i.getX();
				pointsMean[1] += i.getY();
			}
			
			x = pointsMean[0] / owned.size();
			y = pointsMean[1] / owned.size();
		}
		// empty ownership list
		owned.clear();
	}


	/**
	 * This method calculates the distance from a given Point object to the Centroid
	 * object for use in determining ownership.
	 * 
	 * @param pnt A Point object to calculate the distance to/from.
	 * @return The distance from the given Point object to the Centroid Object
	 */
	public double distanceTo(Point pnt) {
		//point distance calculation for r^2 distance. remove comma for r
		return /*Math.sqrt(*/Math.pow(this.x-pnt.x,2)+Math.pow(this.y-pnt.y,2)/*)*/;
	}

	/**
	 * Add given Point object to list of points owned by this Centroid.
	 * 
	 * @param pnt A Point object to add to owned points list.
	 */
	public void ownPoint(Point pnt) {
		owned.add(pnt);
	}

	/**
	 * Compare centroids by their list of owned points
	 *
	 * @param Centroid other is centroid being compared to this
	 */
	public boolean equals(Centroid other){
		return owned.equals(other.getOwned());
	}

	/**
	 * Returns a copy of the owned points list of the Centroid using a shallow copy
	 * as to not recreate immutable Point objects but to disconnect mutability of
	 * original list.
	 * 
	 * @return A a shallow copy of the List of Point objects owned by the Centoid.
	 */
	@SuppressWarnings("unchecked")
	public List<Point> getOwned() {
		return (List<Point>) owned.clone();
	}
	
	@Override
	public Centroid clone() {
		Centroid temp = new Centroid(this.getID(), this.x, this.y);
		for(Point pnt : owned) {
			temp.ownPoint(pnt);
		}
		return temp;
	}
}
