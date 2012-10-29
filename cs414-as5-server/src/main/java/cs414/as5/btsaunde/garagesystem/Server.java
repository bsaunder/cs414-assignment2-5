package cs414.as5.btsaunde.garagesystem;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

import cs414.as5.btsaunde.garagesystem.service.PaymentService;
import cs414.as5.btsaunde.garagesystem.service.PaymentServiceImpl;
import cs414.as5.btsaunde.garagesystem.service.ReportService;
import cs414.as5.btsaunde.garagesystem.service.ReportServiceImpl;
import cs414.as5.btsaunde.garagesystem.service.TicketService;
import cs414.as5.btsaunde.garagesystem.service.TicketServiceImpl;

/**
 * Main Server Class. Starts the Parking Garage RMI Server and Publishes the
 * Required Services.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Server {
	
	/**
	 * Logger
	 */
	private final Logger logger = Logger.getAnonymousLogger();

	/**
	 * Server Port
	 */
	private final Integer port = 1099;

	/**
	 * Ticket Service URL
	 */
	private String ticketServiceUrl = "rmi://localhost:" + this.port
			+ "/TicketService";

	/**
	 * Payment Service URL
	 */
	private String paymentServiceUrl = "rmi://localhost:" + this.port
			+ "/PaymentService";

	/**
	 * Report Service URL
	 */
	private String reportServiceUrl = "rmi://localhost:" + this.port
			+ "/ReportService";

	/**
	 * Starts the Server.
	 * 
	 * @param args
	 *            Command Line Arguments
	 */
	public static void main(String[] args) {
		new Server();
	}

	/**
	 * Server Constructor
	 */
	public Server() {
		this.startRmi();
	}

	/**
	 * Starts the RMI Server
	 */
	private void startRmi() {
		try {
			// Start Server
			Registry registry = LocateRegistry.createRegistry(this.port);

			// Create Services
			TicketService ticketService = TicketServiceImpl.getInstance();
			PaymentService paymentService = PaymentServiceImpl.getInstance();
			ReportService reportService = ReportServiceImpl.getInstance();

			// Register Services
			Naming.rebind(this.ticketServiceUrl, ticketService);
			Naming.rebind(this.paymentServiceUrl, paymentService);
			Naming.rebind(this.reportServiceUrl, reportService);

			this.logger.info("RMI registry ready: " + registry.toString());
		} catch (Exception e) {
			this.logger.severe("Error Starting RMI: " + e);
			e.printStackTrace();
		}
	}
}
