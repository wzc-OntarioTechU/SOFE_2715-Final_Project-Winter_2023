package comp;

public class MapSpace {
	private Point[] points;
	private Centroid[] centroids;
	
	public MapSpace(Point[] pnts, Centroid[] cntrds) {
		points = new Point[pnts.length];
		centroids = new Centroid[cntrds.length];
		for(int i = 0; i < points.length; i++) {
			if(pnts[i] == null)
				throw new IllegalArgumentException("Given points array contains a null index, which is not allowed.");
			points[i] = pnts[i].clone();
		}
		for(int i = 0; i < cntrds.length; i++) {
			if(cntrds[i] == null)
				throw new IllegalArgumentException("Given centroids array contains a null index, which is not allowed.");
			centroids[i] = cntrds[i].clone();
		}
	}
	
	public MapSpace(Point[] pnts, int numCentroids, boolean kPlusPlus) {
		// TODO: Implement centroid generation algorithms (random, and k++)
		this(pnts, null); // TODO: replace null with generated centroids list
	}
	
	public MapSpace(Point[] pnts, int numCentroids) {
		this(pnts, numCentroids, false);
	}
}
