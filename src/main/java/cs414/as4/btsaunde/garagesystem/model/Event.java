package cs414.as4.btsaunde.garagesystem.model;

public class Event {

	/**
	 * Ticket Associated with the Event.
	 */
	private Ticket ticket;

	/**
	 * Get the ticket.
	 * 
	 * @return the ticket
	 */
	public Ticket getTicket() {
		return this.ticket;
	}

	/**
	 * Set the ticket.
	 * 
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
