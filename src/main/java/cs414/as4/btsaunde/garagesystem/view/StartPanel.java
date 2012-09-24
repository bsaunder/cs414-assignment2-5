package cs414.as4.btsaunde.garagesystem.view;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

import cs414.as4.btsaunde.garagesystem.action.RetrieveTicketAction;

public class StartPanel extends JPanel {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		this.setBounds(new Rectangle(0, 0, 485, 311));
		this.setLayout(null);

		JButton btnRetrieveTicket = new JButton();
		btnRetrieveTicket.setBounds(132, 39, 220, 75);
		btnRetrieveTicket.setAction(new RetrieveTicketAction());
		btnRetrieveTicket.setText("Retrieve Ticket");
		this.add(btnRetrieveTicket);

		JButton btnPayForTicket = new JButton("Pay For Ticket");
		btnPayForTicket.setBounds(132, 198, 220, 75);
		this.add(btnPayForTicket);

	}

}
