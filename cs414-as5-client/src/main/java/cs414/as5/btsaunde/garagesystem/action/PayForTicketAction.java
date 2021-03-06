package cs414.as5.btsaunde.garagesystem.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cs414.as5.btsaunde.garagesystem.view.DashboardWindow;

/**
 * Starts Pay For Ticket Action.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class PayForTicketAction extends AbstractAction {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		DashboardWindow dashboard = DashboardWindow.getInstance();
		dashboard.startPayForTicket();
	}

}
