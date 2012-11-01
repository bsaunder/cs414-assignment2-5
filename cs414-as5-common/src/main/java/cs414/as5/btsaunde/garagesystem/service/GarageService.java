package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;

public interface GarageService extends Remote {

	/**
	 * Get the totalSpaces.
	 * 
	 * @return the totalSpaces
	 */
	public abstract Integer getTotalSpaces() throws RemoteException;

	/**
	 * Set the totalSpaces.
	 * 
	 * @param totalSpaces
	 *            the totalSpaces to set
	 */
	public abstract void setTotalSpaces(Integer totalSpaces)
			throws RemoteException;

	/**
	 * Get the status.
	 * 
	 * @return the status
	 */
	public abstract GarageStatus getStatus() throws RemoteException;

	/**
	 * Set the status.
	 * 
	 * @param status
	 *            the status to set
	 */
	public abstract void setStatus(GarageStatus status) throws RemoteException;

	/**
	 * Get the availableSpaces.
	 * 
	 * @return the availableSpaces
	 */
	public abstract Integer getAvailableSpaces() throws RemoteException;

	/**
	 * Get the parkingFee.
	 * 
	 * @return the parkingFee
	 */
	public abstract Double getParkingFee() throws RemoteException;

	/**
	 * Set the parkingFee.
	 * 
	 * @param parkingFee
	 *            the parkingFee to set
	 */
	public abstract void setParkingFee(Double parkingFee)
			throws RemoteException;

}