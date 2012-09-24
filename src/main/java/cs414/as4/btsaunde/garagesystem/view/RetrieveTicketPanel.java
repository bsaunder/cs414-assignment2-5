package cs414.as4.btsaunde.garagesystem.view;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

import cs414.as4.btsaunde.garagesystem.manager.TicketManager;
import cs414.as4.btsaunde.garagesystem.model.Ticket;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RetrieveTicketPanel extends JPanel implements ActionListener {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * Take Ticket Action Command
	 */
	private static final String TAKE_TICKET_COMMAND = "TakeTicket";
	private JLabel lblTicketIdValue;

	/**
	 * Create the panel.
	 */
	public RetrieveTicketPanel() {
		this.setBounds(new Rectangle(0, 0, 485, 311));
		this.setLayout(new CardLayout(0, 0));

		this.buildTakeTicketPanel();
		this.buildTicketDispensedPanel();
	}

	/**
	 * Builds Take Ticket Panel
	 */
	private void buildTakeTicketPanel() {
		JPanel startPanel = new JPanel();
		this.add(startPanel, "Start");
		startPanel.setLayout(null);

		JButton btnTakeTicket = new JButton("Take Ticket");
		btnTakeTicket.setActionCommand(RetrieveTicketPanel.TAKE_TICKET_COMMAND);
		btnTakeTicket.addActionListener(this);
		btnTakeTicket.setBounds(197, 141, 90, 28);
		startPanel.add(btnTakeTicket);
	}
	
	/**
	 * Builds Ticket Dispensed Panel
	 */
	private void buildTicketDispensedPanel() {
		JPanel startPanel = new JPanel();
		this.add(startPanel, "Dispensed");
		startPanel.setLayout(null);
		
		JLabel lblPleaseTakeYour = new JLabel("Please Take Your Ticket From the Dispenser.");
		lblPleaseTakeYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseTakeYour.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblPleaseTakeYour.setBounds(6, 44, 473, 60);
		startPanel.add(lblPleaseTakeYour);
		
		JLabel lblTicketId = new JLabel("Ticket ID");
		lblTicketId.setBounds(215, 110, 55, 16);
		startPanel.add(lblTicketId);
		
		this.lblTicketIdValue = new JLabel("XXXXX");
		this.lblTicketIdValue.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTicketIdValue.setBounds(6, 126, 473, 16);
		startPanel.add(this.lblTicketIdValue);

		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		if (actionCommand.equals(RetrieveTicketPanel.TAKE_TICKET_COMMAND)) {
			this.logger.info("Generating Ticket");

			TicketManager ticketManager = TicketManager.getInstance();
			Ticket ticket = ticketManager.createNewTicket();

			// Move to Next Card
			this.lblTicketIdValue.setText(ticket.getTicketId());
			
			CardLayout layout = (CardLayout) this.getLayout();
			layout.next(this);

			DashboardWindow dashboard = DashboardWindow.getInstance();
			
			// Open Gate
			dashboard.getGatePanel().openGate();

			// Refresh Sign
			dashboard.refreshFromConfig();

			// User Drives Through Gate
		}
	}
}
