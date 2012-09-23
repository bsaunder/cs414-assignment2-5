package cs414.as4.btsaunde.garagesystem.view;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

import cs414.as4.btsaunde.garagesystem.action.ChangeGarageStatusAction;
import cs414.as4.btsaunde.garagesystem.action.LoginAction;
import cs414.as4.btsaunde.garagesystem.action.LogoutAction;
import cs414.as4.btsaunde.garagesystem.action.SetParkingFeeAction;
import cs414.as4.btsaunde.garagesystem.action.SetTotalSpacesAction;
import cs414.as4.btsaunde.garagesystem.action.ShutdownAction;
import cs414.as4.btsaunde.garagesystem.action.SummonAttendantAction;
import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.security.Identity;
import javax.swing.border.EtchedBorder;

public class DashboardWindow extends JFrame {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton Instance.
	 */
	private static DashboardWindow instance;

	/**
	 * Logger
	 */
	@SuppressWarnings("unused")
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * Panel Contents
	 */
	private JPanel contentPane;
	private JLabel lblSpacesRemaining;
	private JLabel lblGarageStatus;
	private JMenuItem mntmRetrieveParkingTicket;
	private JMenuItem mntmShutdownKiosk;
	private JMenu mnGenerateReport;
	private JMenuItem mntmSetMaximumSpaces;
	private JMenuItem mntmSetParkingFee;
	private JMenu mnSetGarageStatus;
	private JMenuItem mntmLogin;
	private JRadioButtonMenuItem rdbtnmntmOpen;
	private JRadioButtonMenuItem rdbtnmntmClosed;
	private SignPanel signPanel;
	private GatePanel gatePanel;

	/**
	 * Get the Dashboard Instance.
	 * 
	 * @return Current Dashboard
	 */
	public static DashboardWindow getInstance() {
		if (DashboardWindow.instance == null) {
			DashboardWindow.instance = new DashboardWindow();
		}

		return DashboardWindow.instance;
	}

	/**
	 * Create the frame.
	 */
	public DashboardWindow() {
		this.intialize();

		this.buildMenus();
		this.buildPanels();

		this.refreshFromConfig();
	}

