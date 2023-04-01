package ioutil;

import java.io.File;
import java.util.List;

import comp.Centroid;
import comp.Point;

/**
 * This class is used as a utility/helper class for reading in csv files for
 * Point object data and Centroid object data.
 */
public abstract class SpreadSheetHelper {
	/**
	 * This method attempts to read in the specified file as a csv file and convert
	 * the entries to a list of Point objects.
	 * 
	 * @param file A file reference to read Point object data from.
	 * @return A list of Point objects read from the provided file.
	 */
	public static List<Point> readPoints(File file) {
		// TODO: code to import a csv and turn into points goes here
		return null;
	}

	/**
	 * This method attempts to read in the specified file as a csv file and convert
	 * the entries to a list of Centroid objects.
	 * 
	 * @param file A file reference to read Point object data from.
	 * @return A list of Centroid objects read from the provided file.
	 */
	public static List<Centroid> readCentriods(File file) {
		// TODO: code to import a csv and turn into points goes here
		return null;
	}

	/**
	 * This method attempts to write the Point object data and Centroid object
	 * history to a csv file.
	 * 
	 * @param file       A file reference to write to.
	 * @param pnts       An array of Point objects to include in the output file.
	 * @param cntHistory A list of arrays of Centroid objects to include as the
	 *                   Centroid history in the output file.
	 */
	public static void writeResults(File file, Point[] pnts, List<Centroid[]> cntHistory) {
		// TODO: code to export results as a csv goes here

	}
}
