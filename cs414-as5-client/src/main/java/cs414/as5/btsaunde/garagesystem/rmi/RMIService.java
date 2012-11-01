package cs414.as5.btsaunde.garagesystem.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.service.GarageService;
import cs414.as5.btsaunde.garagesystem.service.PaymentService;
import cs414.as5.btsaunde.garagesystem.service.ReportService;
import cs414.as5.btsaunde.garagesystem.service.TicketService;

/**
 * Encapsulates all of the RMI Service Interactions into a Single Class.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class RMIService {

	/**
	 * Retrieves the TicketService from the RMI Server
	 * 
	 * @return TicketService
	 */
	public static TicketService getTicketService() {
		try {
			TicketService service = (TicketService) Naming
					.lookup("rmi://localhost:1099/TicketService");

			return service;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieves the PaymentService from the RMI Server
	 * 
	 * @return PaymentService
	 */
	public static PaymentService getPaymentService() {
		try {
			PaymentService service = (PaymentService) Naming
					.lookup("rmi://localhost:1099/PaymentService");

			return service;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieves the ReportService from the RMI Server
	 * 
	 * @return ReportService
	 */
	public static ReportService getReportService() {
		try {
			ReportService service = (ReportService) Naming
					.lookup("rmi://localhost:1099/ReportService");

			return service;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieves the GarageService from the RMI Server
	 * 
	 * @return GarageService
	 */
	public static GarageService getGarageService() {
		try {
			GarageService service = (GarageService) Naming
					.lookup("rmi://localhost:1099/GarageService");

			return service;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Integer getEventCount() {
		// TODO: Implement RMI Call
		return 5;
	}

	public static Event getEvent(int row) {
		// TODO: Implement RMI Call
		return null;
	}
}
