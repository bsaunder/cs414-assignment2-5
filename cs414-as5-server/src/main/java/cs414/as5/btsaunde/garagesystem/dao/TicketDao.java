package cs414.as5.btsaunde.garagesystem.dao;

import java.util.LinkedList;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

public class TicketDao extends LinkedList<Ticket> {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton Instance.
	 */
	private static TicketDao instance;

	/**
	 * Private Constructor.
	 */
	private TicketDao() {

	}

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static TicketDao getInstance() {
		if (TicketDao.instance == null) {
			TicketDao.instance = new TicketDao();
		}

		return TicketDao.instance;
	}

	/**
	 * Override the Save method to make sure we cant add Tickets when there are
	 * no spaces left.
	 */
	@Override
	public boolean add(Ticket ticket) {
		boolean result = false;

		GarageConfiguration config = GarageConfiguration.getInstance();
		Integer total = config.getTotalSpaces();
		if (this.size() < total) {
			result = super.add(ticket);
		}
		config.notifyListeners();

		return result;
	}
	
	/**
	 * Override Remove Method to call Notify on GarageConfig.
	 */
	@Override
	public boolean remove(Object o) {
		GarageConfiguration config = GarageConfiguration.getInstance();
		config.notifyListeners();
		
		return super.remove(o);
	}

	/**
	 * Find the Ticket By ID
	 * 
	 * @param ticketId
	 *            Ticket ID to Find.
	 * @return Found Ticket or Null
	 */
	public Ticket findTicketById(String ticketId) {
		for (Ticket ticket : this) {
			if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
				return ticket;
			}
		}
		return null;
	}

}
