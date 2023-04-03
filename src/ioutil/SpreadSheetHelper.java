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
	public static List<Point> readPoints(File file, int firstCol, int secondCol) {
		// TODO: Check this code actually works
		try {
			List<Point> input = new ArrayList<Point>(); 
			int count = 0;
			FileReader csv = new FileReader(file);
			BufferedReader dataset = new BufferedReader(csv);
			
			//Checks for negative ranges
			if(firstCol < 0 || secondCol < 0) {
				dataset.close();
				throw new IllegalArgumentException("Column Ranges cannot be negative");
			}
			//Flips the start and end columns if the start col is greater than the end col
			if(firstCol > secondCol)
			{
				int temp = firstCol;
				firstCol = secondCol;
				secondCol = temp;
			}	
			
			String inputCSV = "";
			try {
				while((inputCSV = dataset.readLine()) != null) {
					String [] cols = inputCSV.split(",");
					input.add(new Point(Integer.toString(count), (Double.parseDouble(cols[firstCol])), (Double.parseDouble(cols[secondCol]))));
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
	public static List<Centroid> readCentriods(File file, int firstCol, int secondCol) {
		List<Centroid> input = new ArrayList<Centroid>();
		input.add((Centroid) readPoints(file, firstCol, secondCol));
		return input;
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
		//Use File file as a folder path instead
		try {
		FileWriter [] csvOut = new FileWriter[cntHistory.size()];
		PrintWriter [] output = new PrintWriter[cntHistory.size()];
		int iterate = cntHistory.size();
		if(iterate > 10)
			iterate = 10;
		
		for(int i = 0; i < iterate; i++) {
			csvOut[i] = new FileWriter(file + "Iteration " + i + ".csv");
			output[i] = new PrintWriter(csvOut[i]);
			//Create Column Labels
			for(int j = 0; j < cntHistory.size(); j++) {
				//Cannot use parent methods here? need GetX and GetY
				output[i].print("Points belonging to cluster " + j + "located at: " + "x:" + cntHistory.get(i)[j].getX() + "Y:" + cntHistory.get(i)[j].getY() + ",");
			}
			
			//Print Data to csv file
			for(Point temp : pnts) {
				output[i].println();
				for(int j = 0; j < cntHistory.size(); j++) {
					output[i].print("x:" + temp.getX() + " y:" + temp.getY());
					output[i].print(",");
				}
			}
			output[i].close();
		}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File creation failed");
			e.printStackTrace();
		}

	}
}
