package ioutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	public static List<Point> readPoints(File file, int colDataStart, int colDataEnd) {
		// TODO: Check this code actually works
		try {
			List<Point> input = new ArrayList<Point>(); 
			int count = 0;
			FileReader csv = new FileReader(file);
			BufferedReader dataset = new BufferedReader(csv);
			
			//Checks for negative ranges
			if(colDataStart < 0 || colDataEnd < 0) {
				dataset.close();
				throw new IllegalArgumentException("Column Ranges cannot be negative");
			}
			//Flips the start and end columns if the start col is greater than the end col
			if(colDataStart > colDataEnd)
			{
				int temp = colDataStart;
				colDataStart = colDataEnd;
				colDataEnd = temp;
			}	
			
			String inputCSV = "";
			try {
				while((inputCSV = dataset.readLine()) != null) {
					String [] cols = inputCSV.split(",");
					input.add(new Point(Integer.toString(count), (Double.parseDouble(cols[colDataStart])), (Double.parseDouble(cols[colDataEnd]))));
				}
				dataset.close();
				return input;
			}
			catch(IllegalArgumentException e) {
				System.out.println("Invalid Data type");
				return null;
			}
		} 
		catch (IOException e) {
			System.out.println("Unable to find the specified file path");
			e.printStackTrace();
			return null;
		}
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
		// TODO: Check this code works
		try {
			FileWriter csvOut = new FileWriter("K-Means Clusters of " + file.getName() + ".csv");
			PrintWriter output = new PrintWriter(csvOut);
			//Create Col Labels
			for(int i = 0; i < cntHistory.size(); i++) {
				//Cannot use parent methods here? need GetX and GetY
				output.print("Clustered points around centroid at: (" + cntHistory.get(i) + ")");
			}
			
			//Print Data to csv file
			for(Point temp : pnts) {
				for(int i = 0; i < cntHistory.size(); i++) {
					output.print("x:" + temp.getX() + " y:" + temp.getY());
					output.print(",");
				}
				output.println();
			}
			output.close();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File creation failed");
			e.printStackTrace();
		}

	}
}
