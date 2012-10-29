package cs414.as5.btsaunde.garagesystem.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.enums.PaymentType;
import cs414.as5.btsaunde.garagesystem.manager.TicketManager;
import cs414.as5.btsaunde.garagesystem.model.Gate;
import cs414.as5.btsaunde.garagesystem.model.Sign;
import cs414.as5.btsaunde.garagesystem.service.PaymentService;

/**
 * Pay For Ticket Panel that Simulates the Exit Terminal
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
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
	 * Pay Cash Command
	 */
	private static final String PAY_CASH_COMMAND = "PayCash";

	/**
	 * Pay Credit Command
	 */
	private static final String PAY_CREDIT_COMMAND = "PayCredit";

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * Payment Service
	 */
	private PaymentService paymentService;

	/*
	 * UI Components
	 */
	private JTextField txtTicketId;
	private JLabel lblTotal;

	/**
	 * Create the panel.
	 */
	public PayForTicketPanel() {
		this.paymentService = new PaymentService();

		this.setBounds(new Rectangle(0, 0, 485, 311));
		this.setLayout(new CardLayout(0, 0));

		this.buildInsertTicketPanel();
		this.buildTakePaymentPanel();
		this.buildPaymentSuccessPanel();
	}

	/**
	 * Builds Payment Success Panel
	 */
	private void buildPaymentSuccessPanel() {
		JPanel paymentPanel = new JPanel();
		this.add(paymentPanel, "PaymentSuccess");
		paymentPanel.setLayout(null);

		JLabel lblPaymentSuccess = new JLabel("Payment Success");
		lblPaymentSuccess.setFont(new Font("SansSerif", Font.PLAIN, 46));
		lblPaymentSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentSuccess.setBounds(35, 46, 414, 59);
		paymentPanel.add(lblPaymentSuccess);

		JLabel lblPleaseExitThe = new JLabel(
				"Please Exit the Garage throgh the Gate");
		lblPleaseExitThe.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPleaseExitThe.setBounds(24, 170, 437, 35);
		paymentPanel.add(lblPleaseExitThe);
	}

	/**
	 * Builds Take Payment Panel
	 */
	private void buildTakePaymentPanel() {
		JPanel paymentPanel = new JPanel();
		this.add(paymentPanel, "Payment");
		paymentPanel.setLayout(null);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblTotal.setBounds(205, 27, 74, 40);
		paymentPanel.add(lblTotal);

		this.lblTotal = new JLabel("$9,999.99");
		this.lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 46));
		this.lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTotal.setBounds(110, 62, 264, 88);
		paymentPanel.add(this.lblTotal);

		JButton btnPayWithCash = new JButton("Pay with Cash");
		btnPayWithCash.addActionListener(this);
		btnPayWithCash.setActionCommand(PayForTicketPanel.PAY_CASH_COMMAND);
		btnPayWithCash.setFont(new Font("SansSerif", Font.PLAIN, 26));
		btnPayWithCash.setBounds(6, 172, 235, 100);
		paymentPanel.add(btnPayWithCash);

		JButton btnPayWithCredit = new JButton("Pay with Credit");
		btnPayWithCredit.addActionListener(this);
		btnPayWithCredit.setActionCommand(PayForTicketPanel.PAY_CREDIT_COMMAND);
		btnPayWithCredit.setFont(new Font("SansSerif", Font.PLAIN, 26));
		btnPayWithCredit.setBounds(244, 172, 235, 100);
		paymentPanel.add(btnPayWithCredit);

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
		btnSubmitTicket
				.setActionCommand(PayForTicketPanel.ENTER_TICKET_COMMAND);
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
		String actionCommand = ae.getActionCommand();
		if (actionCommand.equals(PayForTicketPanel.ENTER_TICKET_COMMAND)) {
			this.enterTicketClicked();
		} else if (actionCommand.equals(PayForTicketPanel.PAY_CASH_COMMAND)) {
			this.payCashClicked();
		} else if (actionCommand.equals(PayForTicketPanel.PAY_CREDIT_COMMAND)) {
			this.payCreditClicked();
		}
	}

	/**
	 * Handles a "Pay by Cash" Click
	 */
	private void payCreditClicked() {
		this.logger.info("Paying with Credit");

		String message = "Enter Credit Card Number.";
		String cardNumber = JOptionPane.showInputDialog(null, message,
				"Enter Card Number", JOptionPane.QUESTION_MESSAGE);

		if (cardNumber != null && !cardNumber.isEmpty()) {
			if (this.paymentService.cardIsValid(cardNumber)) {
				this.logger.info("Valid Card Entered");

				// Charge Card
				String feeString = this.lblTotal.getText().replaceAll(
						"[^\\d.]+", "");
				Double fee = Double.valueOf(feeString);
				this.paymentService.chargeCard(cardNumber, fee);

				// Update Ticket Status
				TicketManager manager = TicketManager.getInstance();
				manager.finalizeTicket(this.txtTicketId.getText(),
						PaymentType.CREDIT, fee, System.currentTimeMillis());

				// Finish Transaction
				this.endTransaction();
			} else {
				this.displayInvalidCashAmountError();
			}
		} else {
			this.logger.warning("No Parking Fee Entered");
			JOptionPane.showMessageDialog(null, "Must Enter a Parking Fee.",
					"Set Parking Fee Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Handles a Pay by Credit" Click
	 */
	private void payCashClicked() {
		this.logger.info("Paying with Cash");

		String message = "Enter Cash Amount, Exact Change Only.";
		String amountString = JOptionPane.showInputDialog(null, message,
				"Enter Cash Amount", JOptionPane.QUESTION_MESSAGE);

		if (amountString != null && !amountString.isEmpty()) {
			try {
				Double amount = Double.valueOf(amountString);
				String feeString = this.lblTotal.getText().replaceAll(
						"[^\\d.]+", "");
				Double fee = Double.valueOf(feeString);
				if (amount.equals(fee)) {
					this.logger.info("Correct Change Entered");

					// Update Ticket Status
					TicketManager manager = TicketManager.getInstance();
					manager.finalizeTicket(this.txtTicketId.getText(),
							PaymentType.CASH, fee, System.currentTimeMillis());

					// Finish Transaction
					this.endTransaction();
				} else {
					this.displayInvalidCashAmountError();
				}
			} catch (NumberFormatException nfe) {
				this.displayInvalidCashAmountError();
			}
		} else {
			this.logger.warning("No Parking Fee Entered");
			JOptionPane.showMessageDialog(null, "Must Enter a Parking Fee.",
					"Set Parking Fee Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Finalizes the Transaction
	 */
	private void endTransaction() {
		// Move to Final Payment Screen
		CardLayout layout = (CardLayout) this.getLayout();
		layout.show(this, "PaymentSuccess");

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

	/**
	 * Displays an Error that the Cash Amount Entered was Invalid.
	 */
	private void displayInvalidCashAmountError() {
		this.logger.warning("Invalid Cash Amount Entered");
		JOptionPane.showMessageDialog(null, "Must Enter a Valid Cash Amount.",
				"Enter Cash Amount", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Handles an "Enter Ticket" Click
	 */
	private void enterTicketClicked() {
		String ticketId = this.txtTicketId.getText();
		this.logger.info("Entered Ticket - " + ticketId);

		// Validate Ticket ID
		TicketManager ticketManager = TicketManager.getInstance();
		if (ticketManager.verifyTicketId(ticketId)) {
			// Compute Fee
			Double ticketAmount = this.paymentService.computeFee(ticketId,
					System.currentTimeMillis());
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String amountString = formatter.format(ticketAmount);

			// Set Fee
			this.lblTotal.setText(amountString);

			// Move to Next Card
			CardLayout layout = (CardLayout) this.getLayout();
			layout.show(this, "Payment");
		} else {
			// Not Valid, Error
			this.logger.warning("Invalid Ticket ID (" + ticketId + ") Entered");
			JOptionPane.showMessageDialog(this, ticketId
					+ " Is Not Valid. Please Enter a Valid Ticket ID.",
					"Ticket ID Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
