package cs414.as5.btsaunde.garagesystem.config;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import cs414.as5.btsaunde.garagesystem.service.GarageConfigurationListener;
import cs414.as5.btsaunde.garagesystem.view.DashboardWindow;

public class KioskInfo extends UnicastRemoteObject implements GarageConfigurationListener {

	/**
	 * Default Constructor
	 * 
	 * @throws RemoteException
	 */
	public KioskInfo() throws RemoteException {
		super();
	}

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kiosk ID.
	 */
	private String id;

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	public void update() {
		DashboardWindow dashboard = DashboardWindow.getInstance();
		dashboard.update();
	}

}
