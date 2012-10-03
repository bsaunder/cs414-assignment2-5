package cs414.as4.btsaunde.garagesystem.view;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

import cs414.as4.btsaunde.garagesystem.action.PayForTicketAction;
import cs414.as4.btsaunde.garagesystem.action.RetrieveTicketAction;
import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;

public class StartPanel extends JPanel {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * UI Compnents
	 */
	private JButton btnRetrieveTicket;

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		this.setBounds(new Rectangle(0, 0, 485, 311));
		this.setLayout(null);

		this.btnRetrieveTicket = new JButton();
		this.btnRetrieveTicket.setBounds(132, 39, 220, 75);
		this.btnRetrieveTicket.setAction(new RetrieveTicketAction());
		this.btnRetrieveTicket.setText("Retrieve Ticket");
		this.add(this.btnRetrieveTicket);

		JButton btnPayForTicket = new JButton();
		btnPayForTicket.setBounds(132, 198, 220, 75);
		btnPayForTicket.setAction(new PayForTicketAction());
		btnPayForTicket.setText("Pay For Ticket");
		this.add(btnPayForTicket);
	}

	/**
	 * Enables/Disalbes Buttons Based on Garage Status.
	 */
	public void update() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		
		if (config.getStatus() == GarageStatus.OPEN) {
			this.btnRetrieveTicket.setEnabled(true);
		}else{
			this.btnRetrieveTicket.setEnabled(false);
		}
	}

}
