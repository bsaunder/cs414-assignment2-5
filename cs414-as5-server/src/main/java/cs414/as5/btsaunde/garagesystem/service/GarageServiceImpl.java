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

	/**
	 * {@inheritDoc}
	 */
	public Integer getTotalSpaces() {
		return this.config.getTotalSpaces();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTotalSpaces(Integer totalSpaces) {
		this.config.setTotalSpaces(totalSpaces);
	}

	/**
	 * {@inheritDoc}
	 */
	public GarageStatus getStatus() {
		return this.config.getStatus();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setStatus(GarageStatus status) {
		this.config.setStatus(status);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getAvailableSpaces() {
		return this.config.getAvailableSpaces();
	}

	/**
	 * {@inheritDoc}
	 */
	public Double getParkingFee() {
		return this.config.getParkingFee();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParkingFee(Double parkingFee) {
		this.config.setParkingFee(parkingFee);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addListener(GarageConfigurationListener listener)
			throws RemoteException {
		this.config.addListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(GarageConfigurationListener listener)
			throws RemoteException {
		this.config.removeListener(listener);
	}
}
