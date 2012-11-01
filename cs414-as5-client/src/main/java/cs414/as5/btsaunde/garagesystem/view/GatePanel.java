package cs414.as5.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs414.as5.btsaunde.garagesystem.config.KioskConfiguration;
import cs414.as5.btsaunde.garagesystem.model.Gate;

/**
 * Gate Panel that Simulates the Gate.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GatePanel extends JPanel implements ActionListener {

	/**
	 * Open Gate Color
	 */
	private static final Color OPEN_COLOR = new Color(0, 128, 0);

	/**
	 * Closed Gate Color
	 */
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
		this.btnDriveThroughGate.addActionListener(this);
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
	public void closeGate() {
		this.btnDriveThroughGate.setEnabled(false);
		this.lblStatus.setText("Gate is Closed");
		this.lblStatus.setForeground(GatePanel.CLOSED_COLOR);
	}

	/**
	 * Updates the Panel
	 */
	public void update() {
		try {
			KioskConfiguration config = KioskConfiguration.getInstance();
			Gate gate = config.getGate();

			if (gate.isOpen()) {
				this.openGate();
			} else {
				this.closeGate();
			}
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Error Contacting the Server.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			String actionCommand = ae.getActionCommand();
			if (actionCommand.equals(GatePanel.DRIVE_THROUGH_COMMAND)) {
				// Close Gate
				KioskConfiguration config = KioskConfiguration.getInstance();
				Gate gate = config.getGate();
				gate.closeGate();
				this.closeGate();

				// Reset Dashboard
				DashboardWindow dashboard = DashboardWindow.getInstance();
				dashboard.reset();
			}
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Error Contacting the Server.");
		}
	}

}
