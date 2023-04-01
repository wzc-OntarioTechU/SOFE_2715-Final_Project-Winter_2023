import java.util.List;
import comp.Centroid;
import comp.Point;

public class Mapspace {
	public List<Centroid> centroids;
	public List<Point> points;

	/**
	 * Construct a new Mapspace object with a given list of points and centroids (allows manual entry of centroids) 
	 * 
	 * @param points
	 * @param centroids
	 */
	public Mapspace(List<Point> points, List<Centroid> centroids) {
		this.points = points; 
		this.centroids = centroids;
	}
	/**
	 * Construct a new Mapspace object with a given list of points, number of centroids, and method for finding centroid placements (random or k++)
	 * 
	 * @param points
	 * @param numCentroids
	 * @param kPlusPlus
	 */
	public Mapspace(List<Point> points, int numCentroids, boolean kPlusPlus) {
		
	}
	
	/**
	 * Construct a new Mapspace object with a given list of points, number of centroids, then uses random centroid placement
	 * 
	 * @param points
	 * @param numCentroids
	 */
	public Mapspace(List<Point> points, int numCentroids) {
		
	}
	
	/**
	 * Updates the centroid and clusters based on the means of each cluster 
	 */
	public void iterate() {
		//Run Clustering
	}
	
	/**
	 * FILL IN (NOT SURE WHAT TO PUT HERE)
	 * 
	 * @return a copy of the points in the cluster
	 */
	public List<Point> getPoints(){
		return null;
		
	}
	
	/**
	 * FILL IN (NOT SURE WHAT TO PUT HERE)
	 * 
	 * @return a copy of the centroids for each cluster
	 */
	public List<Centroid> getCentroids()
	{	
		return null;
	}
}
