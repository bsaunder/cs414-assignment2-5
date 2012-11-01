package cs414.as5.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs414.as5.btsaunde.garagesystem.config.KioskConfiguration;
import cs414.as5.btsaunde.garagesystem.model.Sign;

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
	public void update() {
		KioskConfiguration config = KioskConfiguration.getInstance();
		Sign sign = config.getSign();

		this.lblStatus.setText(sign.getText());
	}

}
