package cs414.as4.btsaunde.garagesystem.manager;

import java.util.Date;

import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
import cs414.as4.btsaunde.garagesystem.enums.PaymentType;
import cs414.as4.btsaunde.garagesystem.model.Event;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

public class TicketManager {

	/**
	 * Singleton Instance.
	 */
	private static TicketManager instance;

	/**
	 * Ticket DAO.
	 */
	private TicketDao ticketDao;

	/**
	 * Event DAO.
	 */
	private EventDao eventDao;

	/**
	 * Private Constructor.
	 */
	private TicketManager() {
		this.ticketDao = TicketDao.getInstance();
		this.eventDao = EventDao.getInstance();
	}

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static TicketManager getInstance() {
		if (TicketManager.instance == null) {
			TicketManager.instance = new TicketManager();
		}

		return TicketManager.instance;
	}

	/**
	 * Create New Ticket and Log Record Event Information. Returns null if
	 * Ticket was Not Created.
	 * 
	 * @return New Ticket or null
	 */
	public Ticket createNewTicket() {
		// TODO Refactor This. Dont Like it.
		Ticket ticket = new Ticket();

		Date issued = new Date();
		ticket.setTimeIssued(issued.getTime());

		// String id = UUID.randomUUID().toString();
		String id = "SPS" + this.ticketDao.size() + 1;
		ticket.setTicketId(id);

		// Save Ticket
		boolean saved = this.ticketDao.add(ticket);

		if (saved) {
			// Log Ticket Event
			Event event = new Event();
			event.setTicket(ticket);
			event.setTimeIssued(issued);

			this.eventDao.add(event);

			return ticket;
		} else {
			return null;
		}
	}

	/**
	 * Verifies that a TicketID Exists
	 * 
	 * @param ticketId
	 *            Ticket ID to Verify
	 * @return True if the ID is Valid
	 */
	public boolean verifyTicketId(String ticketId) {
		Ticket ticket = this.ticketDao.findTicketById(ticketId);
		return ticket != null;
	}

	/**
	 * Gets the Ticket with the Specified ID.
	 * 
	 * @param ticketId
	 *            Ticket ID to Get
	 * @return Ticket or null if Ticket Not Found.
	 */
	public Ticket getTicket(String ticketId) {
		return this.ticketDao.findTicketById(ticketId);
	}

	/**
	 * Finalizes a Ticket by Updating its Event and Removing it from the
	 * Database.
	 * 
	 * @param ticket
	 *            Ticket to Update
	 * @param paymentType
	 *            Payment Type
	 * @param totalFee
	 *            Total Fee
	 * @param timePaid
	 *            Time Ticket was Paid
	 */
	public void finalizeTicket(Ticket ticket, PaymentType paymentType,
			Double totalFee, Long timePaid) {
		
		Event event = this.eventDao.findEventByTicket(ticket);
		
		event.setTimePaid(timePaid);
		event.setTotalFee(totalFee);
		event.setPaymentType(paymentType);
		
		this.ticketDao.remove(ticket);
	}
	
	/**
	 * Finalizes a Ticket by Updating its Event and Removing it from the
	 * Database.
	 * 
	 * @param ticketId
	 *            TicketID to Update
	 * @param paymentType
	 *            Payment Type
	 * @param totalFee
	 *            Total Fee
	 * @param timePaid
	 *            Time Ticket was Paid
	 */
	public void finalizeTicket(String ticketId, PaymentType paymentType,
			Double totalFee, Long timePaid) {
		
		Ticket ticket = this.getTicket(ticketId);
		this.finalizeTicket(ticket, paymentType, totalFee, timePaid);
	}
}
