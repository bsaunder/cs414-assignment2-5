/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.config;

import cs414.as4.btsaunde.garagesystem.dao.TicketDao;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;

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
	private GarageConfiguration(){
		this.ticketDao = TicketDao.getInstance();
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
		// If Setting the Status to Open & The Current Status is Full, Leave as
		// Full
		if (status == GarageStatus.OPEN && this.status == GarageStatus.FULL) {
			this.status = GarageStatus.FULL;
		} else {
			this.status = status;
		}
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
	}

}
