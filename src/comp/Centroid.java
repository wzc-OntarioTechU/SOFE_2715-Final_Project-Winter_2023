package comp;

import java.util.LinkedList;
import java.util.List;

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
		if (!owned.isEmpty()) {
			// TODO: Centroid Calculation here
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
		// TODO: point distance calculation, check if this should be regular distance or
		// r^2 distance
		return 0;
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
