package comp;

/**
 * Point class to represent a generic data point in 2D space with a given ID.
 * The objects details are immutable after instantiation as these points should
 * not move.
 *
 */
public class Point {
	private String ID;
	protected double x, y;

	/**
	 * Construct a new <code>Point</code> object with a given ID and 2D location.
	 * 
	 * @param id A String of the identifier assigned to this point.
	 * @param x  A double of the x coordinate of this point.
	 * @param y  A double of the y coordinate of this point.
	 */
	public Point(String id, double x, double y) {
		this.ID = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * Retrieve the identifier assigned to this point.
	 * 
	 * @return A String of the identifier assigned to this point.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Retrieve the x coordinate of this point.
	 * 
	 * @return A double of the x coordinate of this point.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Retrieve the y coordinate of this point.
	 * 
	 * @return A double of the y coordinate of this point.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Retrieve the coordinates of this point together as an array of length 2.
	 * 
	 * @return A double array of length 2 containing the coordinates of this point.
	 */
	public double[] getCoords() {
		double[] arr = { x, y };
		return arr;
	}

	@Override
	public Point clone() {
		return new Point(ID, x, y);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Point: ");
		str.append(ID);
		str.append(" X: ");
		str.append(x);
		str.append(" Y: ");
		str.append(y);
		return str.toString();
	}

	public boolean equals(Point other) {
		return (this.x == other.x && this.y == other.y);
	}
}
