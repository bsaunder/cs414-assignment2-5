/**
 * 
 */
package cs414.as5.btsaunde.garagesystem.view.model;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import cs414.as5.btsaunde.garagesystem.model.Event;
import cs414.as5.btsaunde.garagesystem.rmi.RMIService;
import cs414.as5.btsaunde.garagesystem.service.EventService;

/**
 * Table Model for Displaying Events.
 * 
 * @author Bryan Saunders <bsaunder@redhat.com>
 * 
 */
public class EventTableModel extends AbstractTableModel {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private EventService eventService;

	/**
	 * Table Column Names.
	 */
	private String[] columnNames = { "Ticket ID", "Issued", "Paid", "Pay Type",
			"Fee" };

	/**
	 * Table Model Constructor.
	 */
	public EventTableModel() {
		this.eventService = RMIService.getEventService();
	}

	/**
	 * Gets Column Count
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Gets Row Count
	 */
	public int getRowCount() {
		try {
			return this.eventService.getEventCount();
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Gets Column Name
	 */
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * Gets Value for a Cell
	 */
	public Object getValueAt(int row, int col) {
		Event event = null;
		try {
			event = this.eventService.getEvent(row);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		Object value = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yy kk:mm");

		switch (col) {
		case 0:
			value = event.getTicket().getTicketId();
			break;
		case 1:
			if (event.getTimeIssued() != null) {
				Date timeIssued = event.getTimeIssued();
				value = formatter.format(timeIssued);
			}
			break;
		case 2:
			if (event.getTimePaid() != null) {
				Date timePaid = event.getTimePaid();
				value = formatter.format(timePaid);
			}
			break;
		case 3:
			value = event.getPaymentType();
			break;
		case 4:
			value = event.getTotalFee();
			break;
		}

		return value;
	}

	/**
	 * Gets the Class for a Column
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
