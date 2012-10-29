package cs414.as5.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs414.as5.btsaunde.garagesystem.enums.ReportType;
import cs414.as5.btsaunde.garagesystem.service.ReportService;
import cs414.as5.btsaunde.garagesystem.view.model.DayComboBoxModel;
import cs414.as5.btsaunde.garagesystem.view.model.MonthComboBoxModel;
import cs414.as5.btsaunde.garagesystem.view.model.YearComboBoxModel;

/**
 * Dialog for Building Reports.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ReportBuilder extends JDialog implements ActionListener {

	/**
	 * Cancel Command
	 */
	private static final String CANCEL_COMMAND = "Cancel";

	/**
	 * OK Command
	 */
	private static final String OK_COMMAND = "OK";

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * UI Compnents
	 */
	private JPanel contentPanel;
	private JComboBox endYearComboBox;
	private JComboBox endDayComboBox;
	private JComboBox endMonthComboBox;
	private JComboBox startYearComboBox;
	private JComboBox startDayComboBox;
	private JComboBox startMonthComboBox;
	private JComboBox reportTypeComboBox;

	/**
	 * Create the dialog.
	 */
	public ReportBuilder() {
		setTitle("Report Generator");
		this.initalize();
		this.build();
		this.buildButtonPanel();
	}

	/**
	 * Initialize Dialog
	 */
	public void initalize() {
		this.setBounds(100, 100, 243, 300);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
	}

	/**
	 * Build Main Panel
	 */
	public void build() {
		this.contentPanel = new JPanel();
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPanel.setLayout(null);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(6, 6, 84, 16);
		this.contentPanel.add(lblStartDate);

		startMonthComboBox = new JComboBox();
		startMonthComboBox.setModel(new MonthComboBoxModel());
		startMonthComboBox.setSelectedItem("1");
		startMonthComboBox.setBounds(19, 29, 55, 26);
		this.contentPanel.add(startMonthComboBox);

		JLabel lblstartMonth = new JLabel("M:");
		lblstartMonth.setBounds(6, 34, 17, 16);
		this.contentPanel.add(lblstartMonth);

		startDayComboBox = new JComboBox();
		startDayComboBox.setModel(new DayComboBoxModel());
		startDayComboBox.setBounds(95, 29, 53, 26);
		startDayComboBox.setSelectedItem("1");
		this.contentPanel.add(startDayComboBox);

		JLabel lblStartDay = new JLabel("D:");
		lblStartDay.setBounds(82, 34, 17, 16);
		this.contentPanel.add(lblStartDay);

		startYearComboBox = new JComboBox();
		startYearComboBox.setModel(new YearComboBoxModel());
		startYearComboBox.setSelectedItem("2012");
		startYearComboBox.setBounds(164, 29, 69, 26);
		this.contentPanel.add(startYearComboBox);

		JLabel lblStartYear = new JLabel("Y:");
		lblStartYear.setBounds(151, 34, 17, 16);
		this.contentPanel.add(lblStartYear);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(6, 82, 84, 16);
		contentPanel.add(lblEndDate);

		JLabel lblEndMonth = new JLabel("M:");
		lblEndMonth.setBounds(6, 110, 17, 16);
		contentPanel.add(lblEndMonth);

		endMonthComboBox = new JComboBox();
		endMonthComboBox.setModel(new MonthComboBoxModel());
		endMonthComboBox.setSelectedItem("1");
		endMonthComboBox.setBounds(19, 105, 55, 26);
		contentPanel.add(endMonthComboBox);

		JLabel lblEndDay = new JLabel("D:");
		lblEndDay.setBounds(82, 110, 17, 16);
		contentPanel.add(lblEndDay);

		endDayComboBox = new JComboBox();
		endDayComboBox.setModel(new DayComboBoxModel());
		endDayComboBox.setSelectedItem("1");
		endDayComboBox.setBounds(95, 105, 53, 26);
		contentPanel.add(endDayComboBox);

		JLabel lblEndYear = new JLabel("Y:");
		lblEndYear.setBounds(151, 110, 17, 16);
		contentPanel.add(lblEndYear);

		endYearComboBox = new JComboBox();
		endYearComboBox.setModel(new YearComboBoxModel());
		endYearComboBox.setSelectedItem("2012");
		endYearComboBox.setBounds(164, 105, 69, 26);
		contentPanel.add(endYearComboBox);

		reportTypeComboBox = new JComboBox();
		reportTypeComboBox.setModel(new DefaultComboBoxModel(ReportType
				.values()));
		reportTypeComboBox.setBounds(19, 181, 201, 25);
		contentPanel.add(reportTypeComboBox);

		JLabel lblReportType = new JLabel("Report Type");
		lblReportType.setBounds(6, 159, 84, 16);
		contentPanel.add(lblReportType);
	}

	/**
	 * Build Button Panel
	 */
	public void buildButtonPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Generate Report");
		okButton.setActionCommand(OK_COMMAND);
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand(CANCEL_COMMAND);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
	}

	/**
	 * Triggered by Actions bing Performed.
	 */
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if (command.equals(CANCEL_COMMAND)) {
			this.dispose();
		} else if (command.equals(OK_COMMAND)) {
			// Generate Report

			// Build Start Date
			String startDay = this.startDayComboBox.getSelectedItem()
					.toString();
			String startMonth = this.startMonthComboBox.getSelectedItem()
					.toString();
			String startYear = this.startYearComboBox.getSelectedItem()
					.toString();

			Calendar start = Calendar.getInstance();
			start.set(Calendar.DAY_OF_MONTH, Integer.valueOf(startDay));
			start.set(Calendar.MONTH, Integer.valueOf(startMonth) - 1);
			start.set(Calendar.YEAR, Integer.valueOf(startYear));

			// Build End Date
			String endDay = this.endDayComboBox.getSelectedItem().toString();
			String endMonth = this.endMonthComboBox.getSelectedItem()
					.toString();
			String endYear = this.endYearComboBox.getSelectedItem().toString();

			Calendar end = Calendar.getInstance();
			end.set(Calendar.DAY_OF_MONTH, Integer.valueOf(endDay));
			end.set(Calendar.MONTH, Integer.valueOf(endMonth) - 1);
			end.set(Calendar.YEAR, Integer.valueOf(endYear));

			this.logger.info("Generating Report From " + start + " To " + end);

			// Call Appropriate Report Service
			ReportType type = (ReportType) this.reportTypeComboBox
					.getSelectedItem();
			this.logger.info("Report Type: " + type.toString());
			switch (type) {
			case AVG_DAY:
				Map<String, Double> avgDayResults = ReportService
						.getAveragePerDay(start, end);
				this.displayReport(type, avgDayResults);
				break;
			case AVG_STAY_DAY:
				Map<String, Double> avgStayDayResults = ReportService
						.getAverageStayPerDay(start, end);
				this.displayReport(type, avgStayDayResults);
				break;
			case MOST_DAY:
				Calendar busiestDay = ReportService.getBusiestDay(start, end);
				Format formatter = new SimpleDateFormat("EEEE MM/dd/yy");

				JOptionPane.showMessageDialog(null, "The Busiest Day Was: "
						+ formatter.format(busiestDay.getTime()), type.toString(),
						JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}

	/**
	 * Displays the Results of a Report
	 * 
	 * @param type
	 *            Report Type
	 * @param results
	 *            Report Results
	 */
	private void displayReport(ReportType type, Map<String, Double> results) {
		StringBuilder builder = new StringBuilder();
		builder.append("Report: " + type.toString() + "\n");
		builder.append("\n");
		DecimalFormat formatter = new DecimalFormat("#,###,###,##0.00");
		for (Entry<String, Double> entry : results.entrySet()) {
			builder.append(entry.getKey() + " = "
					+ formatter.format(entry.getValue()));
			builder.append("\n");
		}

		JOptionPane.showMessageDialog(null, builder.toString(),
				type.toString(), JOptionPane.INFORMATION_MESSAGE);
	}
}
