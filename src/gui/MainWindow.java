package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Canvas;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import comp.Centroid;
import comp.MapSpace;
import comp.Point;
import ioutil.ChartExportHelper;
import ioutil.SpreadSheetHelper;

import java.awt.Font;

public class MainWindow {

	private JFrame frmKMeansClustering;
	private JTextField filePathTextField;
	private JSpinner startColSpinner;
	private JSpinner endColSpinner;
	private JSpinner centroidsSpinner;
	private JRadioButton kppRadioBtn;
	private Canvas canvas;
	private int current;
	private final ButtonGroup genRadioBtns = new ButtonGroup();
	private List<Centroid[]> cntHist;
	private MapSpace currProcess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmKMeansClustering.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKMeansClustering = new JFrame();
		frmKMeansClustering.setTitle("k-means clustering");
		frmKMeansClustering.setBounds(100, 100, 918, 588);
		frmKMeansClustering.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{501, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmKMeansClustering.getContentPane().setLayout(gridBagLayout);
		
		JLabel canvasTitle = new JLabel("Canvas Title");
		canvasTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		canvasTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_canvasTitle = new GridBagConstraints();
		gbc_canvasTitle.fill = GridBagConstraints.BOTH;
		gbc_canvasTitle.insets = new Insets(0, 0, 5, 5);
		gbc_canvasTitle.gridx = 0;
		gbc_canvasTitle.gridy = 0;
		frmKMeansClustering.getContentPane().add(canvasTitle, gbc_canvasTitle);
		
		JLabel settingsLabel = new JLabel("Options");
		settingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_settingsLabel = new GridBagConstraints();
		gbc_settingsLabel.gridwidth = 4;
		gbc_settingsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_settingsLabel.gridx = 1;
		gbc_settingsLabel.gridy = 0;
		frmKMeansClustering.getContentPane().add(settingsLabel, gbc_settingsLabel);
		
		canvas = new Canvas();
		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.gridheight = 7;
		gbc_canvas.insets = new Insets(0, 0, 5, 5);
		gbc_canvas.gridx = 0;
		gbc_canvas.gridy = 1;
		frmKMeansClustering.getContentPane().add(canvas, gbc_canvas);
		
		filePathTextField = new JTextField();
		GridBagConstraints gbc_filePathTextField = new GridBagConstraints();
		gbc_filePathTextField.gridwidth = 2;
		gbc_filePathTextField.insets = new Insets(0, 0, 5, 5);
		gbc_filePathTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_filePathTextField.gridx = 1;
		gbc_filePathTextField.gridy = 1;
		frmKMeansClustering.getContentPane().add(filePathTextField, gbc_filePathTextField);
		filePathTextField.setColumns(10);
		
		JButton chooseFileBtn = new JButton();
		chooseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickFile();
			}
		});
		chooseFileBtn.setName("Open File");
		chooseFileBtn.setText("Choose File");
		GridBagConstraints gbc_chooseFileBtn = new GridBagConstraints();
		gbc_chooseFileBtn.gridwidth = 2;
		gbc_chooseFileBtn.insets = new Insets(0, 0, 5, 0);
		gbc_chooseFileBtn.gridx = 3;
		gbc_chooseFileBtn.gridy = 1;
		frmKMeansClustering.getContentPane().add(chooseFileBtn, gbc_chooseFileBtn);
		
		startColSpinner = new JSpinner();
		startColSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_startColSpinner = new GridBagConstraints();
		gbc_startColSpinner.anchor = GridBagConstraints.EAST;
		gbc_startColSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_startColSpinner.gridx = 1;
		gbc_startColSpinner.gridy = 2;
		frmKMeansClustering.getContentPane().add(startColSpinner, gbc_startColSpinner);
		
		JLabel startColLabel = new JLabel("X Data Column");
		GridBagConstraints gbc_startColLabel = new GridBagConstraints();
		gbc_startColLabel.anchor = GridBagConstraints.WEST;
		gbc_startColLabel.insets = new Insets(0, 0, 5, 5);
		gbc_startColLabel.gridx = 2;
		gbc_startColLabel.gridy = 2;
		frmKMeansClustering.getContentPane().add(startColLabel, gbc_startColLabel);
		
		endColSpinner = new JSpinner();
		endColSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(2), null, Integer.valueOf(1)));
		GridBagConstraints gbc_endColSpinner = new GridBagConstraints();
		gbc_endColSpinner.anchor = GridBagConstraints.EAST;
		gbc_endColSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_endColSpinner.gridx = 3;
		gbc_endColSpinner.gridy = 2;
		frmKMeansClustering.getContentPane().add(endColSpinner, gbc_endColSpinner);
		
		JLabel endColLabel = new JLabel("End Column");
		GridBagConstraints gbc_endColLabel = new GridBagConstraints();
		gbc_endColLabel.anchor = GridBagConstraints.WEST;
		gbc_endColLabel.insets = new Insets(0, 0, 5, 0);
		gbc_endColLabel.gridx = 4;
		gbc_endColLabel.gridy = 2;
		frmKMeansClustering.getContentPane().add(endColLabel, gbc_endColLabel);
		
		JRadioButton randRadioBtn = new JRadioButton("Random Start");
		genRadioBtns.add(randRadioBtn);
		randRadioBtn.setSelected(true);
		GridBagConstraints gbc_randRadioBtn = new GridBagConstraints();
		gbc_randRadioBtn.gridwidth = 2;
		gbc_randRadioBtn.insets = new Insets(0, 0, 5, 5);
		gbc_randRadioBtn.gridx = 1;
		gbc_randRadioBtn.gridy = 3;
		frmKMeansClustering.getContentPane().add(randRadioBtn, gbc_randRadioBtn);
		
		kppRadioBtn = new JRadioButton("K++ Start");
		genRadioBtns.add(kppRadioBtn);
		GridBagConstraints gbc_kppRadioBtn = new GridBagConstraints();
		gbc_kppRadioBtn.gridwidth = 2;
		gbc_kppRadioBtn.insets = new Insets(0, 0, 5, 0);
		gbc_kppRadioBtn.gridx = 3;
		gbc_kppRadioBtn.gridy = 3;
		frmKMeansClustering.getContentPane().add(kppRadioBtn, gbc_kppRadioBtn);
		
		JButton exportCsvBtn = new JButton("Export csv Results");
		exportCsvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportFile();
			}
		});
		exportCsvBtn.setText("Export csv Results");
		
		centroidsSpinner = new JSpinner();
		centroidsSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_centroidsSpinner = new GridBagConstraints();
		gbc_centroidsSpinner.anchor = GridBagConstraints.EAST;
		gbc_centroidsSpinner.gridwidth = 2;
		gbc_centroidsSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_centroidsSpinner.gridx = 1;
		gbc_centroidsSpinner.gridy = 4;
		frmKMeansClustering.getContentPane().add(centroidsSpinner, gbc_centroidsSpinner);
		
		JLabel centroidsLabel = new JLabel("Number of Groups/Centroids");
		GridBagConstraints gbc_centroidsLabel = new GridBagConstraints();
		gbc_centroidsLabel.anchor = GridBagConstraints.WEST;
		gbc_centroidsLabel.gridwidth = 2;
		gbc_centroidsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_centroidsLabel.gridx = 3;
		gbc_centroidsLabel.gridy = 4;
		frmKMeansClustering.getContentPane().add(centroidsLabel, gbc_centroidsLabel);
		
		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runProcess();
			}
		});
		startBtn.setText("Start");
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_startBtn.gridwidth = 4;
		gbc_startBtn.insets = new Insets(0, 0, 5, 0);
		gbc_startBtn.gridx = 1;
		gbc_startBtn.gridy = 5;
		frmKMeansClustering.getContentPane().add(startBtn, gbc_startBtn);
		GridBagConstraints gbc_exportCsvBtn = new GridBagConstraints();
		gbc_exportCsvBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_exportCsvBtn.gridwidth = 2;
		gbc_exportCsvBtn.insets = new Insets(0, 0, 5, 5);
		gbc_exportCsvBtn.gridx = 1;
		gbc_exportCsvBtn.gridy = 6;
		frmKMeansClustering.getContentPane().add(exportCsvBtn, gbc_exportCsvBtn);
		
		JButton exportChartsBtn = new JButton("Export Charts");
		exportChartsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportCharts();
			}
		});
		exportChartsBtn.setText("Export Charts");
		GridBagConstraints gbc_exportChartsBtn = new GridBagConstraints();
		gbc_exportChartsBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_exportChartsBtn.gridwidth = 2;
		gbc_exportChartsBtn.insets = new Insets(0, 0, 5, 0);
		gbc_exportChartsBtn.gridx = 3;
		gbc_exportChartsBtn.gridy = 6;
		frmKMeansClustering.getContentPane().add(exportChartsBtn, gbc_exportChartsBtn);
		
		JButton prevBtn = new JButton("Previous Iteration");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		prevBtn.setText("Previous Iteration");
		GridBagConstraints gbc_prevBtn = new GridBagConstraints();
		gbc_prevBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_prevBtn.gridwidth = 2;
		gbc_prevBtn.insets = new Insets(0, 0, 5, 5);
		gbc_prevBtn.gridx = 1;
		gbc_prevBtn.gridy = 7;
		frmKMeansClustering.getContentPane().add(prevBtn, gbc_prevBtn);
		
		JButton nextBtn = new JButton("Next Iteration");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		nextBtn.setText("Next Iteration");
		GridBagConstraints gbc_nextBtn = new GridBagConstraints();
		gbc_nextBtn.insets = new Insets(0, 0, 5, 0);
		gbc_nextBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nextBtn.gridwidth = 2;
		gbc_nextBtn.gridx = 3;
		gbc_nextBtn.gridy = 7;
		frmKMeansClustering.getContentPane().add(nextBtn, gbc_nextBtn);
		
		JLabel lblNewLabel = new JLabel("Run TIme");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 8;
		frmKMeansClustering.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	}

	private void pickFile() {
		JFileChooser fpicker = new JFileChooser();
		fpicker.setCurrentDirectory(new File(System.getProperty("user.home")));
		fpicker.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fpicker.setFileFilter(new FileNameExtensionFilter("csv","csv"));
		if(fpicker.showOpenDialog(frmKMeansClustering) == JFileChooser.APPROVE_OPTION) {
			File f = fpicker.getSelectedFile();
			filePathTextField.setText(f.toString());
		}
	}

	private void exportFile() {
		JFileChooser fpicker = new JFileChooser();
		fpicker.setCurrentDirectory(new File(System.getProperty("user.home")));
		fpicker.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fpicker.setFileFilter(new FileNameExtensionFilter("csv","csv"));
		if(fpicker.showSaveDialog(frmKMeansClustering) == JFileChooser.APPROVE_OPTION) {
			File f = fpicker.getSelectedFile();
			SpreadSheetHelper.writeResults(f, currProcess.getPoints(), cntHist);
		}
	}
	
	private void exportCharts() {
		JFileChooser fpicker = new JFileChooser();
		fpicker.setCurrentDirectory(new File(System.getProperty("user.home")));
		fpicker.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fpicker.setFileFilter(new FileNameExtensionFilter("png","png"));
		if(fpicker.showSaveDialog(frmKMeansClustering) == JFileChooser.APPROVE_OPTION) {
			File f = fpicker.getSelectedFile();
			ChartExportHelper exp = ChartExportHelper.getInstance();
			try {
				exp.exportAsCharts(f, currProcess.getPoints(), cntHist, true, true, true);
			} catch (IOException e) {
				System.err.println("Error in exporting charts: ");
				e.printStackTrace();
			}
		}
	}
	
	private void runProcess() {
		List<Point> pts = SpreadSheetHelper.readPoints(new File(filePathTextField.getText()), (Integer) startColSpinner.getValue() - 1, (Integer) endColSpinner.getValue() - 1);
		currProcess = new MapSpace(pts.toArray(new Point[1]), (Integer) centroidsSpinner.getValue(), kppRadioBtn.isSelected());
		cntHist = new LinkedList<Centroid[]>();
		currProcess.run(cntHist);
		ChartExportHelper exp = ChartExportHelper.getInstance();
		canvas = exp.generateChart(canvas, pts.toArray(new Point[1]), cntHist.get(cntHist.size() - 1), true, true, true);
	}
	
	private void prev() {
		
	}
	
	private void next() {
		
	}
}
