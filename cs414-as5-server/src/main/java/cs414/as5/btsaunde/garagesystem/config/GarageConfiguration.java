/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.config;

import cs414.as5.btsaunde.garagesystem.dao.TicketDao;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Gate;
import cs414.as5.btsaunde.garagesystem.model.Sign;

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
	 * Garage Gate.
	 */
	private Gate gate;

	/**
	 * Garage Sign.
	 */
	private Sign sign;

	/**
	 * Singleton Constructor.
	 */
	private GarageConfiguration() {
		this.ticketDao = TicketDao.getInstance();

		// Set Defaults
		this.parkingFee = 1.00;
		this.totalSpaces = 1;
		this.status = GarageStatus.OPEN;

		// Create Gate
		this.gate = new Gate();
		this.gate.closeGate();

		// Create Sign
		this.sign = new Sign();
		this.sign.setText(this.status.toString());
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
		this.status = status;
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

	/**
	 * Get the gate.
	 * 
	 * @return the gate
	 */
	public Gate getGate() {
		return this.gate;
	}

	/**
	 * Set the gate.
	 * 
	 * @param gate
	 *            the gate to set
	 */
	public void setGate(Gate gate) {
		this.gate = gate;
	}

	/**
	 * Get the sign.
	 * 
	 * @return the sign
	 */
	public Sign getSign() {
		return this.sign;
	}

	/**
	 * Set the sign.
	 * 
	 * @param sign the sign to set
	 */
	public void setSign(Sign sign) {
		this.sign = sign;
	}

}
