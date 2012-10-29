package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import cs414.as5.btsaunde.garagesystem.enums.PaymentType;
import cs414.as5.btsaunde.garagesystem.model.Ticket;

public interface TicketService extends Remote {

	/**
	 * Create New Ticket and Log Record Event Information. Returns null if
	 * Ticket was Not Created.
	 * 
	 * @return New Ticket or null
	 */
	public Ticket createNewTicket() throws RemoteException;

	/**
	 * Verifies that a TicketID Exists
	 * 
	 * @param ticketId
	 *            Ticket ID to Verify
	 * @return True if the ID is Valid
	 */
	public boolean verifyTicketId(String ticketId) throws RemoteException;

	/**
	 * Gets the Ticket with the Specified ID.
	 * 
	 * @param ticketId
	 *            Ticket ID to Get
	 * @return Ticket or null if Ticket Not Found.
	 */
	public Ticket getTicket(String ticketId) throws RemoteException;

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
			Double totalFee, Long timePaid) throws RemoteException;

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
			Double totalFee, Long timePaid) throws RemoteException;

}