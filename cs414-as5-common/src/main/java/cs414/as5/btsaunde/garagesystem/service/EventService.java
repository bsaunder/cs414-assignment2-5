package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import cs414.as5.btsaunde.garagesystem.model.Event;

public interface EventService extends Remote {

	/**
	 * Gets the Number of Events
	 * 
	 * @return Event Count
	 */
	public abstract Integer getEventCount() throws RemoteException;

	/**
	 * Gets the Specified Event
	 * 
	 * @param id
	 *            Event ID
	 * @return Event
	 */
	public abstract Event getEvent(int id) throws RemoteException;

}