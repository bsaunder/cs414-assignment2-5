package cs414.as5.btsaunde.garagesystem.config;

import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Gate;
import cs414.as5.btsaunde.garagesystem.model.Sign;

public class KioskConfiguration {

	/**
	 * Singleton Instance.
	 */
	private static KioskConfiguration instance;

	/**
	 * Kiosk Gate.
	 */
	private Gate gate;

	/**
	 * Kiosk Sign.
	 */
	private Sign sign;

	/**
	 * Singleton Constructor.
	 */
	private KioskConfiguration() {
		// Create Gate
		this.gate = new Gate();
		this.gate.closeGate();

		// Create Sign
		this.sign = new Sign();
		this.sign.setText("");
	}

	/**
	 * Get an Instance of the Current Kiosk Configuration.
	 * 
	 * @return Current Kiosk Configuration.
	 */
	public static KioskConfiguration getInstance() {
		if (KioskConfiguration.instance == null) {
			KioskConfiguration.instance = new KioskConfiguration();
		}

		return KioskConfiguration.instance;
	}

	/**
	 * Get the totalSpaces.
	 * 
	 * @return the totalSpaces
	 */
	public Integer getTotalSpaces() {
		// TODO: Implement This as RMI Call to Server
		return 5;
	}

	/**
	 * Set the totalSpaces.
	 * 
	 * @param totalSpaces
	 *            the totalSpaces to set
	 */
	public void setTotalSpaces(Integer totalSpaces) {
		// TODO: Implement This as RMI Call to Server
	}

	/**
	 * Get the status.
	 * 
	 * @return the status
	 */
	public GarageStatus getStatus() {
		// TODO: Implement This as RMI Call to Server
		return GarageStatus.OPEN;
	}

	/**
	 * Set the status.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(GarageStatus status) {
		// TODO: Implement This as RMI Call to Server
	}

	/**
	 * Get the availableSpaces.
	 * 
	 * @return the availableSpaces
	 */
	public Integer getAvailableSpaces() {
		// TODO: Implement This as RMI Call to Server
		return 4;
	}

	/**
	 * Get the parkingFee.
	 * 
	 * @return the parkingFee
	 */
	public Double getParkingFee() {
		// TODO: Implement This as RMI Call to Server
		return 0.5;
	}

	/**
	 * Set the parkingFee.
	 * 
	 * @param parkingFee
	 *            the parkingFee to set
	 */
	public void setParkingFee(Double parkingFee) {
		// TODO: Implement This as RMI Call to Server
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
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(Sign sign) {
		this.sign = sign;
	}
}
