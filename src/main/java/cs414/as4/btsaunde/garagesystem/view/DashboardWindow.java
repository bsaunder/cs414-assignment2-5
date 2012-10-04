package cs414.as4.btsaunde.garagesystem.view;

import java.awt.CardLayout;
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
import javax.swing.border.EtchedBorder;

import cs414.as4.btsaunde.garagesystem.action.ChangeGarageStatusAction;
import cs414.as4.btsaunde.garagesystem.action.LoginAction;
import cs414.as4.btsaunde.garagesystem.action.LogoutAction;
import cs414.as4.btsaunde.garagesystem.action.OpenDataViewerAction;
import cs414.as4.btsaunde.garagesystem.action.OpenReportGeneratorAction;
import cs414.as4.btsaunde.garagesystem.action.RetrieveTicketAction;
import cs414.as4.btsaunde.garagesystem.action.SetParkingFeeAction;
import cs414.as4.btsaunde.garagesystem.action.SetTotalSpacesAction;
import cs414.as4.btsaunde.garagesystem.action.ShutdownAction;
import cs414.as4.btsaunde.garagesystem.action.SummonAttendantAction;
import cs414.as4.btsaunde.garagesystem.config.GarageConfiguration;
import cs414.as4.btsaunde.garagesystem.enums.GarageStatus;
import cs414.as4.btsaunde.garagesystem.security.Identity;

/**
 * Main Dashboard for Simulator.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class DashboardWindow extends JFrame {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieve Ticket Action Command.
	 */
	public static final String RETRIEVE_TICKET_COMMAND = "RetrieveTicket";

	/**
	 * Singleton Instance.
	 */
	private static DashboardWindow instance;

	/**
	 * Start Panel
	 */
	private static String START_PANEL = "StartPanel";

	/**
	 * Retrieve Ticket Panel
	 */
	private static String RETRIEVE_TICKET_PANEL = "RetrieveTicketPanel";

	/**
	 * Pay For Ticket Panel
	 */
	private static String PAY_FOR_TICKET_PANEL = "PayForTicketPanel";

	/**
	 * Logger
	 */
	private Logger logger = Logger.getAnonymousLogger();

	/*
	 * Panel Contents
	 */
	private JPanel contentPane;
	private JLabel lblSpacesRemaining;
	private JLabel lblGarageStatus;
	private JMenuItem mntmRetrieveParkingTicket;
	private JMenuItem mntmShutdownKiosk;
	private JMenu mnDataTools;
	private JMenuItem mntmSetMaximumSpaces;
	private JMenuItem mntmSetParkingFee;
	private JMenu mnSetGarageStatus;
	private JMenuItem mntmLogin;
	private JRadioButtonMenuItem rdbtnmntmOpen;
	private JRadioButtonMenuItem rdbtnmntmClosed;
	private SignPanel signPanel;
	private GatePanel gatePanel;
	private JPanel contentPanel;
	private RetrieveTicketPanel retrieveTicketPanel;
	private PayForTicketPanel payForTicketPanel;
	private StartPanel startPanel;

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

		this.update();
	}

	/**
	 * Initialize Dashboard.
	 */
	private void intialize() {
		this.setResizable(false);
		this.setTitle("Saunders Parking Systems");
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(25, 25, 500, 500);
	}

	/**
	 * Build Dashboard Panels.
	 */
	private void buildPanels() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.signPanel = new SignPanel();
		this.signPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		this.signPanel.setBounds(5, 5, 240, 100);
		this.contentPane.add(this.signPanel);

		this.gatePanel = new GatePanel();
		this.gatePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		this.gatePanel.setBounds(250, 5, 240, 100);
		this.contentPane.add(this.gatePanel);

		this.contentPanel = new JPanel();
		this.contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		this.contentPanel.setBounds(5, 116, 485, 311);
		this.contentPanel.setLayout(new CardLayout(0, 0));

		this.startPanel = new StartPanel();
		this.contentPanel.add(this.startPanel, DashboardWindow.START_PANEL);

		this.retrieveTicketPanel = new RetrieveTicketPanel();
		this.contentPanel.add(this.retrieveTicketPanel,
				DashboardWindow.RETRIEVE_TICKET_PANEL);

		this.payForTicketPanel = new PayForTicketPanel();
		this.contentPanel.add(this.payForTicketPanel,
				DashboardWindow.PAY_FOR_TICKET_PANEL);

		this.contentPane.add(this.contentPanel);

		JPanel statusPanel = new JPanel();
		statusPanel.setBounds(0, 429, 494, 17);
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

		this.mntmRetrieveParkingTicket = new JMenuItem();
		this.mntmRetrieveParkingTicket.setAction(new RetrieveTicketAction());
		this.mntmRetrieveParkingTicket.setText("Retrieve Parking Ticket");
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

		mnAdmin.addSeparator();

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

		this.mnDataTools = new JMenu("Data Tools");
		this.mnDataTools.setMnemonic('T');
		mnAdmin.add(this.mnDataTools);

		JMenuItem mntmOpenDataViewer = new JMenuItem();
		mntmOpenDataViewer.setAction(new OpenDataViewerAction());
		mntmOpenDataViewer.setText("Open Data Viewer");
		mntmOpenDataViewer.setMnemonic('D');
		this.mnDataTools.add(mntmOpenDataViewer);
		
		JMenuItem mntmOpenReportGenerator = new JMenuItem();
		mntmOpenReportGenerator.setAction(new OpenReportGeneratorAction());
		mntmOpenReportGenerator.setText("Open Report Generator");
		mntmOpenReportGenerator.setMnemonic('R');
		this.mnDataTools.add(mntmOpenReportGenerator);

		mnAdmin.addSeparator();

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
	 * Update Entire Dashboard based on Current System Status.
	 */
	public void update() {
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
			this.mnDataTools.setEnabled(true);
			this.mntmShutdownKiosk.setEnabled(true);
		} else {
			this.mntmLogin.setAction(new LoginAction());
			this.mntmLogin.setText("Login");

			this.mnSetGarageStatus.setEnabled(false);
			this.mntmSetMaximumSpaces.setEnabled(false);
			this.mntmSetParkingFee.setEnabled(false);
			this.mnDataTools.setEnabled(false);
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

		// Update Sign Panel
		this.signPanel.update();

		// Update Start Panel
		this.startPanel.update();
		
		// Update Gate Pane
		this.gatePanel.update();
	}

	/**
	 * Starts Retrieve Ticket Workflow.
	 */
	public void startRetrieveTicket() {
		this.logger.info("Starting Retrieve Ticket Scenario");

		CardLayout panelLayout = (CardLayout) this.retrieveTicketPanel
				.getLayout();
		panelLayout.first(this.retrieveTicketPanel);

		CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
		cardLayout.show(this.contentPanel,
				DashboardWindow.RETRIEVE_TICKET_PANEL);
	}

	/**
	 * Starts Retrieve Ticket Workflow.
	 */
	public void startPayForTicket() {
		this.logger.info("Starting Pay For Ticket Scenario");

		CardLayout panelLayout = (CardLayout) this.payForTicketPanel
				.getLayout();
		panelLayout.first(this.payForTicketPanel);

		CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
		cardLayout
				.show(this.contentPanel, DashboardWindow.PAY_FOR_TICKET_PANEL);
	}

	/**
	 * Resets Back to Main Start Panel.
	 */
	public void reset() {
		this.logger.info("Resetting to Main Scenario Screen");
		CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
		cardLayout.show(this.contentPanel, DashboardWindow.START_PANEL);
	}
}
