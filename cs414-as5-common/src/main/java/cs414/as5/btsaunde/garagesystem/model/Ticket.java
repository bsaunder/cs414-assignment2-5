package cs414.as5.btsaunde.garagesystem.model;

import java.io.Serializable;

public class Ticket implements Serializable {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ticket ID.
	 */
	private String ticketId;

	/**
	 * Time Ticket was Issued.
	 */
	private Long timeIssued;

	/**
	 * Default Constructor
	 */
	public Ticket() {
		this.timeIssued = System.currentTimeMillis();
		this.ticketId = "";
	}

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
	 * @param ticketId
	 *            the ticketId to set
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
	 * @param timeIssued
	 *            the timeIssued to set
	 */
	public void setTimeIssued(Long timeIssued) {
		this.timeIssued = timeIssued;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ticket) {
			Ticket ticket = (Ticket) obj;
			String incTicketId = ticket.getTicketId();
			if (incTicketId != null) {
				return incTicketId.equals(this.ticketId);
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.ticketId.hashCode();
	}

}
