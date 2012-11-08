package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for Listening to Garage Configuration Changes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public interface GarageConfigurationListener extends Remote {

	/**
	 * Called when the Garage Configuration Changes.
	 */
	public abstract void update() throws RemoteException;;

}
