/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.dao.EventDao;
import cs414.as5.btsaunde.garagesystem.model.Event;

/**
 * Reporting Service for Generating Reports.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class ReportServiceImpl extends UnicastRemoteObject implements
		ReportService {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	/**
	 * Singleton Instance.
	 */
	private static ReportService instance;

	/**
	 * Default Constructor
	 * 
	 * @throws RemoteException
	 */
	private ReportServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * Get Ticket DAO.
	 * 
	 * @return Ticket DAO
	 */
	public static ReportService getInstance() {
		try {
			if (ReportServiceImpl.instance == null) {
				ReportServiceImpl.instance = new ReportServiceImpl();
			}
		} catch (RemoteException e) {
			ReportServiceImpl.LOGGER
					.info("Error Occured while Creating Report Service Remote Object.");

		}

		return ReportServiceImpl.instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.ReportService#getAveragePerDay
	 * (java.util.Calendar, java.util.Calendar)
	 */
	public Map<String, Double> getAveragePerDay(final Calendar start,
			final Calendar end) {
		Map<String, Double> reportResults = new LinkedHashMap<String, Double>();

		EventDao eventDao = EventDao.getInstance();

		Map<Integer, List<Integer>> dataSet = getEmptyIntegerDataset();
		for (Calendar curDate = start; !curDate.after(end); curDate.add(
				Calendar.DATE, 1)) {

			List<Integer> data = null;
			List<Event> events = null;
			switch (curDate.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY:
				data = dataSet.get(Calendar.MONDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.TUESDAY:
				data = dataSet.get(Calendar.TUESDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.WEDNESDAY:
				data = dataSet.get(Calendar.WEDNESDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.THURSDAY:
				data = dataSet.get(Calendar.THURSDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.FRIDAY:
				data = dataSet.get(Calendar.FRIDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.SATURDAY:
				data = dataSet.get(Calendar.SATURDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			case Calendar.SUNDAY:
				data = dataSet.get(Calendar.SUNDAY);
				events = eventDao.findEventByDateIssued(curDate);
				data.add(events.size());
				break;
			}

		}

		// Compute Averages
		for (Integer i = 1; i < 8; i++) {
			LOGGER.info("Computing Average for Day: " + i);

			List<Integer> data = dataSet.get(i);
			Integer size = data.size();
			Double total = 0.0;

			for (Integer x : data) {
				total += x;
			}

			Double average = total / size;
			String key = getDayOfWeekString(i);

			LOGGER.info("Average: " + key + "=" + average + " (Size: " + size
					+ ")(Total: " + total + ")");
			reportResults.put(key, average);
		}

		return reportResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.ReportService#getAverageStayPerDay
	 * (java.util.Calendar, java.util.Calendar)
	 */
	public Map<String, Double> getAverageStayPerDay(final Calendar start,
			final Calendar end) {
		Map<String, Double> reportResults = new LinkedHashMap<String, Double>();

		EventDao eventDao = EventDao.getInstance();

		Map<Integer, List<Double>> dataSet = getEmptyDoubleDataset();
		for (Calendar curDate = start; !curDate.after(end); curDate.add(
				Calendar.DATE, 1)) {

			List<Double> data = null;
			List<Event> events = null;
			switch (curDate.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY:
				data = dataSet.get(Calendar.MONDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.TUESDAY:
				data = dataSet.get(Calendar.TUESDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.WEDNESDAY:
				data = dataSet.get(Calendar.WEDNESDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.THURSDAY:
				data = dataSet.get(Calendar.THURSDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.FRIDAY:
				data = dataSet.get(Calendar.FRIDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.SATURDAY:
				data = dataSet.get(Calendar.SATURDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			case Calendar.SUNDAY:
				data = dataSet.get(Calendar.SUNDAY);
				events = eventDao.findEventByDateIssued(curDate);
				for (Event event : events) {
					Long issued = event.getTimeIssued().getTime();
					Long paid = event.getTimePaid().getTime();
					Double minutes = (paid - issued) / (60.00 * 1000.00);
					data.add(minutes);
				}
				break;
			}

		}

		// Compute Averages
		for (Integer i = 1; i < 8; i++) {
			LOGGER.info("Computing Average for Day: " + i);

			List<Double> data = dataSet.get(i);
			Integer size = data.size();
			Double total = 0.0;

			for (Double x : data) {
				total += x;
			}

			Double average = total / size;
			String key = getDayOfWeekString(i);

			LOGGER.info("Average: " + key + "=" + average + " (Size: " + size
					+ ")(Total: " + total + ")");
			reportResults.put(key, average);
		}

		return reportResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs414.as5.btsaunde.garagesystem.service.ReportService#getBusiestDay(java
	 * .util.Calendar, java.util.Calendar)
	 */
	public Calendar getBusiestDay(final Calendar start, final Calendar end) {

		EventDao eventDao = EventDao.getInstance();

		Calendar busiestDay = Calendar.getInstance();
		Integer maxVisits = -1;

		for (Calendar curDate = start; !curDate.after(end); curDate.add(
				Calendar.DATE, 1)) {

			List<Event> events = eventDao.findEventByDateIssued(curDate);
			if (events.size() > maxVisits) {
				maxVisits = events.size();
				busiestDay.setTime(curDate.getTime());
			}
		}

		return busiestDay;
	}

	/**
	 * Creates and Initializes an Integer Dataset for Report Data.
	 * 
	 * @return New Intitialized Dataset
	 */
	private Map<Integer, List<Integer>> getEmptyIntegerDataset() {
		Map<Integer, List<Integer>> dataSet = new HashMap<Integer, List<Integer>>();

		for (Integer i = 1; i < 8; i++) {
			dataSet.put(i, new LinkedList<Integer>());
		}

		return dataSet;
	}

	/**
	 * Creates and Initializes a Double Dataset for Report Data.
	 * 
	 * @return New Intitialized Dataset
	 */
	private Map<Integer, List<Double>> getEmptyDoubleDataset() {
		Map<Integer, List<Double>> dataSet = new HashMap<Integer, List<Double>>();

		for (Integer i = 1; i < 8; i++) {
			dataSet.put(i, new LinkedList<Double>());
		}

		return dataSet;
	}

	/**
	 * Gets the Day of the Week based on the Calendar Integer
	 * 
	 * @param day
	 *            Day Integer
	 * @return Day String
	 */
	private String getDayOfWeekString(Integer day) {
		String value = "";
		switch (day) {
		case Calendar.MONDAY:
			value = "Monday";
			break;
		case Calendar.TUESDAY:
			value = "Tuesday";
			break;
		case Calendar.WEDNESDAY:
			value = "Wednesday";
			break;
		case Calendar.THURSDAY:
			value = "Thursday";
			break;
		case Calendar.FRIDAY:
			value = "Friday";
			break;
		case Calendar.SATURDAY:
			value = "Saturday";
			break;
		case Calendar.SUNDAY:
			value = "Sunday";
			break;
		}

		return value;
	}

}
