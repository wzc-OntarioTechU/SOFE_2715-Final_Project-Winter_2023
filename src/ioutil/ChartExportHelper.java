package ioutil;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
		Color[] temp = new Color[8];
		temp[0] = Color.BLUE;
		temp[1] = Color.CYAN;
		temp[2] = Color.GREEN;
		temp[3] = Color.MAGENTA;
		temp[4] = Color.ORANGE;
		temp[5] = Color.PINK;
		temp[6] = Color.RED;
		temp[7] = Color.YELLOW;
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
	 * @throws IOException 
	 */
	public void exportAsCharts(File dest, Point[] pnts, List<Centroid[]> cntHistory, boolean links, boolean zones, boolean labels) throws IOException {
		for(int i = 1; i <= cntHistory.size(); i++) {
			File f = new File(dest.getPath() + "(" + i + ").png");
			Canvas can = generateChart(800,800, pnts, cntHistory.get(i - 1), links, zones, labels);
			BufferedImage img = new BufferedImage(can.getWidth(), can.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = img.getGraphics();
			can.paint(g);
			ImageIO.write(img, "png", f);
		}
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
	public Canvas generateChart(Canvas canvas, Point[] pnts, Centroid[] cnts, boolean links, boolean zones, boolean labels) {
		canvas.setBackground(Color.WHITE);
		// find scaling factors
		double xScl, xOff, yScl, yOff;
		double low = Double.MAX_VALUE, high = Double.MIN_VALUE;
		for(Point pnt : pnts) {
			if(pnt.getX() < low)
				low = pnt.getX();
			if(pnt.getX() > high)
				high = pnt.getX();
		}
		for(Point pnt : cnts) {
			if(pnt.getX() < low)
				low = pnt.getX();
			if(pnt.getX() > high)
				high = pnt.getX();
		}
		xScl = canvas.getWidth() / (high - low);
		xOff = (0 - low) * xScl;
		low = Double.MAX_VALUE;
		high = Double.MIN_VALUE;
		for(Point pnt : pnts) {
			if(pnt.getY() < low)
				low = pnt.getY();
			if(pnt.getY() > high)
				high = pnt.getY();
		}
		for(Point pnt : cnts) {
			if(pnt.getY() < low)
				low = pnt.getY();
			if(pnt.getY() > high)
				high = pnt.getY();
		}
		yScl = canvas.getWidth() / (high - low);
		yOff = (0 - low) * yScl;
		
		// zones will likely be unimplemented
		Graphics g = canvas.getGraphics();
		g.clearRect(0, 0, canvas.getWidth(), canvas.getWidth());
		if(zones) {
		}
		
		//now for the points
		g.setColor(Color.BLACK);
		for(Point pnt : pnts) {
			int x = (int) ((pnt.getX() - xOff) * xScl);
			int y = (int) ((pnt.getY() - yOff) * yScl);
			g.fillOval(x - 2, y - 2, 4, 4);
		}
		
		//now for the centroids
		for(int i = 0; i < cnts.length; i++) {
			g.setColor(PALETTE[i % (PALETTE.length - 1)]);
			int x = (int) ((cnts[i].getX() - xOff) * xScl);
			int y = (int) ((cnts[i].getY() - yOff) * yScl);
			g.fillRect(x - 2, y - 2, 4, 4);
		}
		
		//now for the links
		if(links) {
			for(int i = 0; i < cnts.length; i++) {
				g.setColor(PALETTE[i % (PALETTE.length - 1)]);
				int x = (int) ((cnts[i].getX() - xOff) * xScl);
				int y = (int) ((cnts[i].getY() - yOff) * yScl);
				for(Point pnt : cnts[i].getOwned()) {
					int x1 = (int) ((pnt.getX() - xOff) * xScl);
					int y1 = (int) ((pnt.getY() - yOff) * yScl);
					g.drawLine(x1, y1, x, y);
				}
			}
		}
		
		//now for the lables, leave unimplemented for now
		if(labels) {
		}
		
		canvas.paint(g);
		
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
		Canvas temp = new Canvas();
		temp.setSize(xSize, ySize);
		return generateChart(temp, pnts, cnt, links, zones, labels);
	}
}
