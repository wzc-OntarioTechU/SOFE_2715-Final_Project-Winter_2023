package ioutil;

import java.io.File;

public class placeholder {
	// note that abstract class can be used in place of a singleton. We don't need a singleton process, just abstract.
	public static void runner() {
		File in = new File("D:\\Downloads\\Top 50 US Tech Companies 2022 - 2023.csv");
		System.out.println(SpreadSheetHelper.readPoints(in,5,6).get(0).getX());
		System.out.println(SpreadSheetHelper.readPoints(in, 5, 6).get(0).getY());
	}
	
	public static void main (String [] args) {
		//Testcase
		runner();
	}
}
