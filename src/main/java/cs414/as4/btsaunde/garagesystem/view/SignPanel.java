package cs414.as4.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;

public class SignPanel extends JPanel {
	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Garage Status Label.
	 */
	private JLabel lblStatus;

	/**
	 * Create the panel.
	 */
	public SignPanel() {
		setLayout(new BorderLayout(0, 0));

		this.lblStatus = new JLabel("CLOSED");
		this.lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 46));
		add(this.lblStatus);

	}

	/**
	 * Updates the Sign to Display the Current Garage Status.
	 */
	public void updateSign() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		this.lblStatus.setText(config.getStatus().toString());
	}

}
