package cs414.as4.btsaunde.garagesystem.dao;

import java.util.LinkedList;

import cs414.as4.btsaunde.garagesystem.model.Event;
import cs414.as4.btsaunde.garagesystem.model.Ticket;

/**
 * Event DAO to Store Events.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EventDao extends LinkedList<Event> {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton Instance.
	 */
	private static EventDao instance;

	/**
	 * Private Constructor.
	 */
	private EventDao() {
	}

	/**
	 * Get Event DAO.
	 * 
	 * @return Event DAO
	 */
	public static EventDao getInstance() {
		if (EventDao.instance == null) {
			EventDao.instance = new EventDao();
		}

		return EventDao.instance;
	}

	/**
	 * Finds the Event that the Ticket Belongs To.
	 * 
	 * @param ticket
	 *            Ticket to Search On
	 * @return Matching Event or null if No Event found
	 */
	public Event findEventByTicket(Ticket ticket){
		for(Event event : this){
			if(event.getTicket().equals(ticket)){
				return event;
			}
		}
		
		return null;
	}
}