	/**
	 * Initialize Dashboard.
	 */
	private void intialize() {
		setResizable(false);
		setTitle("Saunders Parking Systems");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 500, 500);
	}

	/**
	 * Build Dashboard Panels.
	 */
	private void buildPanels() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.signPanel = new SignPanel();
		this.signPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.signPanel.setBounds(5, 5, 240, 100);
		this.contentPane.add(this.signPanel);

		this.gatePanel = new GatePanel();
		this.gatePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.gatePanel.setBounds(250, 5, 240, 100);
		this.contentPane.add(this.gatePanel);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.setBounds(5, 116, 485, 311);
		this.contentPane.add(contentPanel);

		JPanel statusPanel = new JPanel();
		statusPanel.setBounds(0, 433, 494, 13);
		this.contentPane.add(statusPanel);
		statusPanel.setLayout(null);

		this.lblGarageStatus = new JLabel("Garage Status: CLOSED");
		this.lblGarageStatus.setBounds(10, 0, 156, 14);
		statusPanel.add(this.lblGarageStatus);

		this.lblSpacesRemaining = new JLabel("Spaces Remaining: 999");
		this.lblSpacesRemaining.setBounds(353, 0, 141, 14);
		statusPanel.add(this.lblSpacesRemaining);
	}

	/**
	 * Build Dashboard Menu Bar.
	 */
	private void buildMenus() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("System");
		mnSystem.setMnemonic('S');
		menuBar.add(mnSystem);

		this.mntmRetrieveParkingTicket = new JMenuItem(
				"Retrieve Parking Ticket");
		this.mntmRetrieveParkingTicket.setMnemonic('R');
		mnSystem.add(this.mntmRetrieveParkingTicket);

		JMenuItem mntmPayForParking = new JMenuItem("Pay for Parking Ticket");
		mntmPayForParking.setMnemonic('P');
		mnSystem.add(mntmPayForParking);

		JMenu mnAdmin = new JMenu("Admin");
		mnAdmin.setMnemonic('A');
		menuBar.add(mnAdmin);

		this.mntmLogin = new JMenuItem("Login");
		this.mntmLogin.setMnemonic('L');
		mnAdmin.add(this.mntmLogin);

		this.mnSetGarageStatus = new JMenu("Set Garage Status");
		this.mnSetGarageStatus.setMnemonic('S');
		mnAdmin.add(this.mnSetGarageStatus);

		ButtonGroup statusGroup = new ButtonGroup();
		this.rdbtnmntmOpen = new JRadioButtonMenuItem();
		this.rdbtnmntmOpen.setAction(new ChangeGarageStatusAction(
				GarageStatus.OPEN));
		this.rdbtnmntmOpen.setText("Open");
		this.rdbtnmntmOpen.setMnemonic('O');
		this.mnSetGarageStatus.add(this.rdbtnmntmOpen);

		this.rdbtnmntmClosed = new JRadioButtonMenuItem();
		this.rdbtnmntmClosed.setAction(new ChangeGarageStatusAction(
				GarageStatus.CLOSED));
		this.rdbtnmntmClosed.setText("Closed");
		this.rdbtnmntmClosed.setMnemonic('C');
		this.mnSetGarageStatus.add(this.rdbtnmntmClosed);

		statusGroup.add(this.rdbtnmntmOpen);
		statusGroup.add(this.rdbtnmntmClosed);

		this.mntmSetParkingFee = new JMenuItem();
		this.mntmSetParkingFee.setAction(new SetParkingFeeAction());
		this.mntmSetParkingFee.setText("Set Parking Fee ($###)");
		this.mntmSetParkingFee.setMnemonic('P');
		mnAdmin.add(this.mntmSetParkingFee);

		this.mntmSetMaximumSpaces = new JMenuItem();
		this.mntmSetMaximumSpaces.setAction(new SetTotalSpacesAction());
		this.mntmSetMaximumSpaces.setText("Set Maximum Spaces (###)");
		this.mntmSetMaximumSpaces.setMnemonic('M');
		mnAdmin.add(this.mntmSetMaximumSpaces);

		this.mnGenerateReport = new JMenu("Generate Report");
		this.mnGenerateReport.setMnemonic('G');
		mnAdmin.add(this.mnGenerateReport);

		JMenuItem mntmReport = new JMenuItem("Report 1");
		this.mnGenerateReport.add(mntmReport);

		this.mntmShutdownKiosk = new JMenuItem();
		this.mntmShutdownKiosk.setAction(new ShutdownAction());
		this.mntmShutdownKiosk.setText("Shutdown Kiosk");
		this.mntmShutdownKiosk.setMnemonic('K');
		mnAdmin.add(this.mntmShutdownKiosk);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		JMenuItem mntmSummonAttendant = new JMenuItem();
		mntmSummonAttendant.setAction(new SummonAttendantAction());
		mntmSummonAttendant.setText("Summon Attendant");
		mntmSummonAttendant.setMnemonic('S');
		mnHelp.add(mntmSummonAttendant);
	}

	/**
	 * Refresh Dashboard based on Current System Status.
	 */
	public void refreshFromConfig() {
		GarageConfiguration config = GarageConfiguration.getInstance();

		// Update Menu Items Based on Garage Status
		if (config.getStatus() == GarageStatus.OPEN) {
			this.mntmRetrieveParkingTicket.setEnabled(true);
			this.rdbtnmntmOpen.setSelected(true);
			this.rdbtnmntmClosed.setSelected(false);
		} else if (config.getStatus() == GarageStatus.FULL) {
			this.mntmRetrieveParkingTicket.setEnabled(false);
			this.rdbtnmntmOpen.setSelected(true);
			this.rdbtnmntmClosed.setSelected(false);
		} else {
			this.mntmRetrieveParkingTicket.setEnabled(false);
			this.rdbtnmntmOpen.setSelected(false);
			this.rdbtnmntmClosed.setSelected(true);
		}

		// Update Menu Items Based on Authorization
		Identity identity = Identity.getInstance();
		if (identity.isAuthenticated()) {
			this.mntmLogin.setAction(new LogoutAction());
			this.mntmLogin.setText("Logout");

			this.mnSetGarageStatus.setEnabled(true);
			this.mntmSetMaximumSpaces.setEnabled(true);
			this.mntmSetParkingFee.setEnabled(true);
			this.mnGenerateReport.setEnabled(true);
			this.mntmShutdownKiosk.setEnabled(true);
		} else {
			this.mntmLogin.setAction(new LoginAction());
			this.mntmLogin.setText("Login");

			this.mnSetGarageStatus.setEnabled(false);
			this.mntmSetMaximumSpaces.setEnabled(false);
			this.mntmSetParkingFee.setEnabled(false);
			this.mnGenerateReport.setEnabled(false);
			this.mntmShutdownKiosk.setEnabled(false);
		}
		this.mntmLogin.setMnemonic(KeyEvent.VK_L);

		// Update Config Menu Items
		this.mntmSetMaximumSpaces.setText("Set Maximum Spaces ("
				+ config.getTotalSpaces() + ")");
		this.mntmSetParkingFee.setText("Set Parking Fee ($"
				+ config.getParkingFee() + ")");

		// Update Status Panel
		this.lblSpacesRemaining.setText("Remaining Spaces: "
				+ config.getAvailableSpaces());
		this.lblGarageStatus.setText("Garage Status: " + config.getStatus());
		
		// Refresh Sign Panel
		this.signPanel.updateSign();
	}

	/**
	 * Get the signPanel.
	 * 
	 * @return the signPanel
	 */
	public SignPanel getSignPanel() {
		return this.signPanel;
	}

	/**
	 * Get the gatePanel.
	 * 
	 * @return the gatePanel
	 */
	public GatePanel getGatePanel() {
		return this.gatePanel;
	}
	
	
}