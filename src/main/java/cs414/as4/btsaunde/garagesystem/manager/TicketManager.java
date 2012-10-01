package cs414.as4.btsaunde.garagesystem.manager;

import java.util.Date;

import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
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
}
