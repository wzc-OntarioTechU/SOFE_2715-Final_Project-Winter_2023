package ioutil;

import java.awt.Canvas;
import java.awt.Color;
import java.io.File;
import java.util.List;

import comp.Centroid;
import comp.Point;

/**
 * This class is used as a utility to generate charts of the data for display in GUI and export as images. Since there should be a colour palette stored it is a singleton.
 */
public class ChartExportHelper {
	private static ChartExportHelper INSTANCE;
	public static Color[] PALETTE;
	
	/**
	 * Private constructor to make the singleton.
	 */
	private ChartExportHelper() {
	}
	
	/**
	 * This method returns the current instance of the ChartExportHelper or creates a new one with the given colour palette.
	 * @param pal An array of Color objects to use as a colour palette.
	 * @return The current singleton of ChartExportHelper.
	 */
	public static ChartExportHelper getInstance(Color[] pal) {
		if(INSTANCE == null)
			INSTANCE = new ChartExportHelper();
		if(pal == null || pal.length == 0)
			throw new IllegalArgumentException("Colour pallette is empty. Unable to create/update ChartExportHelper instance.");
		PALETTE = pal;
		return INSTANCE;
	}
	
	/**
	 * This method returns the current instance of the ChartExportHelper or creates a new one with a predefined colour palette.
	 * @return The current singleton of ChartExportHelper.
	 */
	public static ChartExportHelper getInstance() {
		Color[] temp = null;
		// TODO: define default colour palette
		return getInstance(temp);
	}
	
	/**
	 * This method exports the charts of the k means clustering as a set of images to the file system.
	 * @param dest A File object to export to.
	 * @param pnts An array of Point objects to use in the charts.
	 * @param cntHistory A list of arrays of Centroid objects to use in the charts.
	 * @param links A boolean flag to draw links between centroids and their nearest points.
	 * @param zones A boolean flag to draw a background of the centroids and their nearest points.
	 * @param labels A boolean flag to draw the IDs of the points and centroids.
	 */
	public void exportAsCharts(File dest, Point[] pnts, List<Centroid[]> cntHistory, boolean links, boolean zones, boolean labels) {
		// TODO: write code to export charts using other provided methods.
	}
	
	/**
	 * This method draws a chart on the provided Canvas object.
	 * @param canvas A Canvas object to draw the chart on.
	 * @param pnts An array of Point objects to use in the charts.
	 * @param cntHistory A list of arrays of Centroid objects to use in the charts.
	 * @param links A boolean flag to draw links between centroids and their nearest points.
	 * @param zones A boolean flag to draw a background of the centroids and their nearest points.
	 * @param labels A boolean flag to draw the IDs of the points and centroids.
	 * @return The provided Canvas object with the newly drawn chart.
	 */
	public Canvas generateChart(Canvas canvas, Point[] pnts, Centroid[] cnt, boolean links, boolean zones, boolean labels) {
		// TODO: write code to redraw chart onto provided canvas
		return canvas;
	}
	
	/**
	 * This method generates a new Canvas object and draws a chart on it.
	 * @param xSize An integer of the width of the chart.
	 * @param ySize An integer of the height of the chart.
	 * @param pnts An array of Point objects to use in the charts.
	 * @param cntHistory A list of arrays of Centroid objects to use in the charts.
	 * @param links A boolean flag to draw links between centroids and their nearest points.
	 * @param zones A boolean flag to draw a background of the centroids and their nearest points.
	 * @param labels A boolean flag to draw the IDs of the points and centroids.
	 * @return The generated Canvas ofject with the newly drawn chart.
	 */
	public Canvas generateChart(int xSize, int ySize, Point[] pnts, Centroid[] cnt, boolean links, boolean zones, boolean labels) {
		// TODO: write code to generate new canvas and then call other provided method.
		return new Canvas();
	}
}
