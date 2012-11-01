package cs414.as5.btsaunde.garagesystem.model;

import java.io.Serializable;
import java.util.Date;

import cs414.as5.btsaunde.garagesystem.enums.PaymentType;

public class Event implements Serializable {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ticket Associated with the Event.
	 */
	private Ticket ticket;

	/**
	 * Time Issued
	 */
	private Date timeIssued;

	/**
	 * Time Paid
	 */
	private Date timePaid;

	/**
	 * Total Fee
	 */
	private Double totalFee;

	/**
	 * Payment Type
	 */
	private PaymentType paymentType;

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

	/**
	 * Get the timeIssued.
	 * 
	 * @return the timeIssued
	 */
	public Date getTimeIssued() {
		return this.timeIssued;
	}

	/**
	 * Set the timeIssued.
	 * 
	 * @param timeIssued
	 *            the timeIssued to set
	 */
	public void setTimeIssued(Date timeIssued) {
		this.timeIssued = timeIssued;
	}

	/**
	 * Set the timeIssued.
	 * 
	 * @param timeIssued
	 *            the timeIssued to set
	 */
	public void setTimeIssued(Long timeIssued) {
		this.setTimeIssued(new Date(timeIssued));
	}

	/**
	 * Get the timePaid.
	 * 
	 * @return the timePaid
	 */
	public Date getTimePaid() {
		return this.timePaid;
	}

	/**
	 * Set the timePaid.
	 * 
	 * @param timePaid
	 *            the timePaid to set
	 */
	public void setTimePaid(Date timePaid) {
		this.timePaid = timePaid;
	}

	/**
	 * Set the timePaid.
	 * 
	 * @param timePaid
	 *            the timePaid to set
	 */
	public void setTimePaid(Long timePaid) {
		this.setTimePaid(new Date(timePaid));
	}

	/**
	 * Get the totalFee.
	 * 
	 * @return the totalFee
	 */
	public Double getTotalFee() {
		return this.totalFee;
	}

	/**
	 * Set the totalFee.
	 * 
	 * @param totalFee
	 *            the totalFee to set
	 */
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * Get the paymentType.
	 * 
	 * @return the paymentType
	 */
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	/**
	 * Set the paymentType.
	 * 
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
