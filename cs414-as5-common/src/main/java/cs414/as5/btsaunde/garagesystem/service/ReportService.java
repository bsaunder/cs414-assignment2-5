package cs414.as5.btsaunde.garagesystem.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Map;

public interface ReportService extends Remote {

	/**
	 * Finds the Average Number of Cars Per Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Results Map
	 */
	public abstract Map<String, Double> getAveragePerDay(final Calendar start,
			final Calendar end) throws RemoteException;

	/**
	 * Finds the Average Length of Stay Per Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Results Map
	 */
	public abstract Map<String, Double> getAverageStayPerDay(
			final Calendar start, final Calendar end) throws RemoteException;

	/**
	 * Finds the Busiest Day for the Given Time Period
	 * 
	 * @param start
	 *            Start Date
	 * @param end
	 *            End Date
	 * @return Busiest Day
	 */
	public abstract Calendar getBusiestDay(final Calendar start,
			final Calendar end) throws RemoteException;

}