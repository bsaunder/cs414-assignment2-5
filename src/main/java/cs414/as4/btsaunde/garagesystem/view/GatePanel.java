package cs414.as4.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GatePanel extends JPanel implements ActionListener {

	private static final Color OPEN_COLOR = new Color(0, 128, 0);

	private static final Color CLOSED_COLOR = new Color(128, 0, 0);

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Drive Through Action Command
	 */
	private static final String DRIVE_THROUGH_COMMAND = "DriveThrough";

	/*
	 * Panel Components
	 */
	private JButton btnDriveThroughGate;
	private JLabel lblStatus;

	/**
	 * Create the panel.
	 */
	public GatePanel() {
		setLayout(new BorderLayout(0, 0));

		this.btnDriveThroughGate = new JButton("Drive Through Gate");
		this.btnDriveThroughGate.setEnabled(false);
		this.btnDriveThroughGate
				.setActionCommand(GatePanel.DRIVE_THROUGH_COMMAND);
		add(this.btnDriveThroughGate, BorderLayout.SOUTH);

		this.lblStatus = new JLabel("Gate is CLOSED");
		this.lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 26));
		this.lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblStatus.setForeground(GatePanel.CLOSED_COLOR);
		add(this.lblStatus, BorderLayout.NORTH);

	}

	/**
	 * Opens the Gate.
	 */
	public void openGate() {
		this.btnDriveThroughGate.setEnabled(true);
		this.lblStatus.setText("Gate is Open");
		this.lblStatus.setForeground(GatePanel.OPEN_COLOR);
	}

	/**
	 * Closes the Gate.
	 */
	private void closeGate() {
		this.btnDriveThroughGate.setEnabled(false);
		this.lblStatus.setText("Gate is Closed");
		this.lblStatus.setForeground(GatePanel.CLOSED_COLOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		if (actionCommand.equals(GatePanel.DRIVE_THROUGH_COMMAND)) {
			this.closeGate();
		}
	}

}
