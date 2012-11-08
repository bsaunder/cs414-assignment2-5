package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.dao.EventDao;
import cs414.as5.btsaunde.garagesystem.model.Event;

public class EventServiceImpl extends UnicastRemoteObject implements EventService {

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
	private static EventService instance;

	/**
	 * Event DAO.
	 */
	private EventDao eventDao;

	/**
	 * Default Constructor
	 * 
	 * @throws RemoteException
	 */
	private EventServiceImpl() throws RemoteException {
		this.eventDao = EventDao.getInstance();
	}
	
	/**
	 * Get GarageService.
	 * 
	 * @return GarageService
	 */
	public static EventService getInstance() {
		try {
			if (EventServiceImpl.instance == null) {
				EventServiceImpl.instance = new EventServiceImpl();
			}
		} catch (RemoteException e) {
			EventServiceImpl.LOGGER.severe("Error Occured while Creating Event Service Remote Object.");
			e.printStackTrace();
		}

		return EventServiceImpl.instance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Integer getEventCount(){
		return this.eventDao.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Event getEvent(int id){
		return this.eventDao.get(id);
	}

}
