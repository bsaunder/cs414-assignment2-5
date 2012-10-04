/**
 * 
 */
package cs414.as4.btsaunde.garagesystem.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class DayComboBoxModel extends AbstractListModel implements
		ComboBoxModel {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List of Days
	 */
	private List<String> dayList = new LinkedList<String>();

	/**
	 * Selected Day
	 */
	private String selection = "1";

	/**
	 * Create the Model
	 */
	public DayComboBoxModel() {
		for (int i = 0; i <= 31; i++) {
			this.dayList.add(String.valueOf(i));
		}
	}

	/**
	 * Get Element
	 */
	public Object getElementAt(int index) {
		return this.dayList.get(index);
	}

	/**
	 * Get Size
	 */
	public int getSize() {
		return this.dayList.size();
	}

	/**
	 * Set Selected
	 */
	public void setSelectedItem(Object anItem) {
		this.selection = (String) anItem;
	}

	/**
	 * Get Selected
	 */
	public Object getSelectedItem() {
		return this.selection;
	}
}
