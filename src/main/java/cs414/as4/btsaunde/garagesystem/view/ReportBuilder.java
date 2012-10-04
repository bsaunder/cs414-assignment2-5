package cs414.as4.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Dialog for Building Reports.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ReportBuilder extends JDialog implements ActionListener {

	private static final String CANCEL_COMMAND = "Cancel";
	private static final String OK_COMMAND = "OK";
	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * UI Compnents
	 */
	private JPanel contentPanel;

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
		this.setBounds(100, 100, 450, 300);
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
		lblStartDate.setBounds(6, 6, 55, 16);
		this.contentPanel.add(lblStartDate);

		JComboBox startMonthComboBox = new JComboBox();
		startMonthComboBox.setBounds(19, 29, 46, 26);
		this.contentPanel.add(startMonthComboBox);

		JLabel lblM = new JLabel("M:");
		lblM.setBounds(6, 34, 17, 16);
		this.contentPanel.add(lblM);

		JComboBox startDayComboBox = new JComboBox();
		startDayComboBox.setModel(new DayComboBoxModel());
		startDayComboBox.setBounds(86, 29, 46, 26);
		this.contentPanel.add(startDayComboBox);

		JLabel lblD = new JLabel("D:");
		lblD.setBounds(73, 34, 17, 16);
		this.contentPanel.add(lblD);

		JComboBox startYearComboBox = new JComboBox();
		startYearComboBox.setBounds(151, 29, 69, 26);
		this.contentPanel.add(startYearComboBox);

		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(138, 34, 17, 16);
		this.contentPanel.add(lblY);
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
		}
	}
}
