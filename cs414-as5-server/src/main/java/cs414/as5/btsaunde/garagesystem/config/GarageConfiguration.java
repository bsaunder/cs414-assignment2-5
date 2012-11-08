/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.config;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.service.GarageConfigurationListener;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GarageConfiguration {

	/**
	 * Singleton Instance.
	 */
	private static GarageConfiguration instance;
	
	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();
	
	/**
	 * List of Listeners.
	 */
	private List<GarageConfigurationListener> listeners;

	/**
	 * Ticket DAO.
	 */
	private TicketDao ticketDao;

	/**
	 * Number of Spaces in the Garage.
	 */
	private Integer totalSpaces;

	/**
	 * 15min Parking Fee
	 */
	private Double parkingFee;

	/**
	 * Garage Status.
	 */
	private GarageStatus status;

	/**
	 * Singleton Constructor.
	 */
	private GarageConfiguration() {
		this.listeners = new LinkedList<GarageConfigurationListener>();
		
		this.ticketDao = TicketDao.getInstance();

		// Set Defaults
		this.parkingFee = 1.00;
		this.totalSpaces = 1;
		this.status = GarageStatus.OPEN;
	}

	/**
	 * Get an Instance of the Current Garage Configuration.
	 * 
	 * @return Current Garage Configuration.
	 */
	public static GarageConfiguration getInstance() {
		if (GarageConfiguration.instance == null) {
			GarageConfiguration.instance = new GarageConfiguration();
		}

		return GarageConfiguration.instance;
	}

	/**
	 * Get the totalSpaces.
	 * 
	 * @return the totalSpaces
	 */
	public Integer getTotalSpaces() {
		return this.totalSpaces;
	}

	/**
	 * Set the totalSpaces.
	 * 
	 * @param totalSpaces
	 *            the totalSpaces to set
	 */
	public void setTotalSpaces(Integer totalSpaces) {
		this.totalSpaces = totalSpaces;
		this.notifyListeners();
	}

	/**
	 * Get the status.
	 * 
	 * @return the status
	 */
	public GarageStatus getStatus() {
		return this.status;
	}

	/**
	 * Set the status.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(GarageStatus status) {
		this.status = status;
		this.notifyListeners();
	}

	/**
	 * Get the availableSpaces.
	 * 
	 * @return the availableSpaces
	 */
	public Integer getAvailableSpaces() {
		int usedSpaces = this.ticketDao.size();
		return this.totalSpaces - usedSpaces;
	}

	/**
	 * Get the parkingFee.
	 * 
	 * @return the parkingFee
	 */
	public Double getParkingFee() {
		return this.parkingFee;
	}

	/**
	 * Set the parkingFee.
	 * 
	 * @param parkingFee
	 *            the parkingFee to set
	 */
	public void setParkingFee(Double parkingFee) {
		this.parkingFee = parkingFee;
		this.notifyListeners();
	}
	
	/**
	 * Add a new Listener to the Configuration
	 * 
	 * @param listener Listener to Add
	 */
	public void addListener(GarageConfigurationListener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Remove a Listener from the Configuration
	 * 
	 * @param listener Listener to Remove
	 */
	public void removeListener(GarageConfigurationListener listener){
		this.listeners.remove(listener);
	}
	
	/**
	 * Notify Listeners of a Change.
	 */
	public void notifyListeners(){
		this.logger.info("Notifying Listeners of Garage Change");
		
		for(GarageConfigurationListener listener : this.listeners){
			try {
				listener.update();
			} catch (RemoteException e) {
				this.logger.warning("Could Not Notify Listener");
				e.printStackTrace();
			}
		}
	}

}
