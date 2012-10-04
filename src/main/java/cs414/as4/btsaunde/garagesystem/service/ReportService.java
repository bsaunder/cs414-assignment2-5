/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.service;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Reporting Service for Generating Reports.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class ReportService {

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
	 * Finds the Average Number of Cars Per Hour for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Results Map
	 */
	public static Map<String, Double> getAveragePerHour(final Calendar start,
			final Calendar end) {
		Map<String, Double> reportResults = new LinkedHashMap<String, Double>();

		reportResults.put("1", 5.6);
		reportResults.put("2", 2.6);
		reportResults.put("3", 4.6);
		reportResults.put("4", 7.5);
		reportResults.put("5", 9.8);
		reportResults.put("6", 5.3);
		reportResults.put("7", 2.8);
		reportResults.put("8", 5.6);
		reportResults.put("9", 2.6);
		reportResults.put("10", 4.6);
		reportResults.put("11", 7.5);
		reportResults.put("12", 9.8);
		reportResults.put("13", 5.3);
		reportResults.put("14", 2.8);
		reportResults.put("15", 5.6);
		reportResults.put("16", 2.6);
		reportResults.put("17", 4.6);
		reportResults.put("18", 7.5);
		reportResults.put("19", 9.8);
		reportResults.put("20", 5.3);
		reportResults.put("21", 2.8);
		reportResults.put("22", 5.6);
		reportResults.put("23", 2.6);
		reportResults.put("24", 4.6);

		return reportResults;
	}
}
