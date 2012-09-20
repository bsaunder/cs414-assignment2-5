package cs414.as4.btsaunde.garagesystem.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

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

/**
 * Kiosk represents the Main Terminal for the Garage System.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Kiosk extends JFrame {

	/**
	 * Singleton Instance.
	 */
	private static Kiosk instance;

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	@SuppressWarnings("unused")
	private Logger logger = Logger.getAnonymousLogger();

	/**
	 * Kiosk MenuBar.
	 */
	private JMenuBar menuBar;

	/**
	 * Status Panel
	 */
	private JPanel statusPanel;

	/*
	 * Kiosk Menu Items.
	 */
	private JMenu setStatusMenu;
	private JMenuItem setSpacesMenuItem;
	private JMenuItem setFeeMenuItem;
	private JMenuItem generateReportMenuItem;
	private JMenuItem shutDownMenuItem;
	private JMenuItem accessItemMenuItem;
	private JRadioButtonMenuItem openStatusMenuItem;
	private JRadioButtonMenuItem closeStatusMenuItem;
	private JMenuItem enterGarageMenuItem;
	private JMenuItem exitGarageMenuItem;

	/*
	 * Kiosk Status Labels
	 */
	private JLabel garageStatusLabel;
	private JLabel availableSpacesLabel;

	/**
	 * Get the Kiosk Instance.
	 * 
	 * @return Current Kiosk
	 */
	public static Kiosk getInstance() {
		if (Kiosk.instance == null) {
			Kiosk.instance = new Kiosk();
		}

		return Kiosk.instance;
	}

	/**
	 * Creates a new Kiosk Instance.
	 * 
	 * @param newAttendant
	 *            On Duty Attendant
	 */
	private Kiosk() {
		this.setTitle("Saunders Parking Systems");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Full Screen Ahead!
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, screenSize.width, screenSize.height);

		this.buildMenu();
		this.buildStatusPanel();
	}

	/**
	 * Builds the Status Panel.
	 */
	private void buildStatusPanel() {
		this.statusPanel = new JPanel();
		this.statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.add(this.statusPanel, BorderLayout.SOUTH);

		this.statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		this.statusPanel.setLayout(new GridLayout(1, 2));

		this.garageStatusLabel = new JLabel();
		this.garageStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.statusPanel.add(garageStatusLabel);

		this.availableSpacesLabel = new JLabel();
		this.availableSpacesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.statusPanel.add(this.availableSpacesLabel);

		this.updateStatusPanel();
	}

	/**
	 * Refreshes The Test on the Status Panel:
	 */
	public void updateStatusPanel() {
		GarageConfiguration config = GarageConfiguration.getInstance();

		this.garageStatusLabel.setText("Garage Status: " + config.getStatus());
		this.availableSpacesLabel.setText("Available Spaces: "
				+ config.getAvailableSpaces());
	}

	/**
	 * Creates the Menu Bar.
	 */
	private void buildMenu() {
		this.menuBar = new JMenuBar();

		// System Menu
		JMenu systemMenu = new JMenu("System");
		systemMenu.setMnemonic(KeyEvent.VK_S);

		this.enterGarageMenuItem = new JMenuItem("Retrieve Ticket",
				KeyEvent.VK_R);
		systemMenu.add(this.enterGarageMenuItem);

		this.exitGarageMenuItem = new JMenuItem("Pay For Ticket", KeyEvent.VK_P);
		systemMenu.add(this.exitGarageMenuItem);

		this.updateSystemMenu();

		this.menuBar.add(systemMenu);

		// Admin Menu
		JMenu adminMenu = new JMenu("Admin");
		adminMenu.setMnemonic(KeyEvent.VK_A);

		this.buildAdminMenu(adminMenu);

		this.menuBar.add(adminMenu);

		// Help Menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		JMenuItem summonAttendant = new JMenuItem();
		summonAttendant.setAction(new SummonAttendantAction());
		summonAttendant.setText("Summon Attendant");
		summonAttendant.setMnemonic(KeyEvent.VK_S);
		helpMenu.add(summonAttendant);

		this.menuBar.add(helpMenu);

		this.setJMenuBar(this.menuBar);
	}

	/**
	 * Builds the Admin Menu
	 * 
	 * @param adminMenu
	 *            Admin Menu
	 */
	private void buildAdminMenu(JMenu adminMenu) {
		this.accessItemMenuItem = new JMenuItem();
		adminMenu.add(this.accessItemMenuItem);

		adminMenu.addSeparator();

		this.setStatusMenu = new JMenu("Set Garage Status");
		this.setStatusMenu.setMnemonic(KeyEvent.VK_S);

		ButtonGroup group = new ButtonGroup();
		this.openStatusMenuItem = new JRadioButtonMenuItem();
		this.openStatusMenuItem.setAction(new ChangeGarageStatusAction(
				GarageStatus.OPEN));
		this.openStatusMenuItem.setText("Open");
		this.openStatusMenuItem.setMnemonic(KeyEvent.VK_O);

		group.add(this.openStatusMenuItem);

		this.closeStatusMenuItem = new JRadioButtonMenuItem();
		this.closeStatusMenuItem.setAction(new ChangeGarageStatusAction(
				GarageStatus.CLOSED));
		this.closeStatusMenuItem.setText("Closed");
		this.closeStatusMenuItem.setMnemonic(KeyEvent.VK_C);
		group.add(this.closeStatusMenuItem);

		this.setStatusMenu.add(this.openStatusMenuItem);
		this.setStatusMenu.add(this.closeStatusMenuItem);

		adminMenu.add(this.setStatusMenu);

		this.setSpacesMenuItem = new JMenuItem();
		this.setSpacesMenuItem.setAction(new SetTotalSpacesAction());
		this.setSpacesMenuItem.setMnemonic(KeyEvent.VK_T);
		adminMenu.add(this.setSpacesMenuItem);

		this.setFeeMenuItem = new JMenuItem();
		this.setFeeMenuItem.setAction(new SetParkingFeeAction());
		this.setFeeMenuItem.setMnemonic(KeyEvent.VK_F);
		adminMenu.add(this.setFeeMenuItem);

		this.generateReportMenuItem = new JMenuItem("Generate Report",
				KeyEvent.VK_G);
		adminMenu.add(generateReportMenuItem);

		adminMenu.addSeparator();

		this.shutDownMenuItem = new JMenuItem();
		this.shutDownMenuItem.setAction(new ShutdownAction());
		this.shutDownMenuItem.setText("Shutdown Kiosk");
		this.shutDownMenuItem.setMnemonic(KeyEvent.VK_K);
		adminMenu.add(this.shutDownMenuItem);

		this.updateAdminMenu();
	}

	/**
	 * Refresh the Kiosk Screen.
	 */
	public void refresh() {
		this.updateAdminMenu();
		this.updateStatusPanel();
		this.updateSystemMenu();
	}

	/**
	 * Updates the System Menu.
	 */
	public void updateSystemMenu() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		if (config.getStatus() == GarageStatus.OPEN) {
			this.enterGarageMenuItem.setEnabled(true);
		} else {
			this.enterGarageMenuItem.setEnabled(false);
		}
	}

	/**
	 * Update Login/Logout Buttons
	 */
	public void updateAdminMenu() {
		Identity identity = Identity.getInstance();
		if (identity.isAuthenticated()) {
			this.accessItemMenuItem.setAction(new LogoutAction());
			this.accessItemMenuItem.setText("Logout");

			this.setStatusMenu.setEnabled(true);
			this.setSpacesMenuItem.setEnabled(true);
			this.setFeeMenuItem.setEnabled(true);
			this.generateReportMenuItem.setEnabled(true);
			this.shutDownMenuItem.setEnabled(true);
			this.openStatusMenuItem.setEnabled(true);
			this.closeStatusMenuItem.setEnabled(true);
		} else {
			this.accessItemMenuItem.setAction(new LoginAction());
			this.accessItemMenuItem.setText("Login");

			this.setStatusMenu.setEnabled(false);
			this.setSpacesMenuItem.setEnabled(false);
			this.setFeeMenuItem.setEnabled(false);
			this.generateReportMenuItem.setEnabled(false);
			this.shutDownMenuItem.setEnabled(false);
			this.openStatusMenuItem.setEnabled(false);
			this.closeStatusMenuItem.setEnabled(false);
		}
		this.accessItemMenuItem.setMnemonic(KeyEvent.VK_L);

		this.updateStatusMenu();
		this.updateSetFeeText();
		this.updateSetSpacesText();
	}

	/**
	 * Update Garage Status Menu
	 */
	private void updateStatusMenu() {
		// Set Menu Items to Match Config
		GarageConfiguration config = GarageConfiguration.getInstance();
		if (config.getStatus() == GarageStatus.CLOSED) {
			this.closeStatusMenuItem.setSelected(true);
			this.openStatusMenuItem.setSelected(false);
		} else {
			this.closeStatusMenuItem.setSelected(false);
			this.openStatusMenuItem.setSelected(true);
		}
	}

	/**
	 * Updates the Total Spaces
	 */
	private void updateSetSpacesText() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		this.setSpacesMenuItem.setText("Set Total Spaces ("
				+ config.getTotalSpaces() + ")");
	}

	/**
	 * Updates the Set Fee Text
	 */
	private void updateSetFeeText() {
		GarageConfiguration config = GarageConfiguration.getInstance();
		this.setFeeMenuItem.setText("Set 15m Fee ($" + config.getParkingFee()
				+ ")");
	}

}
