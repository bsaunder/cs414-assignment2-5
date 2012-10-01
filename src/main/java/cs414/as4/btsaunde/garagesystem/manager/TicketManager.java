package cs414.as4.btsaunde.garagesystem.manager;

import java.util.Date;
import java.util.UUID;

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

		String id = UUID.randomUUID().toString();
		ticket.setTicketId(id);

		// Save Ticket
		boolean saved = this.ticketDao.add(ticket);

		if(saved){
			// Log Ticket Event
			Event event = new Event();
			event.setTicket(ticket);

			this.eventDao.add(event);
			
			return ticket;
		}else{
			return null;
		}
	}
}