package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Canvas;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class MainWindow {

	private JFrame frmKMeansClustering;
	private JTextField filePathTextField;
	private final ButtonGroup genRadioBtns = new ButtonGroup();
	private final Action openFilePicker = new SwingAction();
	private final Action runProcess = new SwingAction_1();
	private final Action exportCsv = new SwingAction_2();
	private final Action exportCharts = new SwingAction_3();
	private final Action prevSwitch = new SwingAction_4();
	private final Action nextSwitch = new SwingAction_5();

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
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		Canvas canvas = new Canvas();
		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.gridheight = 7;
		gbc_canvas.insets = new Insets(0, 0, 0, 5);
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
		
		JButton chooseFileBtn = new JButton(openFilePicker);
		chooseFileBtn.setName("Open File");
		chooseFileBtn.setText("Choose File");
		GridBagConstraints gbc_chooseFileBtn = new GridBagConstraints();
		gbc_chooseFileBtn.gridwidth = 2;
		gbc_chooseFileBtn.insets = new Insets(0, 0, 5, 0);
		gbc_chooseFileBtn.gridx = 3;
		gbc_chooseFileBtn.gridy = 1;
		frmKMeansClustering.getContentPane().add(chooseFileBtn, gbc_chooseFileBtn);
		
		JSpinner startColSpinner = new JSpinner();
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
		
		JSpinner endColSpinner = new JSpinner();
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
		
		JRadioButton kppRadioBtn = new JRadioButton("K++ Start");
		genRadioBtns.add(kppRadioBtn);
		GridBagConstraints gbc_kppRadioBtn = new GridBagConstraints();
		gbc_kppRadioBtn.gridwidth = 2;
		gbc_kppRadioBtn.insets = new Insets(0, 0, 5, 0);
		gbc_kppRadioBtn.gridx = 3;
		gbc_kppRadioBtn.gridy = 3;
		frmKMeansClustering.getContentPane().add(kppRadioBtn, gbc_kppRadioBtn);
		
		JButton exportCsvBtn = new JButton("Export csv Results");
		exportCsvBtn.setAction(exportCsv);
		exportCsvBtn.setText("Export csv Results");
		
		JSpinner centroidsSpinner = new JSpinner();
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
		startBtn.setAction(runProcess);
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
		exportChartsBtn.setAction(exportCharts);
		exportChartsBtn.setText("Export Charts");
		GridBagConstraints gbc_exportChartsBtn = new GridBagConstraints();
		gbc_exportChartsBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_exportChartsBtn.gridwidth = 2;
		gbc_exportChartsBtn.insets = new Insets(0, 0, 5, 0);
		gbc_exportChartsBtn.gridx = 3;
		gbc_exportChartsBtn.gridy = 6;
		frmKMeansClustering.getContentPane().add(exportChartsBtn, gbc_exportChartsBtn);
		
		JButton prevBtn = new JButton("Previous Iteration");
		prevBtn.setAction(prevSwitch);
		prevBtn.setText("Previous Iteration");
		GridBagConstraints gbc_prevBtn = new GridBagConstraints();
		gbc_prevBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_prevBtn.gridwidth = 2;
		gbc_prevBtn.insets = new Insets(0, 0, 0, 5);
		gbc_prevBtn.gridx = 1;
		gbc_prevBtn.gridy = 7;
		frmKMeansClustering.getContentPane().add(prevBtn, gbc_prevBtn);
		
		JButton nextBtn = new JButton("Next Iteration");
		nextBtn.setAction(nextSwitch);
		nextBtn.setText("Next Iteration");
		GridBagConstraints gbc_nextBtn = new GridBagConstraints();
		gbc_nextBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nextBtn.gridwidth = 2;
		gbc_nextBtn.gridx = 3;
		gbc_nextBtn.gridy = 7;
		frmKMeansClustering.getContentPane().add(nextBtn, gbc_nextBtn);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "SwingAction_3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "SwingAction_4");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "SwingAction_5");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
