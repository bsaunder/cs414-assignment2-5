package cs414.as5.btsaunde.garagesystem.rmi;

import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.service.PaymentService;
import cs414.as5.btsaunde.garagesystem.service.ReportService;
import cs414.as5.btsaunde.garagesystem.service.TicketService;

public class RMIService {

	public static TicketService getTicketService() {
		// TODO: Implement RMI Call
		return null;
	}

	public static PaymentService getPaymentService() {
		// TODO: Implement RMI Call
		return null;
	}

	public static ReportService getReportService() {
		// TODO: Implement RMI Call
		return null;
	}
	
	public static Integer getEventCount(){
		// TODO: Implement RMI Call
		return 5;
	}

	public static Event getEvent(int row) {
		// TODO: Implement RMI Call
		return null;
	}
}
