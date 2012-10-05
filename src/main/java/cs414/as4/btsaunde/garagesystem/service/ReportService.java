/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cs414.as4.btsaunde.garagesystem.dao.EventDao;
import cs414.as4.btsaunde.garagesystem.model.Event;

/**
 * Reporting Service for Generating Reports.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class ReportService {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	/**
	 * Finds the Average Number of Cars Per Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Results Map
	 */
	public static Map<String, Double> getAveragePerDay(final Calendar start,
			final Calendar end) {
		Map<String, Double> reportResults = new LinkedHashMap<String, Double>();

		EventDao eventDao = EventDao.getInstance();

		Map<Integer, List<Integer>> dataSet = getEmptyDataset();
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

	/**
	 * Finds the Average Length of Stay Per Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Results Map
	 */
	public static Map<String, Double> getAverageStayPerDay(
			final Calendar start, final Calendar end) {
		Map<String, Double> reportResults = new LinkedHashMap<String, Double>();

		reportResults.put("Monday", 5.6);
		reportResults.put("Tuesday", 2.6);
		reportResults.put("Wendesday", 4.6);
		reportResults.put("Thursday", 7.5);
		reportResults.put("Friday", 9.8);
		reportResults.put("Saturday", 5.3);
		reportResults.put("Sunday", 2.8);

		return reportResults;
	}

	/**
	 * Finds the Busiest Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Busiest Day
	 */
	public static Calendar getBusiestDay(final Calendar start,
			final Calendar end) {
		return Calendar.getInstance();
	}

	/**
	 * Creates and Initializes a Dataset for Report Data.
	 * 
	 * @return New Intitialized Dataset
	 */
	private static Map<Integer, List<Integer>> getEmptyDataset() {
		Map<Integer, List<Integer>> dataSet = new HashMap<Integer, List<Integer>>();

		for (Integer i = 1; i < 8; i++) {
			dataSet.put(i, new LinkedList<Integer>());
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
	private static String getDayOfWeekString(Integer day) {
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
