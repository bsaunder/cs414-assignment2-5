package cs414.as4.btsaunde.garagesystem.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.manager.TicketManager;
import cs414.as4.btsaunde.garagesystem.model.Gate;
import cs414.as4.btsaunde.garagesystem.model.Sign;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Retrieve Ticket Panel that Simulates the Entrance Panel.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
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

	/*
	 * UI Components
	 */
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
		btnTakeTicket.setBounds(171, 132, 143, 47);
		startPanel.add(btnTakeTicket);
	}

	/**
	 * Builds Ticket Dispensed Panel
	 */
	private void buildTicketDispensedPanel() {
		JPanel dispensedPanel = new JPanel();
		this.add(dispensedPanel, "Dispensed");
		dispensedPanel.setLayout(null);

		JLabel lblPleaseTakeYour = new JLabel(
				"Please Take Your Ticket From the Dispenser.");
		lblPleaseTakeYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseTakeYour.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblPleaseTakeYour.setBounds(6, 44, 473, 60);
		dispensedPanel.add(lblPleaseTakeYour);

		JLabel lblTicketId = new JLabel("Ticket ID");
		lblTicketId.setBounds(215, 110, 55, 16);
		dispensedPanel.add(lblTicketId);

		this.lblTicketIdValue = new JLabel("XXXXX");
		this.lblTicketIdValue.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTicketIdValue.setBounds(6, 126, 473, 16);
		dispensedPanel.add(this.lblTicketIdValue);

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

			// Update Garage Status
			GarageConfiguration config = GarageConfiguration.getInstance();
			if (config.getStatus() != GarageStatus.CLOSED) {
				if (config.getAvailableSpaces() < 1) {
					config.setStatus(GarageStatus.FULL);
				} else {
					config.setStatus(GarageStatus.OPEN);
				}
			}

			// Open Gate
			Gate gate = config.getGate();
			gate.openGate();

			// Update Sign
			Sign sign = config.getSign();
			sign.setText(config.getStatus().toString());

			// Refresh Dashboard
			DashboardWindow dashboard = DashboardWindow.getInstance();
			dashboard.update();

			// User Drives Through Gate
		}
	}
}
