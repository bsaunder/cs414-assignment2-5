package cs414.as4.btsaunde.garagesystem.view;

import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class DataViewer extends JDialog {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * UI Components
	 */
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public DataViewer() {
		this.logger.info("Starting DataViewer...");

		this.initialize();
		this.build();
	}

	/**
	 * Initialize Frame.
	 */
	public void initialize() {
		this.setTitle("Parking Data Viewer");
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(25, 25, 450, 450);
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
	}

	/**
	 * Build Frame.
	 */
	public void build() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 434, 408);
		contentPane.add(scrollPane);

		this.table = new JTable();
		scrollPane.setViewportView(table);
		this.table.setModel(new EventTableModel());
	}
}
