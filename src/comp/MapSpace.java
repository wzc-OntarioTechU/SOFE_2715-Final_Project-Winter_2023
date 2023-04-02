package comp;

import java.util.Arrays;

public class MapSpace {
	private Point[] points;
	private Centroid[] centroids;

	/**
	 * Constructs a new MapSpace object with the given Point and Centroid arrays to
	 * perform k means clustering calculations. This constructor performs a deep
	 * copy to eliminate any links back to the original Point and Centroid objects.
	 * 
	 * @param pnts   An array of Point objects to use in the k means calculation.
	 * @param cntrds An array of Centroid objects to start the k means calculation.
	 */
	public MapSpace(Point[] pnts, Centroid[] cntrds) {
		points = new Point[pnts.length];
		centroids = new Centroid[cntrds.length];
		for (int i = 0; i < points.length; i++) {
			if (pnts[i] == null)
				throw new IllegalArgumentException("Given points array contains a null index, which is not allowed.");
			points[i] = pnts[i];
		}
		for (int i = 0; i < cntrds.length; i++) {
			if (cntrds[i] == null)
				throw new IllegalArgumentException(
						"Given centroids array contains a null index, which is not allowed.");
			centroids[i] = cntrds[i].clone();
		}
	}

	/**
	 * Constructs a new MapSpace object with the given Point array, the specified number of Centroid objects to generate, and whether to use random initialization or k++ initialization.
	 * @param pnts An array of Point objects to use in the k means calculation.
	 * @param numCentroids An integer of the number of Centroid objects to generate.
	 * @param kPlusPlus A boolean of whether to use k++ generation or random (true for k++).
	 */
	public MapSpace(Point[] pnts, int numCentroids, boolean kPlusPlus) {
		// TODO: Implement centroid generation algorithms (random, and k++)
		this(pnts, null); // TODO: replace null with generated centroids list
	}
	
	/**
	 * Constructs a new MapSpace object with the given Point array, and the specified number of Centroid objects to generate using random initialization.
	 * @param pnts An array of Point objects to use in the k means calculation.
	 * @param numCentroids An integer of the number of Centroid objects to generate.
	 */
	public MapSpace(Point[] pnts, int numCentroids) {
		this(pnts, numCentroids, false);
	}

	/**
	 * Method to perform an iteration of the k means calculation on the MapSpace object.
	 */
	public void iterate() {
		for (Point pnt : points) {
			//initalize candidate centroid
			Centroid candidate = centroids[0];
			double distanceCan = centroids[0].distanceTo(pnt);
			//measure distance to all centroids
			for (Centroid cntrd : centroids){
				double distance = cntrd.distanceTo(pnt);
				if (distance < distanceCan){
					candidate = cntrd;
					distanceCan = distance;
				}
			}
			//set closest centroid
			candidate.ownPoint(pnt);
		}
		// find new centroid position
		for (Centroid cntrd : centroids){
			cntrd.recenter();
		}

	}

	/**
	 * Method to start the unsupervised model
	 */
	public void run() {
		Centroid [] prevState;
		do {
			// save current state
			prevState = this.getCentroids();
			//take one step
			this.iterate();
			//check if two states match
		} while(!Arrays.equals(this.centroids, prevState));

	}
	/**
	 * Returns a shallow copy of the Point objects array used in this MapSpace object (Used to prevent changes to internal array but shallow since Point objects are immutable).
	 * @return An array of Point objects that is a shallow copy of the instance Point objects array.
	 */
	public Point[] getPoints() {
		return Arrays.copyOf(points, points.length);
	}

	/**
	 * Returns a deep copy of the Centroid objects array used in this MapSpace object (used since Centroids should not be moved by other classes).
	 * @return An array of Centroid objects that is a deep copy of the instance Centroid objects array.
	 */
	public Centroid[] getCentroids() {
		Centroid[] temp = new Centroid[centroids.length];
		for (int i = 0; i < centroids.length; i++) {
			temp[i] = centroids[i].clone();
		}
		return temp;
	}
	
	@Override
	public MapSpace clone() {
		// no need to worry about using getPoints or getCentroids since deep copy is handled by constructor
		return new MapSpace(points, centroids);
	}
}
