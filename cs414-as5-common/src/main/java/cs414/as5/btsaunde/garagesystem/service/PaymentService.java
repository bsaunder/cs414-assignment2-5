package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PaymentService extends Remote {

	/**
	 * Computes the Total Fee for a Given Ticket ID
	 * 
	 * @param ticketId
	 *            Ticket ID
	 * @return Total Fee
	 */
	public abstract Double computeFee(String ticketId, Long end) throws RemoteException;

	/**
	 * Validates Card Number
	 * 
	 * @param cardNumber
	 *            Card Number to Validate
	 * @return Always True for Testing Purposes.
	 */
	public abstract boolean cardIsValid(String cardNumber) throws RemoteException;

	/**
	 * Charges the Card
	 * 
	 * @param cardNumber
	 *            Card to Charge
	 * @param fee
	 *            Fee to Charge
	 */
	public abstract void chargeCard(String cardNumber, Double fee) throws RemoteException;

}