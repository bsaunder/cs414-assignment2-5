package cs414.as4.btsaunde.garagesystem.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cs414.as4.btsaunde.garagesystem.manager.TicketManager;

public class PayForTicketPanel extends JPanel implements ActionListener {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Enter Ticket Command
	 */
	private static final String ENTER_TICKET_COMMAND = "EnterTicket";

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * UI Components
	 */
	private JTextField txtTicketId;

	/**
	 * Create the panel.
	 */
	public PayForTicketPanel() {
		this.setBounds(new Rectangle(0, 0, 485, 311));
		this.setLayout(new CardLayout(0, 0));

		this.buildInsertTicketPanel();
		this.buildTakePaymentPanel();
	}

	/**
	 * Builds Take Payment Panel
	 */
	private void buildTakePaymentPanel() {
		JPanel paymentPanel = new JPanel();
		this.add(paymentPanel, "Payment");
		paymentPanel.setLayout(null);

	}

	/**
	 * Builds Take Ticket Panel
	 */
	private void buildInsertTicketPanel() {
		JPanel startPanel = new JPanel();
		this.add(startPanel, "Start");
		startPanel.setLayout(null);

		JLabel lblEnterTicketId = new JLabel("Enter Ticket ID");
		lblEnterTicketId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTicketId.setFont(new Font("Tahoma", Font.PLAIN, 46));
		lblEnterTicketId.setBounds(10, 37, 465, 62);
		startPanel.add(lblEnterTicketId);

		this.txtTicketId = new JTextField();
		this.txtTicketId.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtTicketId.setFont(new Font("SansSerif", Font.PLAIN, 26));
		this.txtTicketId.setBounds(128, 104, 228, 42);
		startPanel.add(this.txtTicketId);
		this.txtTicketId.setColumns(10);

		JButton btnSubmitTicket = new JButton("Submit Ticket");
		btnSubmitTicket.addActionListener(this);
		btnSubmitTicket.setActionCommand(ENTER_TICKET_COMMAND);
		btnSubmitTicket.setBounds(187, 157, 111, 23);
		startPanel.add(btnSubmitTicket);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		// TODO Refactor This into a Seperate Controller Class

		String actionCommand = ae.getActionCommand();
		if (actionCommand.equals(PayForTicketPanel.ENTER_TICKET_COMMAND)) {
			String ticketId = this.txtTicketId.getText();
			this.logger.info("Entered Ticket - " + ticketId);

			// Validate Ticket ID
			TicketManager ticketManager = TicketManager.getInstance();
			if (ticketManager.verifyTicketId(ticketId)) {
				// If Valid, Move to Next Screen
				CardLayout layout = (CardLayout) this.getLayout();
				layout.show(this, "Payment");
			} else {
				// Not Valid, Error
				this.logger.warning("Invalid Ticket ID (" + ticketId
						+ ") Entered");
				JOptionPane.showMessageDialog(this, ticketId
						+ " Is Not Valid. Please Enter a Valid Ticket ID.",
						"Ticket ID Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
