package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as5.btsaunde.garagesystem.enums.GarageStatus;

/**
 * Garage Service Implementation.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GarageServiceImpl extends UnicastRemoteObject implements GarageService {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	/**
	 * Singleton Instance.
	 */
	private static GarageService instance;

	/**
	 * Garage Configuration
	 */
	private GarageConfiguration config;

	/**
	 * Default Constructor
	 * 
	 * @throws RemoteException
	 */
	private GarageServiceImpl() throws RemoteException {
		this.config = GarageConfiguration.getInstance();
	}
	
	/**
	 * Get GarageService.
	 * 
	 * @return GarageService
	 */
	public static GarageService getInstance() {
		try {
			if (GarageServiceImpl.instance == null) {
				GarageServiceImpl.instance = new GarageServiceImpl();
			}
		} catch (RemoteException e) {
			GarageServiceImpl.LOGGER.severe("Error Occured while Creating Garage Service Remote Object.");
			e.printStackTrace();
		}

		return GarageServiceImpl.instance;
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#getTotalSpaces()
	 */
	public Integer getTotalSpaces() {
		return this.config.getTotalSpaces();
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#setTotalSpaces(java.lang.Integer)
	 */
	public void setTotalSpaces(Integer totalSpaces) {
		this.config.setTotalSpaces(totalSpaces);
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#getStatus()
	 */
	public GarageStatus getStatus() {
		return this.config.getStatus();
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#setStatus(cs414.as5.btsaunde.garagesystem.enums.GarageStatus)
	 */
	public void setStatus(GarageStatus status) {
		this.config.setStatus(status);
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#getAvailableSpaces()
	 */
	public Integer getAvailableSpaces() {
		return this.config.getAvailableSpaces();
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#getParkingFee()
	 */
	public Double getParkingFee() {
		return this.config.getParkingFee();
	}

	/* (non-Javadoc)
	 * @see cs414.as5.btsaunde.garagesystem.service.GarageService#setParkingFee(java.lang.Double)
	 */
	public void setParkingFee(Double parkingFee) {
		this.config.setParkingFee(parkingFee);
	}
}
