package cs414.as5.btsaunde.garagesystem.config;

import java.rmi.RemoteException;

import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as5.btsaunde.garagesystem.model.Gate;
import cs414.as5.btsaunde.garagesystem.model.Sign;
import cs414.as5.btsaunde.garagesystem.rmi.RMIService;
import cs414.as5.btsaunde.garagesystem.service.GarageService;

public class KioskConfiguration {

	/**
	 * Singleton Instance.
	 */
	private static KioskConfiguration instance;
	
	/**
	 * Garage Service
	 */
	private GarageService garageService;

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
	 * @throws RemoteException 
	 */
	private KioskConfiguration() throws RemoteException {
		this.garageService = RMIService.getGarageService();
		
		// Create Gate
		this.gate = new Gate();
		this.gate.closeGate();

		// Create Sign
		this.sign = new Sign();
		this.sign.setText(this.garageService.getStatus().toString());
	}

	/**
	 * Get an Instance of the Current Kiosk Configuration.
	 * 
	 * @return Current Kiosk Configuration.
	 * @throws RemoteException 
	 */
	public static KioskConfiguration getInstance() throws RemoteException {
		if (KioskConfiguration.instance == null) {
			KioskConfiguration.instance = new KioskConfiguration();
		}

		return KioskConfiguration.instance;
	}

	/**
	 * Get the totalSpaces.
	 * 
	 * @return the totalSpaces
	 * @throws RemoteException 
	 */
	public Integer getTotalSpaces() throws RemoteException {
		return this.garageService.getTotalSpaces();
	}

	/**
	 * Set the totalSpaces.
	 * 
	 * @param totalSpaces
	 *            the totalSpaces to set
	 * @throws RemoteException 
	 */
	public void setTotalSpaces(Integer totalSpaces) throws RemoteException {
		this.garageService.setTotalSpaces(totalSpaces);
	}

	/**
	 * Get the status.
	 * 
	 * @return the status
	 * @throws RemoteException 
	 */
	public GarageStatus getStatus() throws RemoteException {
		return this.garageService.getStatus();
	}

	/**
	 * Set the status.
	 * 
	 * @param status
	 *            the status to set
	 * @throws RemoteException 
	 */
	public void setStatus(GarageStatus status) throws RemoteException {
		this.garageService.setStatus(status);
	}

	/**
	 * Get the availableSpaces.
	 * 
	 * @return the availableSpaces
	 * @throws RemoteException 
	 */
	public Integer getAvailableSpaces() throws RemoteException {
		return this.garageService.getAvailableSpaces();
	}

	/**
	 * Get the parkingFee.
	 * 
	 * @return the parkingFee
	 * @throws RemoteException 
	 */
	public Double getParkingFee() throws RemoteException {
		return this.garageService.getParkingFee();
	}

	/**
	 * Set the parkingFee.
	 * 
	 * @param parkingFee
	 *            the parkingFee to set
	 * @throws RemoteException 
	 */
	public void setParkingFee(Double parkingFee) throws RemoteException {
		this.garageService.setParkingFee(parkingFee);
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
