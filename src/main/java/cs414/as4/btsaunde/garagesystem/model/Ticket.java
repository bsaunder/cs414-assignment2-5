package cs414.as4.btsaunde.garagesystem.model;

public class Ticket {

	/**
	 * Ticket ID.
	 */
	private String ticketId;
	
	/**
	 * Time Ticket was Issued.
	 */
	private Long timeIssued;

	/**
	 * Get the ticketId.
	 * 
	 * @return the ticketId
	 */
	public String getTicketId() {
		return this.ticketId;
	}

	/**
	 * Set the ticketId.
	 * 
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * Get the timeIssued.
	 * 
	 * @return the timeIssued
	 */
	public Long getTimeIssued() {
		return this.timeIssued;
	}

	/**
	 * Set the timeIssued.
	 * 
	 * @param timeIssued the timeIssued to set
	 */
	public void setTimeIssued(Long timeIssued) {
		this.timeIssued = timeIssued;
	}
	
	
	
}
