
/***
 *   APPLICATION NAME: 	MATH FARM
 * 			TEAM NAME: 	3 MUSKETEERS
 * 		 TEAM MEMBERS: 	JONATHAN DUQUETTE, MICHAEL REEVES, MUKILARASI SELVARAJU
 * 			 SEMESTER:	FALL SEMESTER, 2021
 * 			   COURSE: 	CSCI 362 - SOFTWARE ENGINEERING
 * 			PROFESSOR:	MARK MORABITO
 * 		 INSTITUTION:	FRAMINGHAM STATE UNIVERSITY
***/

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Main {

	private JFrame frame;
	private JPanel headerPanel;
	private JLayeredPane layeredPane;
	private JPanel loginPanel;
	private JPanel adminLoginPanel;
	private JPanel adminHomePanel;
	private JPanel adminAddUserPanel;
	private JPanel adminDeleteUserPanel;
	private JPanel adminReportsPanel;
	private JPanel adminPasswordPanel;
	private JPanel badgePanel;
	private JPanel homePanel;
	private JPanel gradePanel;
	private JPanel learnPracticePanel;
	private JPanel testPanel;
	private JPanel learnPanel;
	private JPanel activityPanel;
	private JPanel practicePanel;
	private JLabel headerLabel;

	// Global constants for overall frame's ratio and buffer
	int PRIMARY_RATIO;
	int FRAME_WIDTH_BUFFER;
	int FRAME_HEIGHT_BUFFER;

	private JLabel backgroundLabel;
	private JLabel title;

	// Global ints for user's test score
	private int testScore = 0;
	private int testCounter = 0;

	// Global array for panel hierarchy (used for Back button and header titles)
	private JPanel[] panelList = new JPanel[10];
	private String[] panelTitle = new String[10];
	private int panelCounter = 0;

	// Global array for clickable button RGB values
	private int[] GREEN_RGB = { 146, 208, 80 };
	private int[] SKY_RGB = { 139, 197, 219 };
	private int[] DARK_GREEN_RGB = { 0, 176, 80 };
	private int[] GRAY_BUTTON = { 200, 200, 200 };

	// Global variable to keep the user active session
	boolean isUserLoggedIn = false;
	boolean isAdminLoggedIn = false;

	// Set raised bevel border variable
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();

	Login login;
	String username;

	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Method for changing panels whenever a button is clicked
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	// Run main application
	public Main() {
		initialize();
	}

	// Initialize the contents of the frame
	private void initialize() {

		login = new Login();

		// Initialize frame. Set dimensions to user's screen size
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 800);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Set a primary ratio that is equal to screen's width / 30
		// Set pixel buffers to account for frame's external width/height border
		PRIMARY_RATIO = frame.getWidth() / 40;
		FRAME_WIDTH_BUFFER = 30;
		FRAME_HEIGHT_BUFFER = 90;

		createHeaderPanel();
		headerPanel.setVisible(false);

		// Initialize layered pane
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(PRIMARY_RATIO, headerPanel.getHeight() + PRIMARY_RATIO * 2,
				frame.getWidth() - FRAME_WIDTH_BUFFER - PRIMARY_RATIO * 2,
				frame.getHeight() - FRAME_HEIGHT_BUFFER - PRIMARY_RATIO * 3 - headerPanel.getHeight());
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		createLoginPanel();

		// Set background
		setBackground();

	}

	////////// LOGIN PANEL //////////
	public void createLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setBounds(PRIMARY_RATIO, headerPanel.getHeight() + PRIMARY_RATIO * 2, layeredPane.getWidth(),
				layeredPane.getHeight());
		layeredPane.add(loginPanel);
		loginPanel.setBackground(new Color(0, 0, 0, 0));
		loginPanel.setLayout(null);
		panelList[panelCounter] = loginPanel;

		// addHeading("MATHFARM");

		title = new JLabel();

		// Set background to same size as frame, minus buffer to account for external
		// frame edge region
		int titleWidth = frame.getWidth() - FRAME_WIDTH_BUFFER - PRIMARY_RATIO * 6;
		title.setBounds((frame.getWidth() - titleWidth) / 2, PRIMARY_RATIO, titleWidth,
				(int) ((frame.getWidth() - FRAME_WIDTH_BUFFER - PRIMARY_RATIO * 6) / 5.32));
		frame.getContentPane().add(title);

		// Set background image to same size as backgroundLabel
		Image titleImage = new ImageIcon(this.getClass().getResource("/title.png")).getImage()
				.getScaledInstance(title.getWidth(), title.getHeight(), java.awt.Image.SCALE_SMOOTH);
		frame.getContentPane().setLayout(null);
		title.setIcon(new ImageIcon(titleImage));

		JLabel loginUsername = new JLabel("USERNAME:");
		loginUsername.setHorizontalAlignment(SwingConstants.CENTER);
		loginUsername.setBounds(loginPanel.getWidth() / 2 - loginPanel.getWidth() / 5,
				loginPanel.getHeight() / 2 - PRIMARY_RATIO * 3, loginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginUsername.setFont(new Font("Calibri", Font.PLAIN, loginUsername.getHeight() / 2));
		loginUsername.setBackground(Color.ORANGE);
		loginUsername.setOpaque(true);
		loginUsername.setBorder(raisedbevel);

		loginPanel.add(loginUsername);

		JLabel loginPassword = new JLabel("PASSWORD:");
		loginPassword.setHorizontalAlignment(SwingConstants.CENTER);
		loginPassword.setBounds(loginPanel.getWidth() / 2 - loginPanel.getWidth() / 5, loginPanel.getHeight() / 2,
				loginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginPassword.setFont(new Font("Calibri", Font.PLAIN, loginPassword.getHeight() / 2));
		loginPassword.setBackground(Color.ORANGE);
		loginPassword.setOpaque(true);
		loginPassword.setBorder(raisedbevel);
		loginPanel.add(loginPassword);

		JTextField loginUsernameField = new JTextField();
		loginUsernameField.setBounds(loginPanel.getWidth() / 2, loginPanel.getHeight() / 2 - PRIMARY_RATIO * 3,
				loginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginUsernameField.setColumns(20);
		loginUsernameField.setFont(new Font("Calibri", Font.PLAIN, loginUsernameField.getHeight() / 2));
		loginUsernameField.setBackground(SystemColor.menu);
		loginUsernameField.setBorder(BorderFactory.createRaisedBevelBorder());
		loginPanel.add(loginUsernameField);

		JPasswordField loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(loginPanel.getWidth() / 2, loginPanel.getHeight() / 2, loginPanel.getWidth() / 5,
				PRIMARY_RATIO * 2);
		loginPasswordField.setColumns(20);
		loginPasswordField.setFont(new Font("Calibri", Font.PLAIN, loginPasswordField.getHeight() / 2));
		loginPasswordField.setBackground(SystemColor.menu);
		loginPasswordField.setBorder(BorderFactory.createRaisedBevelBorder());
		loginPanel.add(loginPasswordField);

		JLabel error = new JLabel("Please enter valid username and password!");
		error.setBounds(loginPanel.getWidth() / 2 - loginPanel.getWidth() / 5 + PRIMARY_RATIO,
				headerPanel.getHeight() + PRIMARY_RATIO * 2, loginPanel.getWidth() / 5 * 2, PRIMARY_RATIO * 2);
		error.setFont(new Font("Calibri", Font.PLAIN, error.getHeight() / 3));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setBackground(SystemColor.menu);
		error.setOpaque(true);
		error.setBorder(raisedbevel);
		frame.getContentPane().add(error);
		error.setVisible(false);

		JButton loginButton = new JButton("LOG IN");
		loginPanel.add(loginButton);
		createButton(loginButton, loginPanel.getWidth() / 2 - loginPanel.getWidth() / 10,
				loginPanel.getHeight() / 2 + PRIMARY_RATIO * 3, loginPanel.getWidth() / 5, PRIMARY_RATIO * 2, GREEN_RGB,
				null, 1, 1, true);
		loginButton.setFont(new Font("Calibri", Font.PLAIN, loginButton.getHeight() / 2));

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String uid = loginUsernameField.getText();
				String pwd = String.valueOf(loginPasswordField.getPassword());
				isUserLoggedIn = login.login(loginUsernameField.getText(), pwd);
				loginButton.setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
				if (uid.equals("") || pwd.equals("") || !isUserLoggedIn) {
					error.setVisible(true);
					return;
				} else {
					username = uid;
					createHomePanel();
					headerPanel.setVisible(true);
					error.setVisible(false);
					title.setVisible(false);
					loginUsernameField.setText(null);
					loginPasswordField.setText(null);
					switchPanel(homePanel);
				}

			}
		});

		JButton adminButton = new JButton();
		loginPanel.add(adminButton);
		int[] ADMIN_RGB = { 28, 138, 219 };
		createButton(adminButton, loginPanel.getWidth() - 100, loginPanel.getHeight() - 100, 75, 75, ADMIN_RGB,
				"/admin.png", 75, 75, false);

		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				title.setVisible(false);
				error.setVisible(false);
				createAdminLoginPanel();
				switchPanel(adminLoginPanel);
			}
		});
	}

	////////// ADMIN LOGIN PANEL //////////
	public void createAdminLoginPanel() {
		adminLoginPanel = new JPanel();
		adminLoginPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminLoginPanel);
		adminLoginPanel.setLayout(null);

		backgroundLabel.setVisible(false);

		JLabel loginUsername = new JLabel("USERNAME:");
		loginUsername.setHorizontalAlignment(SwingConstants.CENTER);
		loginUsername.setBounds(adminLoginPanel.getWidth() / 2 - adminLoginPanel.getWidth() / 5,
				adminLoginPanel.getHeight() / 2 - PRIMARY_RATIO * 3, adminLoginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginUsername.setFont(new Font("Calibri", Font.PLAIN, loginUsername.getHeight() / 2));
		loginUsername.setOpaque(true);

		adminLoginPanel.add(loginUsername);

		JLabel loginPassword = new JLabel("PASSWORD:");
		loginPassword.setHorizontalAlignment(SwingConstants.CENTER);
		loginPassword.setBounds(adminLoginPanel.getWidth() / 2 - adminLoginPanel.getWidth() / 5,
				adminLoginPanel.getHeight() / 2, adminLoginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginPassword.setFont(new Font("Calibri", Font.PLAIN, loginPassword.getHeight() / 2));
		loginPassword.setOpaque(true);
		adminLoginPanel.add(loginPassword);

		JTextField loginUsernameField = new JTextField();
		loginUsernameField.setBounds(adminLoginPanel.getWidth() / 2,
				adminLoginPanel.getHeight() / 2 - PRIMARY_RATIO * 3, adminLoginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginUsernameField.setColumns(20);
		loginUsernameField.setFont(new Font("Calibri", Font.PLAIN, loginUsernameField.getHeight() / 2));
		loginUsernameField.setBackground(Color.WHITE);
		loginUsernameField.setBorder(BorderFactory.createRaisedBevelBorder());
		adminLoginPanel.add(loginUsernameField);

		JPasswordField loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(adminLoginPanel.getWidth() / 2, adminLoginPanel.getHeight() / 2,
				adminLoginPanel.getWidth() / 5, PRIMARY_RATIO * 2);
		loginPasswordField.setColumns(20);
		loginPasswordField.setFont(new Font("Calibri", Font.PLAIN, loginPasswordField.getHeight() / 2));
		loginPasswordField.setBackground(Color.WHITE);
		loginPasswordField.setBorder(BorderFactory.createRaisedBevelBorder());
		adminLoginPanel.add(loginPasswordField);

		JLabel error = new JLabel("Please enter valid admin username and password!");
		error.setBounds(loginPanel.getWidth() / 2 - loginPanel.getWidth() / 5, 0, loginPanel.getWidth() / 5 * 2,
				PRIMARY_RATIO * 2);
		error.setFont(new Font("Calibri", Font.PLAIN, error.getHeight() / 3));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setBackground(SystemColor.menu);
		error.setOpaque(true);
		error.setBorder(raisedbevel);
		adminLoginPanel.add(error);
		error.setVisible(false);

		JButton loginButton = new JButton("LOG IN");
		adminLoginPanel.add(loginButton);
		createButton(loginButton, adminLoginPanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				adminLoginPanel.getHeight() / 2 + PRIMARY_RATIO * 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 2,
				PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1, true);
		loginButton.setFont(new Font("Calibri", Font.PLAIN, loginButton.getHeight() / 2));

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String uid = loginUsernameField.getText();
				String pwd = String.valueOf(loginPasswordField.getPassword());
				isAdminLoggedIn = login.adminLogin(loginUsernameField.getText(), pwd);
				if (uid.equals("") || pwd.equals("") || !isAdminLoggedIn) {
					error.setVisible(true);
					return;
				} else {
					createAdminHomePanel();
					username = uid;
					error.setVisible(false);
					loginUsernameField.setText(null);
					loginPasswordField.setText(null);
					loginButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
					switchPanel(adminHomePanel);
				}
			}
		});

		JButton backButton = new JButton("BACK");
		adminLoginPanel.add(backButton);
		createButton(backButton, adminLoginPanel.getWidth() / 2 - adminLoginPanel.getWidth() / 5,
				adminLoginPanel.getHeight() / 2 + PRIMARY_RATIO * 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 2,
				PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1, true);
		backButton.setFont(new Font("Calibri", Font.PLAIN, loginButton.getHeight() / 2));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				backgroundLabel.setVisible(true);
				backButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				title.setVisible(true);
				switchPanel(loginPanel);
			}
		});
	}

	////////// ADMIN HOME PANEL //////////
	public void createAdminHomePanel() {
		adminHomePanel = new JPanel();
		adminHomePanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminHomePanel);
		adminHomePanel.setBackground(SystemColor.menu);
		adminHomePanel.setLayout(null);

		JButton addUserButton = new JButton("Add user");
		adminHomePanel.add(addUserButton);
		createButton(addUserButton, adminHomePanel.getWidth() / 2 - adminHomePanel.getWidth() / 5 - PRIMARY_RATIO,
				adminHomePanel.getHeight() / 2 - PRIMARY_RATIO * 3, adminHomePanel.getWidth() / 5, PRIMARY_RATIO * 2,
				GRAY_BUTTON, null, 1, 1, true);
		addUserButton.setFont(new Font("Calibri", Font.PLAIN, addUserButton.getHeight() / 3));

		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				createAdminAddUserPanel();
				addUserButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				switchPanel(adminAddUserPanel);
			}
		});

		JButton deleteUserButton = new JButton("Delete user");
		adminHomePanel.add(deleteUserButton);
		createButton(deleteUserButton, adminHomePanel.getWidth() / 2 + PRIMARY_RATIO,
				adminHomePanel.getHeight() / 2 - PRIMARY_RATIO * 3, adminHomePanel.getWidth() / 5, PRIMARY_RATIO * 2,
				GRAY_BUTTON, null, 1, 1, true);
		deleteUserButton.setFont(new Font("Calibri", Font.PLAIN, deleteUserButton.getHeight() / 3));

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				createAdminDeleteUserPanel();
				deleteUserButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				switchPanel(adminDeleteUserPanel);
			}
		});

		JButton viewReportsButton = new JButton("View reports");
		adminHomePanel.add(viewReportsButton);
		createButton(viewReportsButton, adminHomePanel.getWidth() / 2 - adminHomePanel.getWidth() / 5 - PRIMARY_RATIO,
				adminHomePanel.getHeight() / 2, adminHomePanel.getWidth() / 5, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1,
				1, true);
		viewReportsButton.setFont(new Font("Calibri", Font.PLAIN, viewReportsButton.getHeight() / 3));

		viewReportsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				createAdminReportsPanel();
				viewReportsButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				switchPanel(adminReportsPanel);
			}
		});

		JButton changePasswordButton = new JButton("Change password");
		adminHomePanel.add(changePasswordButton);
		createButton(changePasswordButton, adminHomePanel.getWidth() / 2 + PRIMARY_RATIO,
				adminHomePanel.getHeight() / 2, adminHomePanel.getWidth() / 5, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1,
				1, true);
		changePasswordButton.setFont(new Font("Calibri", Font.PLAIN, changePasswordButton.getHeight() / 3));

		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				createAdminPasswordPanel();
				changePasswordButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				switchPanel(adminPasswordPanel);
			}
		});

		JButton logoutButton = new JButton("Log out");
		adminHomePanel.add(logoutButton);
		createButton(logoutButton, adminHomePanel.getWidth() / 2 - adminHomePanel.getWidth() / 10,
				adminHomePanel.getHeight() / 2 + PRIMARY_RATIO * 3, adminHomePanel.getWidth() / 5, PRIMARY_RATIO * 2,
				GRAY_BUTTON, null, 1, 1, true);
		logoutButton.setFont(new Font("Calibri", Font.PLAIN, logoutButton.getHeight() / 3));

		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				switchPanel(loginPanel);
				logoutButton.setBackground(new Color(GRAY_BUTTON[0], GRAY_BUTTON[1], GRAY_BUTTON[2]));
				backgroundLabel.setVisible(true);
				title.setVisible(true);
			}
		});

	}

	////////// ADMIN ADD USER PANEL //////////
	public void createAdminAddUserPanel() {
		adminAddUserPanel = new JPanel();
		adminAddUserPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminAddUserPanel);
		adminAddUserPanel.setBackground(SystemColor.menu);
		adminAddUserPanel.setLayout(null);

		JLabel error = new JLabel("");
		error.setBounds(adminAddUserPanel.getWidth() / 2 - adminAddUserPanel.getWidth() / 5 + PRIMARY_RATIO,
				PRIMARY_RATIO * 3, adminAddUserPanel.getWidth() / 5 * 2, PRIMARY_RATIO * 2);
		error.setFont(new Font("Calibri", Font.PLAIN, 20));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setOpaque(true);
		error.setBorder(raisedbevel);
		frame.getContentPane().add(error);
		error.setVisible(false);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername
				.setBounds(
						adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
								- PRIMARY_RATIO / 2,
						0, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO);
		adminAddUserPanel.add(lblUsername);
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, lblUsername.getHeight() / 2));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblUsername.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminAddUserPanel.add(lblPassword);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, lblPassword.getHeight() / 2));

		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblPassword.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminAddUserPanel.add(lblFirstName);
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, lblFirstName.getHeight() / 2));

		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblFirstName.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminAddUserPanel.add(lblLastName);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, lblLastName.getHeight() / 2));

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblLastName.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminAddUserPanel.add(lblGrade);
		lblGrade.setFont(new Font("Calibri", Font.PLAIN, lblGrade.getHeight() / 2));

		JLabel lblDOB = new JLabel("D.O.B (YYYY-MM-DD):");
		lblDOB.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblGrade.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminAddUserPanel.add(lblDOB);
		lblDOB.setFont(new Font("Calibri", Font.PLAIN, lblDOB.getHeight() / 2));
		lblDOB.setFont(new Font("Calibri", Font.PLAIN, lblDOB.getHeight() / 2));

		JTextField username = new JTextField();
		username.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2, 0,
				adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO);
		adminAddUserPanel.add(username);
		username.setColumns(20);

		JTextField password = new JPasswordField();
		password.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				username.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(password);

		JTextField fName = new JTextField();
		fName.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				password.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(fName);
		fName.setColumns(20);

		JTextField lName = new JTextField();
		lName.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				fName.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(lName);
		lName.setColumns(20);

		JTextField grade = new JTextField();
		grade.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				lName.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(grade);
		grade.setColumns(1);

		JTextField dob = new JTextField();
		dob.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				grade.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(dob);
		dob.setColumns(10);

		JCheckBox grantAdminAccess = new JCheckBox("Admin");
		grantAdminAccess.setFont(new Font("Calibri", Font.PLAIN, 18));
		grantAdminAccess.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				dob.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3,
				PRIMARY_RATIO);
		adminAddUserPanel.add(grantAdminAccess);

		JButton createUser = new JButton("Create User");
		createButton(createUser, adminAddUserPanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				adminAddUserPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminAddUserPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1, true);
		adminAddUserPanel.add(createUser);
		createUser.setFont(new Font("Calibri", Font.PLAIN, createUser.getHeight() / 3));

		createUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (username.getText().equals("") || password.getText().equals("") || fName.getText().equals("")
						|| lName.getText().equals("") || grade.getText().equals("") || dob.getText().equals("")) {
					error.setText("Please enter valid data!");
					error.setVisible(true);
				} else {
					login.addUser(username.getText(), password.getText(), fName.getText(), lName.getText(),
							grade.getText(), dob.getText(), grantAdminAccess.isSelected());
					error.setText("User created!");
					error.setVisible(true);
				}

			}
		});

		JButton backButton = new JButton("Back");
		adminAddUserPanel.add(backButton);
		createButton(backButton, adminAddUserPanel.getWidth() / 2 - adminAddUserPanel.getWidth() / 5,
				adminAddUserPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminAddUserPanel.getWidth() / 5 - PRIMARY_RATIO / 2, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1, true);
		backButton.setFont(new Font("Calibri", Font.PLAIN, backButton.getHeight() / 3));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				error.setVisible(false);
				switchPanel(adminHomePanel);
			}
		});

	}

	////////// ADMIN DELETE USER PANEL //////////
	public void createAdminDeleteUserPanel() {
		adminDeleteUserPanel = new JPanel();
		adminDeleteUserPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminDeleteUserPanel);
		adminDeleteUserPanel.setBackground(SystemColor.menu);
		adminDeleteUserPanel.setLayout(null);

		final JTable table;

		// Column Names
		String[] columnNames = { "USERNAME", "FIRST NAME", "LAST NAME", "GRADE", "DOB" };

		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		users = login.getAllUsers();
		String[][] usersData = new String[users.size()][columnNames.length];

		for (int i = 0; i < usersData.length; i++) {
			for (int j = 0; j < usersData[i].length; j++) {
				usersData[i][j] = users.get(i).get(j);
			}
		}

		// Initializing JTable
		table = new JTable(usersData, columnNames);
		table.setBounds(0, 0, adminDeleteUserPanel.getWidth(),
				adminDeleteUserPanel.getHeight() - adminDeleteUserPanel.getHeight() / 2 + PRIMARY_RATIO * 2);
		JScrollPane sp = new JScrollPane(table);
		table.getTableHeader().setPreferredSize(new Dimension(sp.getWidth(), PRIMARY_RATIO));
		sp.setBounds(0, 0, adminDeleteUserPanel.getWidth(),
				adminDeleteUserPanel.getHeight() - adminDeleteUserPanel.getHeight() / 2 + PRIMARY_RATIO * 2);
		adminDeleteUserPanel.add(sp);
		table.setRowSelectionAllowed(true);

		JButton deleteUser = new JButton("Delete User");
		createButton(deleteUser, adminDeleteUserPanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				adminDeleteUserPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminDeleteUserPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1,
				true);
		adminDeleteUserPanel.add(deleteUser);
		deleteUser.setFont(new Font("Calibri", Font.PLAIN, deleteUser.getHeight() / 3));

		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				int row = table.getSelectedRow();
				login.deleteUser((String) table.getValueAt(row, 0));
				createAdminDeleteUserPanel();
				switchPanel(adminDeleteUserPanel);
			}
		});

		JButton backButton = new JButton("Back");
		adminDeleteUserPanel.add(backButton);
		createButton(backButton, adminDeleteUserPanel.getWidth() / 2 - adminDeleteUserPanel.getWidth() / 5,
				adminDeleteUserPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminDeleteUserPanel.getWidth() / 5 - PRIMARY_RATIO / 2, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1,
				true);
		backButton.setFont(new Font("Calibri", Font.PLAIN, backButton.getHeight() / 3));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				switchPanel(adminHomePanel);
			}
		});

	}

	////////// ADMIN REPORTS PANEL //////////
	public void createAdminReportsPanel() {
		adminReportsPanel = new JPanel();
		adminReportsPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminReportsPanel);
		adminReportsPanel.setBackground(SystemColor.menu);
		adminReportsPanel.setLayout(null);

		final JTable table;

		// Column Names
		String[] columnNames = { "<html><b>FIRST<br>NAME", "<html><b>LAST<br>NAME", "<html><b>GRADE",
				"<html><b>HIGHEST<br>K SCORE", "<html><b>AVERAGE<br>K SCORE", "<html><b>TOTAL<br>K TESTS",
				"<html><b>HIGHEST<br>G1 SCORE", "<html><b>AVERAGE<br>G1 SCORE", "<html><b>TOTAL<br>G1 TESTS",
				"<html><b>HIGHEST<br>G2 SCORE", "<html><b>AVERAGE<br>G2 SCORE", "<html><b>TOTAL<br>G2 TESTS",
				"<html><b>HIGHEST<br>G3 SCORE", "<html><b>AVERAGE<br>G3 SCORE", "<html><b>TOTAL<br>G3 TESTS",
				"<html><b>HIGHEST<br>G4 SCORE", "<html><b>AVERAGE<br>G4 SCORE", "<html><b>TOTAL<br>G4 TESTS" };

		ArrayList<ArrayList<String>> report = new ArrayList<ArrayList<String>>();
		report = login.getReports();
		String[][] reportData = new String[report.size()][columnNames.length];

		for (int i = 0; i < reportData.length; i++) {
			for (int j = 0; j < reportData[i].length; j++) {
				reportData[i][j] = report.get(i).get(j);
			}
		}

		// Initializing JTable
		table = new JTable(reportData, columnNames);
		table.setBounds(0, 0, adminReportsPanel.getWidth(),
				adminReportsPanel.getHeight() - adminReportsPanel.getHeight() / 2 + PRIMARY_RATIO * 2);
		JScrollPane sp = new JScrollPane(table);
		table.getTableHeader().setPreferredSize(new Dimension(sp.getWidth(), PRIMARY_RATIO));
		sp.setBounds(0, 0, adminReportsPanel.getWidth(),
				adminReportsPanel.getHeight() - adminReportsPanel.getHeight() / 2 + PRIMARY_RATIO * 2);
		adminReportsPanel.add(sp);

		JButton backButton = new JButton("Back");
		adminReportsPanel.add(backButton);
		createButton(backButton, adminReportsPanel.getWidth() / 2 - adminReportsPanel.getWidth() / 10,
				adminReportsPanel.getHeight() / 2 + PRIMARY_RATIO * 3, adminReportsPanel.getWidth() / 5,
				PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1, true);
		backButton.setFont(new Font("Calibri", Font.PLAIN, backButton.getHeight() / 3));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				switchPanel(adminHomePanel);
			}
		});

	}

	////////// ADMIN PASSWORD PANEL //////////
	public void createAdminPasswordPanel() {
		adminPasswordPanel = new JPanel();
		adminPasswordPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		layeredPane.add(adminPasswordPanel);
		adminPasswordPanel.setBackground(SystemColor.menu);
		adminPasswordPanel.setLayout(null);

		JLabel error = new JLabel("");
		error.setBounds(adminPasswordPanel.getWidth() / 2 - adminPasswordPanel.getWidth() / 5, 0,
				adminPasswordPanel.getWidth() / 5 * 2, PRIMARY_RATIO * 2);
		error.setFont(new Font("Calibri", Font.PLAIN, 20));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setForeground(Color.RED);
		error.setOpaque(true);
		error.setBorder(raisedbevel);
		adminPasswordPanel.add(error);
		error.setVisible(false);

		JLabel lblPassword1 = new JLabel("Enter new password:");
		lblPassword1.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				PRIMARY_RATIO * 3, adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO);
		adminPasswordPanel.add(lblPassword1);
		lblPassword1.setFont(new Font("Calibri", Font.PLAIN, lblPassword1.getHeight() / 2));

		JLabel lblPassword2 = new JLabel("Repeat new password:");
		lblPassword2.setBounds(
				adminLoginPanel.getWidth() / 2 - ((adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2)
						- PRIMARY_RATIO / 2,
				lblPassword1.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				(adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3) / 3 * 2, PRIMARY_RATIO);
		adminPasswordPanel.add(lblPassword2);
		lblPassword2.setFont(new Font("Calibri", Font.PLAIN, lblPassword2.getHeight() / 2));

		JTextField password1 = new JPasswordField();
		password1.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2, PRIMARY_RATIO * 3,
				adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO);
		adminPasswordPanel.add(password1);
		password1.setColumns(20);

		JTextField password2 = new JPasswordField();
		password2.setBounds(adminHomePanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				password1.getY() + PRIMARY_RATIO + PRIMARY_RATIO / 3,
				adminLoginPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO);
		adminPasswordPanel.add(password2);

		JButton changePassword = new JButton("Change password");
		createButton(changePassword, adminPasswordPanel.getWidth() / 2 + PRIMARY_RATIO / 2,
				adminPasswordPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminPasswordPanel.getWidth() / 5 - PRIMARY_RATIO / 3, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1,
				true);
		adminPasswordPanel.add(changePassword);
		changePassword.setFont(new Font("Calibri", Font.PLAIN, changePassword.getHeight() / 3));

		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (password1.getText().equals("") || password2.getText().equals("")) {
					error.setText("Please enter a valid password!");
					error.setVisible(true);
				} else if (!password1.getText().equals(password2.getText())) {
					error.setText("Passwords do not match!");
					error.setVisible(true);
				} else {
					login.updatePassword(username, password1.getText());
					error.setText("Password successfully changed!");
					error.setVisible(true);
				}
			}
		});

		JButton backButton = new JButton("Back");
		adminPasswordPanel.add(backButton);
		createButton(backButton, adminPasswordPanel.getWidth() / 2 - adminPasswordPanel.getWidth() / 5,
				adminPasswordPanel.getHeight() / 2 + PRIMARY_RATIO * 3,
				adminPasswordPanel.getWidth() / 5 - PRIMARY_RATIO / 2, PRIMARY_RATIO * 2, GRAY_BUTTON, null, 1, 1,
				true);
		backButton.setFont(new Font("Calibri", Font.PLAIN, backButton.getHeight() / 3));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				error.setVisible(false);
				switchPanel(adminHomePanel);
			}
		});

	}

	////////// HEADER PANEL //////////
	public void createHeaderPanel() {

		headerPanel = new JPanel();
		headerPanel.setBounds(PRIMARY_RATIO, PRIMARY_RATIO, frame.getWidth() - FRAME_WIDTH_BUFFER - PRIMARY_RATIO * 2,
				PRIMARY_RATIO * 4);
		frame.getContentPane().add(headerPanel);
		headerPanel.setBackground(new Color(0, 0, 0, 0));
		headerPanel.setLayout(null);

		// Header content title label
		headerLabel = new JLabel("CHOOSE YOUR GRADE");
		headerLabel.setVerticalAlignment(SwingConstants.CENTER);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("Calibri", Font.PLAIN, (headerPanel.getHeight() - PRIMARY_RATIO) / 3));
		headerLabel.setBackground(Color.ORANGE);
		headerLabel.setBounds(headerPanel.getWidth() / 4, 0, headerPanel.getWidth() / 2,
				headerPanel.getHeight() - PRIMARY_RATIO);
		headerPanel.add(headerLabel);
		headerLabel.setOpaque(true);
		headerLabel.setBorder(raisedbevel);

		// Home button
		JButton homeButton = new JButton();
		headerPanel.add(homeButton);

		// Set button height/width to headerPanel height
		createButton(homeButton, 0, 0, headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				SKY_RGB, "/homeButton.png", headerPanel.getHeight() - PRIMARY_RATIO,
				headerPanel.getHeight() - PRIMARY_RATIO, false);

		// Set text below Home button
		JLabel homeText = new JLabel();
		headerPanel.add(homeText);
		homeText.setBounds(0, headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				PRIMARY_RATIO * 3 / 2);
		homeText.setFont(new Font("Calibri", Font.PLAIN, (int) PRIMARY_RATIO / 8 * 7));
		homeText.setText("HOME");
		homeText.setHorizontalAlignment(SwingConstants.CENTER);
		homeText.setVerticalAlignment(SwingConstants.CENTER);

		// When clicked, return to HOME PANEL
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(homePanel);
				panelCounter = 0;
				headerLabel.setText(panelTitle[panelCounter]);
			}
		});

		// Back button
		JButton backButton = new JButton();
		headerPanel.add(backButton);

		// Set button height/width to headerPanel height
		createButton(backButton,
				(headerPanel.getWidth() / 4 - headerPanel.getHeight() * 2) / 2 + headerPanel.getHeight(), 0,
				headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO, SKY_RGB,
				"/backButton.png", headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				false);

		// Set text below Back button
		JLabel backText = new JLabel();
		headerPanel.add(backText);
		backText.setBounds((headerPanel.getWidth() / 4 - headerPanel.getHeight() * 2) / 2 + headerPanel.getHeight(),
				headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				PRIMARY_RATIO * 3 / 2);
		backText.setFont(new Font("Calibri", Font.PLAIN, (int) PRIMARY_RATIO / 8 * 7));
		backText.setText("BACK");
		backText.setHorizontalAlignment(SwingConstants.CENTER);
		backText.setVerticalAlignment(SwingConstants.CENTER);

		// When clicked, return to previous panel
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelCounter != 0) {
					switchPanel(panelList[--panelCounter]);
					headerLabel.setText(panelTitle[panelCounter]);

					// Rest test score and counter
					testScore = 0;
					testCounter = 0;
				}

			}
		});

		// Badge button
		JButton badgeButton = new JButton();
		headerPanel.add(badgeButton);

		// Set button height/width to headerPanel height
		createButton(badgeButton, headerPanel.getWidth() / 4 * 3 + PRIMARY_RATIO, 0,
				headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO, SKY_RGB,
				"/badgesButton.png", headerPanel.getHeight() - PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				false);

		// Set text below Badge button
		JLabel badgeText = new JLabel();
		headerPanel.add(badgeText);
		badgeText.setBounds(headerPanel.getWidth() / 4 * 3 + PRIMARY_RATIO, headerPanel.getHeight() - PRIMARY_RATIO,
				headerPanel.getHeight() - PRIMARY_RATIO, PRIMARY_RATIO * 3 / 2);
		badgeText.setFont(new Font("Calibri", Font.PLAIN, (int) PRIMARY_RATIO / 8 * 7));
		badgeText.setText("BADGES");
		badgeText.setHorizontalAlignment(SwingConstants.CENTER);
		badgeText.setVerticalAlignment(SwingConstants.CENTER);

		// When clicked, return to previous panel
		badgeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createBadgePanel();
				panelCounter = 0;
				panelList[++panelCounter] = badgePanel;
				panelTitle[panelCounter] = "YOUR BADGES";
				headerLabel.setText(panelTitle[panelCounter]);
				switchPanel(badgePanel);

				// Rest test score and counter
				testScore = 0;
				testCounter = 0;

			}
		});

		// LogOut Button
		JButton logOutButton = new JButton();
		headerPanel.add(logOutButton);

		// Set button height/width to headerPanel height
		createButton(logOutButton, headerPanel.getWidth() - headerPanel.getHeight(), 0, headerPanel.getHeight(),
				headerPanel.getHeight() - PRIMARY_RATIO, SKY_RGB, "/logOutButton.png", headerPanel.getHeight(),
				(int) ((headerPanel.getHeight() - PRIMARY_RATIO) * 0.75), false);

		// Set text below Log out button
		JLabel logOutText = new JLabel();
		headerPanel.add(logOutText);
		logOutText.setBounds(headerPanel.getWidth() - headerPanel.getHeight(), headerPanel.getHeight() - PRIMARY_RATIO,
				headerPanel.getHeight(), PRIMARY_RATIO * 3 / 2);
		logOutText.setFont(new Font("Calibri", Font.PLAIN, (int) PRIMARY_RATIO / 8 * 7));
		logOutText.setText("LOG OUT");
		logOutText.setHorizontalAlignment(SwingConstants.CENTER);
		logOutText.setVerticalAlignment(SwingConstants.CENTER);

		// When clicked, return to LOGIN PAGE
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelCounter = 0;
				headerPanel.setVisible(false);
				title.setVisible(true);
				switchPanel(loginPanel);

			}
		});

	}

	////////// BADGE SCREEN //////////
	public void createBadgePanel() {

		badgePanel = new JPanel();
		layeredPane.add(badgePanel);
		badgePanel.setBackground(new Color(0, 0, 0, 0));
		badgePanel.setLayout(null);

		// Starting x coordinate for first badge
		int badgePanelXCoordinate = 0;

		// Image file names for unlocked badges
		String[] badgeList = { "/badgeChicken.png", "/badgePig.png", "/badgeSheep.png", "/badgeCow.png",
				"/badgeHorse.png" };
		JLabel badgeLabel;

		// Run label for each badge
		for (int i = 0; i < 5; i++) {

			badgeLabel = new JLabel("");
			Image badgeImage;

			int badgeSize = (layeredPane.getWidth() - PRIMARY_RATIO * 2) / 5;

			// If the badge has been unlocked by user, display golden badge. Otherwise,
			// display blank badge
			if (login.isBadgeUnlocked(username, i)) {
				badgeImage = new ImageIcon(this.getClass().getResource(badgeList[i])).getImage()
						.getScaledInstance(badgeSize, badgeSize, java.awt.Image.SCALE_SMOOTH);
			} else {
				badgeImage = new ImageIcon(this.getClass().getResource("/badge.png")).getImage()
						.getScaledInstance(badgeSize, badgeSize, java.awt.Image.SCALE_SMOOTH);
			}

			badgeLabel.setBounds(badgePanelXCoordinate, layeredPane.getHeight() / 2 - PRIMARY_RATIO * 3, badgeSize,
					badgeSize);
			badgeLabel.setIcon(new ImageIcon(badgeImage));
			badgePanel.add(badgeLabel);

			badgePanelXCoordinate += PRIMARY_RATIO / 2 + badgeSize;

		}

	}

	////////// HOME SCREEN //////////
	public void createHomePanel() {

		homePanel = new JPanel();
		layeredPane.add(homePanel, "name_180568393921300");
		homePanel.setBackground(new Color(0, 0, 0, 0));
		homePanel.setLayout(null);

		panelList[panelCounter] = homePanel;
		panelTitle[panelCounter] = "CHOOSE YOUR GRADE";
		headerLabel.setText(panelTitle[panelCounter]);

		// Array for each home panel animal button
		JButton[] homePanelButtons = new JButton[5];
		String[] homePanelText = { "K", "1", "2", "3", "4" };

		// Array for each home panel animal image
		String[] homePanelAnimalImages = { "/homeChicken.png", "/homePig.png", "/homeSheep.png", "/homeCow.png",
				"/homeHorse.png" };

		// Array for each home panel animal ratio
		double[] homePanelAnimalRatios = { 0.62, 0.86, 0.96, 0.88, 0.84 };

		// Starting x coordinate for first home button
		int homePanelButtonXCoordinate = 0;

		// Create each home screen animal button via loop
		for (int i = 0; i < 5; i++) {

			homePanelButtons[i] = new JButton("");
			createButton(homePanelButtons[i], homePanelButtonXCoordinate,
					layeredPane.getHeight() / 2 - layeredPane.getHeight() / 3, PRIMARY_RATIO * 6,
					layeredPane.getHeight() / 3 * 2, GREEN_RGB, "", 1, 1, true);
			homePanel.add(homePanelButtons[i]);
			homePanelButtonXCoordinate += (layeredPane.getWidth() - PRIMARY_RATIO * 6) / 4;

			// Set j equal to i for button click method
			int j = i;

			Image homePanelAnimalImage;
			homePanelAnimalImage = new ImageIcon(this.getClass().getResource(homePanelAnimalImages[i])).getImage()
					.getScaledInstance((int) (PRIMARY_RATIO * 5 * homePanelAnimalRatios[i]), PRIMARY_RATIO * 5,
							java.awt.Image.SCALE_SMOOTH);

			// Split button into image and text
			JLabel buttonImage = new JLabel();
			JLabel buttonText = new JLabel();
			homePanelButtons[i].setLayout(null);
			homePanelButtons[i].add(buttonImage);
			homePanelButtons[i].add(buttonText);
			buttonImage.setIcon(new ImageIcon(homePanelAnimalImage));
			buttonImage.setHorizontalAlignment(SwingConstants.CENTER);
			buttonText.setFont(new Font("Calibri", Font.PLAIN, (homePanelButtons[i].getHeight() / 5)));
			buttonText.setText(homePanelText[i]);
			buttonText.setHorizontalAlignment(SwingConstants.CENTER);
			buttonImage.setBounds(0, 0, homePanelButtons[i].getWidth(), layeredPane.getHeight() / 2);
			buttonText.setBounds(0, homePanelButtons[i].getHeight() / 3 * 2, homePanelButtons[i].getWidth(),
					homePanelButtons[i].getHeight() / 3);

			// When button is clicked, switch to GRADE PANEL
			homePanelButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Set user's grade selection to current index
					int gradeSelection = j;

					createGradePanel(gradeSelection);
					panelList[++panelCounter] = gradePanel;
					panelTitle[panelCounter] = "CHOOSE A TOPIC";
					headerLabel.setText(panelTitle[panelCounter]);
					homePanelButtons[j].setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
					switchPanel(gradePanel);

				}
			});

		}

	}

	////////// GRADE PANEL //////////
	public void createGradePanel(int gradeSelection) {
		gradePanel = new JPanel();
		layeredPane.add(gradePanel, "name_180577775611500");
		gradePanel.setBackground(new Color(0, 0, 0, 0));
		gradePanel.setLayout(null);

		// Array for each grade panel button
		JButton[] gradePanelButtons = new JButton[5];

		// Array for all grade categories
		String[][] categories = { { "<html><center>ADD & <br>SUBTRACT</html>", "COUNT", "COMPARE", "SHAPES" },
				{ "<html><center>ADD & <br>SUBTRACT</html>", "<html><center>TIME & <br>MONEY</html>", "COMPARE",
						"SHAPES" },
				{ "<html><center>ADD & <br>SUBTRACT</html>", "<html><center>TIME & <br>MONEY</html>", "MEASURE",
						"GEOMETRY" },
				{ "<html><center>MULTIPLY & <br>DIVIDE</html>", "FRACTIONS", "MEASURE", "GEOMETRY" },
				{ "<html><center>MULTIPLY & <br>DIVIDE</html>", "FRACTIONS", "MEASURE", "GEOMETRY" } };

		String[][] gradePanelImages = {
				{ "/addSubtractButton.png", "/countButton.png", "/compareButton.png", "/shapesButton.png",
						"/happyChicken.png" },
				{ "/addSubtractButton.png", "/timeMoneyButton.png", "/compareButton.png", "/shapesButton.png",
						"/happyPig.png" },
				{ "/addSubtractButton.png", "/timeMoneyButton.png", "/measureButton.png", "/shapesButton.png",
						"/happySheep.png" },
				{ "/arithmeticButton.png", "/fractionsButton.png", "/measureButton.png", "/shapesButton.png",
						"/happyCow.png" },
				{ "/arithmeticButton.png", "/fractionsButton.png", "/measureButton.png", "/shapesButton.png",
						"/happyHorse.png" } };
		double[][] gradePanelImageRatios = { { 2.11, 0.94, 1.49, 0.92, 0.62 }, { 2.11, 1.0, 1.49, 0.92, 0.86 },
				{ 2.11, 1.0, 1.99, 0.92, 0.96 }, { 1.0, 0.74, 1.99, 0.92, 0.88 }, { 1.0, 0.74, 1.99, 0.92, 0.84 } };

		int gradePanelButtonXCoordinate = 0;
		int gradePanelButtonYCoordinate = 0;

		// Create each grade button via loop
		for (int i = 0; i < 4; i++) {
			gradePanelButtons[i] = new JButton("");
			createButton(gradePanelButtons[i], gradePanelButtonXCoordinate, gradePanelButtonYCoordinate,
					(layeredPane.getWidth() - PRIMARY_RATIO * 2) / 3, (layeredPane.getHeight() - PRIMARY_RATIO) / 2,
					GREEN_RGB, "", 1, 1, true);
			gradePanel.add(gradePanelButtons[i]);
			if (i % 2 == 0) {
				gradePanelButtonXCoordinate = layeredPane.getWidth() - (layeredPane.getWidth() - PRIMARY_RATIO * 2) / 3;
			} else {
				gradePanelButtonYCoordinate = layeredPane.getHeight() - (layeredPane.getHeight() - PRIMARY_RATIO) / 2;
				gradePanelButtonXCoordinate = 0;
			}

			// Draw image and print text on each button
			Image gradePanelImage;
			if (gradePanelImageRatios[gradeSelection][i] < 1) {
				gradePanelImage = new ImageIcon(this.getClass().getResource(gradePanelImages[gradeSelection][i]))
						.getImage()
						.getScaledInstance((int) (PRIMARY_RATIO * 4 * gradePanelImageRatios[gradeSelection][i]),
								PRIMARY_RATIO * 4, java.awt.Image.SCALE_SMOOTH);
			} else {
				gradePanelImage = new ImageIcon(this.getClass().getResource(gradePanelImages[gradeSelection][i]))
						.getImage().getScaledInstance((int) (PRIMARY_RATIO * 4),
								(int) (PRIMARY_RATIO * 4 / gradePanelImageRatios[gradeSelection][i]),
								java.awt.Image.SCALE_SMOOTH);
			}

			// Split button into image and text
			JLabel buttonImage = new JLabel();
			JLabel buttonText = new JLabel();
			gradePanelButtons[i].setLayout(null);
			gradePanelButtons[i].add(buttonImage);
			gradePanelButtons[i].add(buttonText);
			buttonImage.setIcon(new ImageIcon(gradePanelImage));
			buttonImage.setHorizontalAlignment(SwingConstants.CENTER);
			buttonText.setFont(new Font("Calibri", Font.PLAIN, gradePanelButtons[i].getHeight() / 6));
			buttonText.setText(String.valueOf(categories[gradeSelection][i]));
			buttonText.setHorizontalAlignment(SwingConstants.CENTER);

			// Alternate image/text between left and right positions
			if (i % 2 == 0) {
				buttonImage.setBounds(0, 0, gradePanelButtons[0].getWidth() / 4 * 2, gradePanelButtons[i].getHeight());
				buttonText.setBounds(gradePanelButtons[i].getWidth() / 4 * 2, 0,
						gradePanelButtons[i].getWidth() / 4 * 2, gradePanelButtons[i].getHeight());
			} else {
				buttonImage.setBounds(gradePanelButtons[i].getWidth() / 4 * 2, 0,
						gradePanelButtons[0].getWidth() / 4 * 2, gradePanelButtons[i].getHeight());
				buttonText.setBounds(0, 0, gradePanelButtons[i].getWidth() / 4 * 2, gradePanelButtons[i].getHeight());
			}

			// Set j equal to i for button click method
			int j = i;

			// When clicked, open next panel
			gradePanelButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Set user's category selection to current index
					int categorySelection = j;
					createLearnPracticePanel(gradeSelection, categorySelection);
					panelList[++panelCounter] = learnPracticePanel;
					panelTitle[panelCounter] = "LEARN OR PRACTICE?";
					headerLabel.setText(panelTitle[panelCounter]);
					gradePanelButtons[j].setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
					switchPanel(learnPracticePanel);

				}
			});

		}

		// Create button for Test
		JButton testButton = new JButton();
		createButton(testButton, gradePanelButtons[0].getWidth() + PRIMARY_RATIO, PRIMARY_RATIO,
				layeredPane.getWidth() - gradePanelButtons[0].getWidth() * 2 - PRIMARY_RATIO * 2,
				layeredPane.getHeight() - PRIMARY_RATIO * 2, GREEN_RGB, "", 1, 1, true);
		gradePanel.add(testButton);

		// Split button into image and text
		JLabel testImage = new JLabel();
		JLabel testText = new JLabel();
		testButton.setLayout(null);
		testButton.add(testImage);
		testButton.add(testText);
		testImage.setBounds(0, 0, testButton.getWidth(), testButton.getHeight() / 4 * 3);
		testText.setBounds(0, testButton.getHeight() / 4 * 3, testButton.getWidth(), testButton.getHeight() / 4);
		Image testAnimalImage = new ImageIcon(this.getClass().getResource(gradePanelImages[gradeSelection][4]))
				.getImage().getScaledInstance(
						(int) ((testImage.getHeight() - PRIMARY_RATIO) * gradePanelImageRatios[gradeSelection][4]),
						testImage.getHeight() - PRIMARY_RATIO, java.awt.Image.SCALE_SMOOTH);

		// Place the image and text on the label
		testImage.setIcon(new ImageIcon(testAnimalImage));
		testImage.setHorizontalAlignment(SwingConstants.CENTER);
		testText.setFont(new Font("Calibri", Font.PLAIN, (testButton.getHeight() / 10)));
		testText.setText("TEST");
		testText.setHorizontalAlignment(SwingConstants.CENTER);

		// Action Listener for Test button
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTestPanel(gradeSelection);
				panelList[++panelCounter] = testPanel;
				panelTitle[panelCounter] = "TIME FOR A TEST!";
				headerLabel.setText(panelTitle[panelCounter]);
				testButton.setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
				switchPanel(testPanel);
			}
		});

	}

	////////// LEARN / PRACTICE PANEL //////////
	public void createLearnPracticePanel(int gradeSelection, int categorySelection) {
		learnPracticePanel = new JPanel();
		layeredPane.add(learnPracticePanel, "name_180577775611500");
		learnPracticePanel.setBackground(new Color(0, 0, 0, 0));
		learnPracticePanel.setLayout(null);

		// Arrays for the Learn/Practice buttons, images, and ratios
		JButton[] learnPracticeButtons = new JButton[2];
		String[][] learnPracticeImages = { { "/learnChicken.png", "/practiceChicken.png" },
				{ "/learnPig.png", "/practicePig.png" }, { "/learnSheep.png", "/practiceSheep.png" },
				{ "/learnCow.png", "/practiceCow.png" }, { "/learnHorse.png", "/practiceHorse.png" } };
		double[][] learnPracticeImageRatios = { { 0.7, 0.61 }, { 1, 0.85 }, { 1, 0.97 }, { 1, 0.84 }, { 1, 0.82 } };
		String[] learnPracticePanelText = { "LEARN", "PRACTICE" };

		// Starting x coordinate for Learn button
		int learnPracticeButtonXCoordinate = PRIMARY_RATIO * 4;

		// Width for Learn and Practice buttons
		int buttonWidth = (layeredPane.getWidth() - (PRIMARY_RATIO * 16)) / 2;

		// Draw Learn and Practice buttons
		for (int i = 0; i < 2; i++) {
			learnPracticeButtons[i] = new JButton("");
			if (i % 2 != 0) {
				learnPracticeButtonXCoordinate = (PRIMARY_RATIO * 4) * 3
						+ (layeredPane.getWidth() - (PRIMARY_RATIO * 16)) / 2;
			}
			createButton(learnPracticeButtons[i], learnPracticeButtonXCoordinate, 0, buttonWidth,
					layeredPane.getHeight(), GREEN_RGB, "", 1, 1, true);
			learnPracticePanel.add(learnPracticeButtons[i]);

			// Split button into image and text
			JLabel buttonImage = new JLabel();
			JLabel buttonText = new JLabel();
			learnPracticeButtons[i].setLayout(null);
			learnPracticeButtons[i].add(buttonImage);
			learnPracticeButtons[i].add(buttonText);
			buttonImage.setBounds(0, 0, learnPracticeButtons[i].getWidth(),
					learnPracticeButtons[i].getHeight() / 4 * 3);
			buttonText.setBounds(0, learnPracticeButtons[i].getHeight() / 4 * 3, learnPracticeButtons[i].getWidth(),
					learnPracticeButtons[i].getHeight() / 4);
			Image learnPracticeImage = new ImageIcon(
					this.getClass().getResource(learnPracticeImages[gradeSelection][i])).getImage()
							.getScaledInstance(
									(int) ((buttonImage.getHeight() - PRIMARY_RATIO * 2)
											* learnPracticeImageRatios[gradeSelection][i]),
									(int) (buttonImage.getHeight() - PRIMARY_RATIO * 2), java.awt.Image.SCALE_SMOOTH);

			// Place the image and text on the label
			buttonImage.setIcon(new ImageIcon(learnPracticeImage));
			buttonImage.setHorizontalAlignment(SwingConstants.CENTER);
			buttonText.setFont(new Font("Calibri", Font.PLAIN, (learnPracticeButtons[i].getHeight() / 9)));
			buttonText.setText(learnPracticePanelText[i]);
			buttonText.setHorizontalAlignment(SwingConstants.CENTER);

		}

		// Action Listener for Learn button
		learnPracticeButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLearnPanel(gradeSelection, categorySelection);
				panelList[++panelCounter] = learnPanel;
				panelTitle[panelCounter] = "CHOOSE A VIDEO";
				headerLabel.setText(panelTitle[panelCounter]);
				learnPracticeButtons[0].setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
				switchPanel(learnPanel);
			}
		});

		// Action Listener for Practice button
		learnPracticeButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createActivityPanel(gradeSelection, categorySelection);
				panelList[++panelCounter] = activityPanel;
				panelTitle[panelCounter] = "CHOOSE AN ACTIVITY";
				headerLabel.setText(panelTitle[panelCounter]);
				learnPracticeButtons[1].setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
				switchPanel(activityPanel);
			}
		});

	}

	////////// TEST PANEL //////////
	public void createTestPanel(int gradeSelection) {
		testPanel = new JPanel();
		layeredPane.add(testPanel, "name_180577775611500");
		testPanel.setBackground(new Color(0, 0, 0, 0));
		testPanel.setLayout(null);

		// Set test panel width and height
		int panelWidth = layeredPane.getWidth() - PRIMARY_RATIO * 8;
		int panelHeight = layeredPane.getHeight() - PRIMARY_RATIO * 4;

		// Create main background label for test panel
		JLabel testPanelLabel = new JLabel();
		testPanelLabel.setBackground(new Color(DARK_GREEN_RGB[0], DARK_GREEN_RGB[1], DARK_GREEN_RGB[2]));
		testPanelLabel.setBounds(PRIMARY_RATIO * 4, 0, panelWidth, panelHeight);
		testPanel.add(testPanelLabel);
		testPanelLabel.setOpaque(true);
		testPanelLabel.setBorder(raisedbevel);

		// Create label for upper section of panel (question images)
		JLabel testPanelImageLabel = new JLabel();
		testPanelImageLabel.setVerticalAlignment(SwingConstants.CENTER);
		testPanelImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, headerPanel.getHeight() / 3));
		testPanelImageLabel.setForeground(Color.white);
		testPanelImageLabel.setBackground(new Color(DARK_GREEN_RGB[0], DARK_GREEN_RGB[1], DARK_GREEN_RGB[2]));
		testPanelImageLabel.setBounds(PRIMARY_RATIO, PRIMARY_RATIO, panelWidth - PRIMARY_RATIO * 2,
				panelHeight - PRIMARY_RATIO * 4);
		testPanelLabel.add(testPanelImageLabel);
		testPanelImageLabel.setOpaque(true);

		// Create label for lower section of panel (question text)
		JLabel testPanelTextLabel = new JLabel();
		testPanelTextLabel.setBounds(PRIMARY_RATIO, panelHeight - PRIMARY_RATIO * 3, panelWidth - PRIMARY_RATIO * 2,
				PRIMARY_RATIO * 2);
		testPanelLabel.add(testPanelTextLabel);
		testPanelTextLabel.setFont(new Font("Calibri", Font.PLAIN, testPanelTextLabel.getHeight() / 2));
		testPanelTextLabel.setForeground(Color.white);
		testPanelTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPanelTextLabel.setVerticalAlignment(SwingConstants.CENTER);

		// Create button for selecting next question. Disable button by default
		JButton nextQuestion = new JButton();
		createButton(nextQuestion, layeredPane.getWidth() - ((layeredPane.getWidth() - panelWidth) / 2), 0,
				(layeredPane.getWidth() - panelWidth) / 2, panelHeight, new int[] { 192, 192, 192 }, "", 1, 1, true);
		testPanel.add(nextQuestion);
		nextQuestion.setOpaque(true);
		nextQuestion.setEnabled(false);
		nextQuestion.setVerticalAlignment(SwingConstants.CENTER);
		Image nextQuestionImage = new ImageIcon(this.getClass().getResource("/next.png")).getImage().getScaledInstance(
				nextQuestion.getWidth() - PRIMARY_RATIO, (int) ((nextQuestion.getWidth() - PRIMARY_RATIO) / 1.06),
				java.awt.Image.SCALE_SMOOTH);
		nextQuestion.setIcon(new ImageIcon(nextQuestionImage));

		// When button is clicked, reload test panel
		nextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createTestPanel(gradeSelection);
				switchPanel(testPanel);
			}
		});

		// Run test questions if counter is less than 10
		if (testCounter < 10) {

			// Choose a random question based on user's selected grade
			Random rand = new Random();
			testCounter++;
			chooseQuestion(gradeSelection, rand.nextInt(4), rand.nextInt(2), testPanelImageLabel, testPanelTextLabel,
					nextQuestion, testPanelLabel, testPanel);

			// If test is over, display result
		} else {
			testPanelImageLabel.setText("Your score: " + testScore + " / 10");
			login.getTestResults(username, gradeSelection, testScore);

			// If user scores successful score, unlocked grade's badge
			if (testScore > 7) {
				login.unlockBadge(username, gradeSelection);
			}
		}

	}

	////////// LEARN PANEL //////////
	public void createLearnPanel(int gradeSelection, int categorySelection) {
		learnPanel = new JPanel(new GridLayout());
		layeredPane.add(learnPanel, "name_180577775611500");
		learnPanel.setBackground(new Color(0, 0, 0, 0));
		learnPanel.setLayout(null);

		// Set practice panel width and height
		int panelWidth = layeredPane.getWidth() - PRIMARY_RATIO * 8;
		int panelHeight = layeredPane.getHeight() - PRIMARY_RATIO * 4;

		// Create main background label for practice panel
		JLabel learnLabel = new JLabel();
		learnLabel.setBackground(new Color(DARK_GREEN_RGB[0], DARK_GREEN_RGB[1], DARK_GREEN_RGB[2]));
		learnLabel.setBounds(PRIMARY_RATIO * 4, 0, panelWidth, panelHeight);
		learnPanel.add(learnLabel);
		learnLabel.setOpaque(true);
		learnLabel.setBorder(raisedbevel);

		String[][][] hyperlinks = { { // Grade K Learning Links
				{ "https://www.youtube.com/watch?v=mjlsSYLLOSE&t=53s", "https://www.youtube.com/watch?v=rqiu_xcvSk4",
						"https://www.youtube.com/watch?v=igcoDFokKzU", "https://www.youtube.com/watch?v=NHI0ePgwlgU" },
				{ "https://www.youtube.com/watch?v=x6D51-pz2A4", "https://www.youtube.com/watch?v=2X6FSDrWDEo",
						"https://www.youtube.com/watch?v=UsbZ-1VmNvw", "https://www.youtube.com/watch?v=8IxyrEO4i14" },
				{ "https://www.youtube.com/watch?v=ka9zbPcqXBI", "https://www.youtube.com/watch?v=E34PAOGYRNk&t=45s",
						"https://www.youtube.com/watch?v=icR_T7v2xZo" },
				{ "https://www.youtube.com/watch?v=1VBW6rdvRks", "https://www.youtube.com/watch?v=uaUnTxoF4hU",
						"https://www.youtube.com/watch?v=teif6M9FjHE" } },

				{ // Grade 1 Learning Links
						{ "https://www.youtube.com/watch?v=wiGEEJLLKd8", "https://www.youtube.com/watch?v=bGetqbqDVaA",
								"https://www.youtube.com/watch?v=-gmEe0-_ex8",
								"https://www.youtube.com/watch?v=3iQqmmG8wQQ" },
						{ "https://www.youtube.com/watch?v=xdR7s8mwyp8", "https://www.youtube.com/watch?v=9p_Ca_Yb0zQ",
								"https://www.youtube.com/watch?v=pnXJGNo08v0",
								"https://www.youtube.com/watch?v=djwclHApLFo" },
						{ "https://www.youtube.com/watch?v=Qn87cKHa7v4",
								"https://www.youtube.com/watch?v=Qn87cKHa7v4&t=111s" },
						{ "https://www.youtube.com/watch?v=uaUnTxoF4hU", "https://www.youtube.com/watch?v=Ux_kLd7qAcY" }

				}, { // Grade 2 Learning Links
						{ "https://www.youtube.com/watch?v=Q9sLfMrH8_w", "https://www.youtube.com/watch?v=VPsYRPdlIpU",
								"https://www.youtube.com/watch?v=qKxQ33KcRWQ",
								"https://www.youtube.com/watch?v=ySkjVZ0ym7k" },
						{ "https://www.youtube.com/watch?v=bZY8WNMRcQ8", "https://www.youtube.com/watch?v=XvrPpRMsQm4",
								"https://www.youtube.com/watch?v=TnSj6QCnX1o" },
						{ "https://www.youtube.com/watch?v=GjNsvSHYK98",
								"https://www.youtube.com/watch?v=H9w8WRr8034" },
						{ "https://www.youtube.com/watch?v=w27TpLQexbc",
								"https://www.youtube.com/watch?v=guNdJ5MtX1A" } },
				{ // Grade 3 Learning Links
						{ "https://www.youtube.com/watch?v=IwW0GJWKH98", "https://www.youtube.com/watch?v=LD4zp8ruvaI",
								"https://www.youtube.com/watch?v=EgjCLhoI9Mk",
								"https://www.youtube.com/watch?v=CFDCG1b4ahk" },
						{ "https://www.youtube.com/watch?v=p33BYf1NDAE", "https://www.youtube.com/watch?v=KlfxIbO-KJs",
								"https://www.youtube.com/watch?v=SZaXtOHNh6s" },
						{ "https://www.youtube.com/watch?v=Iu6YaZoh4ec",
								"https://www.youtube.com/watch?v=tuBLuIW1U70" },
						{ "https://www.youtube.com/watch?v=kQETZgv04BI", "https://www.youtube.com/watch?v=Sj6PH7kKX2U",
								"https://www.youtube.com/watch?v=DUGkQMLowXA" } },
				{ // Grade 4 Learning Links
						{ "https://www.youtube.com/watch?v=DkM1krQfESM",
								"https://www.youtube.com/watch?v=IwW0GJWKH98&t=458s",
								"https://www.youtube.com/watch?v=a1zBdLQgNZ4",
								"https://www.youtube.com/watch?v=gjqxhtjyfC4" },
						{ "https://www.youtube.com/watch?v=p33BYf1NDAE&t=239s",
								"https://www.youtube.com/watch?v=DnFrOetuUKg",
								"https://www.youtube.com/watch?v=yT1WuyxTCmo&t=23s" },
						{ "https://www.youtube.com/watch?v=VejB7iT0Wzs",
								"https://www.youtube.com/watch?v=VzW2sdCe228" },
						{ "https://www.youtube.com/watch?v=lBCIGY3dWXQ",
								"https://www.youtube.com/watch?v=10dTx1Zy_4w" } } };

		String[][][] videoName = { { // Grade K Learning Links Title
				{ "Addition", "Subtraction", "Add & Subtract with Dinosaurs", "Addition Song" },
				{ "Count 1 to 10", "Missing Number", "Farm Animal Counting", "Interactive Counting Song" },
				{ "Compare Song", "Compare Numbers", "Compare Quantities" },
				{ "Shapes", "Shapes Song", "Shapes - Sing, Dance & Learn" } },

				{ // Grade 1 Learning Links Title
						{ "Count 1-20", "Count 1-100", "Count By 10's", "Even and Odd Numbers" },
						{ "Time", "Half Past Time", "The Money Song", "Count Money & Make Change" },
						{ "Compare", "Compare Numbers" }, { "Shapes Song", "Shapes Movie" } },

				{ // Grade 2 Learning Links Title
						{ "2 Digit Addition", "Addition Using Regrouping", "2 Digit Subtraction", "Subtraction" },
						{ "Time", "Quarter Past Times", "Money" }, { "Measure Song", "Measure With a Ruler" },
						{ "3D Shapes", "3D Shapes Song" } },

				{ // Grade 3 Learning Links Title
						{ "Arithmetic Introduction", "Multiplication", "Multiplication MashUp", "Add In groups" },
						{ "Fractions", "Equivalent Fractions", "Fractions on a Number Line Song" },
						{ "Measurements", "How To Measure" }, { "Angles", "Geometry Introduction", "Polygons" } },

				{ // Grade 4 Learning Links Title
						{ "HipHop Math", "Arithmetic", "Multiplication", "Division" },
						{ "Fractions", "Fractions Song", "Basic Fractions" }, { "Measurement Concepts", "Measurement" },
						{ "Geometry", "Shapes" } } };

		int linkButtonHeight = learnLabel.getHeight() / 5;
		int linkButtonYCoordinate = linkButtonHeight / 5;

		for (int i = 0; i < hyperlinks[gradeSelection][categorySelection].length; i++) {
			JButton linkButton = new JButton();
			createButton(linkButton, linkButtonHeight / 5, linkButtonYCoordinate,
					learnLabel.getWidth() - linkButtonHeight / 5 * 2, linkButtonHeight, GREEN_RGB, "", 1, 1, true);
			learnLabel.add(linkButton);
			linkButton.setText("\u2022 " + videoName[gradeSelection][categorySelection][i]);
			linkButton.setHorizontalAlignment(SwingConstants.LEFT);
			linkButton.setFont(new Font("Calibri", Font.PLAIN, linkButtonHeight / 2));

			linkButtonYCoordinate += linkButtonHeight + linkButtonHeight / 5;

			int j = i;
			try {
				linkButton.addActionListener(new ActionListener() {

					URI uri = new URI(hyperlinks[gradeSelection][categorySelection][j]);

					public void actionPerformed(ActionEvent e) {

						if (Desktop.isDesktopSupported()) {
							Desktop desktop = Desktop.getDesktop();
							try {
								desktop.browse(uri);
							} catch (Exception ex) {
							}
						}

					}
				});
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}

		learnPanel.setVisible(true);
	}

	////////// ACTIVITY PANEL //////////
	public void createActivityPanel(int gradeSelection, int categorySelection) {
		activityPanel = new JPanel();
		layeredPane.add(activityPanel, "name_180577775611500");
		activityPanel.setBackground(new Color(0, 0, 0, 0));
		activityPanel.setLayout(null);

		String[][][] activityImages = {
				{ { "/plusSign.png", "/minusSign.png" }, { "/howMany.png", "/nextSequence.png" }, { "/compare.png" },
						{ "/shapes.png" } },
				{ { "/plusSign.png", "/minusSign.png" }, { "/clock100.png", "/quarter.png" }, { "/compare.png" },
						{ "/shapes.png" } },
				{ { "/plusSign.png", "/minusSign.png" }, { "/calendar.png", "/quarter.png" }, { "/8 inches.png" },
						{ "/shapes3d.png", "/angles.png" } },
				{ { "/multiplicationSign.png", "/divisionSign.png" }, { "/pizza23.png" }, { "/8 inches.png" },
						{ "/shapes3d.png", "/angles.png" } },
				{ { "/multiplicationSign.png", "/divisionSign.png" }, { "/fractionEquivalences.png" },
						{ "/tapeMeasure.png" }, { "/shapes3d.png", "/angles.png" } } };
		double[][][] activityImageRatios = { { { 1.0, 2.0 }, { 0.93, 3.92 }, { 0.62 }, { 0.93 } },
				{ { 1.0, 2.0 }, { 1.0, 1.0 }, { 0.62 }, { 0.93 } },
				{ { 1.0, 2.0 }, { 1.18, 1.0 }, { 1.86 }, { 0.93, 1.0 } },
				{ { 1.0, 1.0 }, { 1.0 }, { 1.86 }, { 0.93, 1.0 } },
				{ { 1.0, 1.0 }, { 1.48 }, { 2.29 }, { 0.93, 1.0 } } };
		String[][][] activityText = {
				{ { "ADD", "SUBTRACT" }, { "HOW MANY", "WHAT'S NEXT" }, { "COMPARE" }, { "NAME THE SHAPE" } },
				{ { "ADD", "SUBTRACT" }, { "WHAT TIME", "HOW MUCH MONEY" }, { "COMPARE" },
						{ "NAME THE SHAPE", "NAME THE ANGLE" } },
				{ { "ADD", "SUBTRACT" }, { "HOW MUCH TIME", "HOW MUCH MONEY" },
						{ "<html><center>WHAT'S THE<br>LENGTH" }, { "NAME THE SHAPE", "NAME THE ANGLE" } },
				{ { "MULTIPLY", "DIVIDE" }, { "PIZZA FRACTIONS" }, { "<html><center>WHAT'S THE<br>LENGTH" },
						{ "NAME THE SHAPE", "NAME THE ANGLE" } },
				{ { "MULTIPLY", "DIVIDE" }, { "FRACTIONS" }, { "HOW MANY UNITS" },
						{ "NAME THE SHAPE", "NAME THE ANGLE" } } };

		JButton[] activityButtons = new JButton[activityImages[gradeSelection][categorySelection].length];

		// Width for activity button
		int buttonWidth = (layeredPane.getWidth() - (PRIMARY_RATIO * 16)) / 2;

		// Starting x coordinate for activity buttons
		int activityButtonXCoordinate;
		if (activityButtons.length == 1) {
			activityButtonXCoordinate = layeredPane.getWidth() / 2 - buttonWidth / 2;
		} else {
			activityButtonXCoordinate = PRIMARY_RATIO * 4;
		}

		// Draw activity buttons
		for (int i = 0; i < activityButtons.length; i++) {
			activityButtons[i] = new JButton("");
			if (i % 2 != 0) {
				activityButtonXCoordinate = (PRIMARY_RATIO * 4) * 3
						+ (layeredPane.getWidth() - (PRIMARY_RATIO * 16)) / 2;
			}
			createButton(activityButtons[i], activityButtonXCoordinate, 0, buttonWidth, layeredPane.getHeight(),
					GREEN_RGB, "", 1, 1, true);
			activityPanel.add(activityButtons[i]);

			// Split button into image and text
			JLabel buttonImage = new JLabel();
			JLabel buttonText = new JLabel();
			activityButtons[i].setLayout(null);
			activityButtons[i].add(buttonImage);
			activityButtons[i].add(buttonText);
			buttonImage.setBounds(0, 0, activityButtons[i].getWidth(), activityButtons[i].getHeight() / 4 * 3);
			buttonText.setBounds(0, activityButtons[i].getHeight() / 4 * 3, activityButtons[i].getWidth(),
					activityButtons[i].getHeight() / 4);
			Image activityImage;
			if (activityImageRatios[gradeSelection][categorySelection][i] < 1) {
				activityImage = new ImageIcon(
						this.getClass().getResource(activityImages[gradeSelection][categorySelection][i])).getImage()
								.getScaledInstance(
										(int) ((buttonImage.getHeight() - PRIMARY_RATIO * 2)
												* activityImageRatios[gradeSelection][categorySelection][i]),
										(int) (buttonImage.getHeight() - PRIMARY_RATIO * 2),
										java.awt.Image.SCALE_SMOOTH);
			} else {
				activityImage = new ImageIcon(
						this.getClass().getResource(activityImages[gradeSelection][categorySelection][i])).getImage()
								.getScaledInstance((int) (buttonImage.getHeight() - PRIMARY_RATIO * 2),
										(int) ((buttonImage.getHeight() - PRIMARY_RATIO * 2)
												/ activityImageRatios[gradeSelection][categorySelection][i]),
										java.awt.Image.SCALE_SMOOTH);
			}

			// Place the image and text on the label
			buttonImage.setIcon(new ImageIcon(activityImage));
			buttonImage.setHorizontalAlignment(SwingConstants.CENTER);
			buttonText.setFont(new Font("Calibri", Font.PLAIN, (activityButtons[i].getHeight() / 11)));
			buttonText.setText(activityText[gradeSelection][categorySelection][i]);
			buttonText.setHorizontalAlignment(SwingConstants.CENTER);

			int j = i;

			// Action listener for activity buttons
			activityButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int activitySelection = j;

					createPracticePanel(gradeSelection, categorySelection, activitySelection);
					panelList[++panelCounter] = practicePanel;
					panelTitle[panelCounter] = "TIME TO PRACTICE!";
					headerLabel.setText(panelTitle[panelCounter]);
					activityButtons[j].setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));
					switchPanel(practicePanel);
				}
			});
		}

	}

	////////// PRACTICE PANEL //////////
	public void createPracticePanel(int gradeSelection, int categorySelection, int activitySelection) {
		practicePanel = new JPanel();
		layeredPane.add(practicePanel, "name_180577775611500");
		practicePanel.setBackground(new Color(0, 0, 0, 0));
		practicePanel.setLayout(null);

		// Set practice panel width and height
		int panelWidth = layeredPane.getWidth() - PRIMARY_RATIO * 8;
		int panelHeight = layeredPane.getHeight() - PRIMARY_RATIO * 4;

		// Create main background label for practice panel
		JLabel practicePanelLabel = new JLabel();
		practicePanelLabel.setBackground(new Color(DARK_GREEN_RGB[0], DARK_GREEN_RGB[1], DARK_GREEN_RGB[2]));
		practicePanelLabel.setBounds(PRIMARY_RATIO * 4, 0, panelWidth, panelHeight);
		practicePanel.add(practicePanelLabel);
		practicePanelLabel.setOpaque(true);
		practicePanelLabel.setBorder(raisedbevel);

		// Create label for upper section of panel (question images)
		JLabel practicePanelImageLabel = new JLabel();
		practicePanelImageLabel.setVerticalAlignment(SwingConstants.CENTER);
		practicePanelImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, headerPanel.getHeight() / 3));
		practicePanelImageLabel.setForeground(Color.white);
		practicePanelImageLabel.setBackground(new Color(DARK_GREEN_RGB[0], DARK_GREEN_RGB[1], DARK_GREEN_RGB[2]));
		practicePanelImageLabel.setBounds(PRIMARY_RATIO, PRIMARY_RATIO, panelWidth - PRIMARY_RATIO * 2,
				panelHeight - PRIMARY_RATIO * 4);
		practicePanelLabel.add(practicePanelImageLabel);
		practicePanelImageLabel.setOpaque(true);

		// Create label for lower section of panel (question text)
		JLabel practicePanelTextLabel = new JLabel();
		practicePanelTextLabel.setBounds(PRIMARY_RATIO, panelHeight - PRIMARY_RATIO * 3, panelWidth - PRIMARY_RATIO * 2,
				PRIMARY_RATIO * 2);
		practicePanelLabel.add(practicePanelTextLabel);
		practicePanelTextLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelTextLabel.getHeight() / 2));
		practicePanelTextLabel.setForeground(Color.white);
		practicePanelTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		practicePanelTextLabel.setVerticalAlignment(SwingConstants.CENTER);

		// Create button for selecting next question. Disable button by default
		JButton nextQuestion = new JButton();
		createButton(nextQuestion, layeredPane.getWidth() - ((layeredPane.getWidth() - panelWidth) / 2), 0,
				(layeredPane.getWidth() - panelWidth) / 2, panelHeight, new int[] { 192, 192, 192 }, "", 1, 1, true);
		practicePanel.add(nextQuestion);
		nextQuestion.setOpaque(true);
		nextQuestion.setEnabled(false);
		nextQuestion.setVerticalAlignment(SwingConstants.CENTER);
		Image nextQuestionImage = new ImageIcon(this.getClass().getResource("/next.png")).getImage().getScaledInstance(
				nextQuestion.getWidth() - PRIMARY_RATIO, (int) ((nextQuestion.getWidth() - PRIMARY_RATIO) / 1.06),
				java.awt.Image.SCALE_SMOOTH);
		nextQuestion.setIcon(new ImageIcon(nextQuestionImage));

		// When button is clicked, reload practice panel
		nextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createPracticePanel(gradeSelection, categorySelection, activitySelection);
				switchPanel(practicePanel);
			}
		});

		// Choose a random question based on user's selected grade and category
		chooseQuestion(gradeSelection, categorySelection, activitySelection, practicePanelImageLabel,
				practicePanelTextLabel, nextQuestion, practicePanelLabel, practicePanel);

	}

	////////// CREATE BUTTON //////////
	public void createButton(JButton button, int x, int y, int width, int height, int[] rgb, String image,
			int imageWidth, int imageHeight, boolean bevel) {
		button.setBounds(x, y, width, height);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
		if (image != null) {
			Image buttonImage = new ImageIcon(this.getClass().getResource(image)).getImage()
					.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(buttonImage));
		}
		if (bevel) {
			button.setBorder(raisedbevel);
			button.setBorderPainted(true);
		}

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
			}
		});
	}

	////////// CHOOSE QUESTION //////////
	public void chooseQuestion(int gradeSelection, int categorySelection, int activitySelection,
			JLabel practicePanelImageLabel, JLabel practicePanelTextLabel, JButton nextQuestion,
			JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

		// Set default values for answers, correctAnswerIndex, and null question object
		String[] answers;
		int correctAnswerIndex;
		Question question = null;

		// Switch for grade level (0-4)
		switch (gradeSelection) {

		// Grade K:
		case 0:

			// Switch for category selection
			switch (categorySelection) {

			// Grade K: Addition/Subtraction
			case 0:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade K: Addition/Subtraction: Addition
				case 0:
					createArithmetic(gradeSelection, 0, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade K: Addition/Subtraction: Subtraction
				case 1:
					createArithmetic(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade K: Count
			case 1:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade K: Count: How many
				case 0:
					createQuestionHowMany(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade K: Count: Next sequence
				case 1:
					createQuestionNextSequence(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade K: Compare
			case 2:

				// Grade K: Compare: Compare
				createQuestionCompare(gradeSelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade K: Shapes
			case 3:

				// Grade K: Shapes: What Shape
				QuestionSingleImage shapeQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
				question = shapeQuestion;
				break;

			}
			break;

		// Grade 1:
		case 1:

			// Switch for category selection
			switch (categorySelection) {

			// Grade 1: Addition/Subtraction
			case 0:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 1: Addition/Subtraction: Addition
				case 0:
					createArithmetic(gradeSelection, 0, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 1: Addition/Subtraction: Subtraction
				case 1:
					createArithmetic(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 1: Time and Money
			case 1:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 1: Time and Money: Time
				case 0:
					createQuestionWhatTime(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 1: Time and Money: Money
				case 1:
					createQuestionCoinCount(gradeSelection, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 1: Compare
			case 2:
				// Grade 1: Compare: Compare
				createQuestionCompare(gradeSelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 1: Shapes
			case 3:

				// Grade 1: Shapes: What Shape
				QuestionSingleImage shapeQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
				question = shapeQuestion;
				break;
			}
			break;

		// Grade 2:
		case 2:

			// Switch for category selection
			switch (categorySelection) {

			// Grade 2: Addition/Subtraction
			case 0:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 2: Addition/Subtraction: Addition
				case 0:
					createArithmetic(gradeSelection, 0, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 2: Addition/Subtraction: Subtraction
				case 1:
					createArithmetic(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 2: Time and Money
			case 1:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 2: Time and Money: Time
				case 0:
					QuestionHowManyUnits shapeQuestion = new QuestionHowManyUnits(gradeSelection);
					question = shapeQuestion;
					break;

				// Grade 2: Time and Money: Money
				case 1:
					createQuestionCoinCount(gradeSelection, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 2: Measure
			case 2:
				createQuestionMeasure(gradeSelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 2: Geometry
			case 3:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 2: Geometry: What Shape
				case 0:

					QuestionSingleImage shapeQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
					question = shapeQuestion;
					break;

				// Grade 2: Geometry: Angles
				case 1:
					createQuestionGeoAngles(gradeSelection, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			}
			break;

		// Grade 3:
		case 3:
			// Switch for category selection
			switch (categorySelection) {

			// Grade 3: Geometry
			case 0:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 3: Multiplication/Division: Multiplication
				case 0:
					createArithmetic(gradeSelection, 2, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 3: Multiplication/Division: Division
				case 1:
					createArithmetic(gradeSelection, 3, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 3: Fractions
			case 1:

				QuestionSingleImage fractionQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
				question = fractionQuestion;
				break;

			// Grade 3: Measure
			case 2:
				createQuestionMeasure(gradeSelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 3: Geometry
			case 3:
				// Switch for activity selection
				switch (activitySelection) {

				// Grade 3: Geometry: 3D Shapes
				case 0:

					QuestionSingleImage shapeQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
					question = shapeQuestion;
					break;

				// Grade 3: Geometry: Angles
				case 1:
					createQuestionGeoAngles(gradeSelection, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;
			}
			break;

		// Grade 4:
		case 4:
			// Switch for category selection
			switch (categorySelection) {

			// Grade 4: Multiplication/Division
			case 0:

				// Switch for activity selection
				switch (activitySelection) {

				// Grade 4: Multiplication/Division: Multiplication
				case 0:
					createArithmetic(gradeSelection, 2, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 4: Multiplication/Division: Division
				case 1:
					createArithmetic(gradeSelection, 3, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 4: Fraction Equivalences
			case 1:
				createQuestionFractionEquivalences(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 4: Measure
			case 2:
				QuestionHowManyUnits measureQuestion = new QuestionHowManyUnits(gradeSelection);
				question = measureQuestion;
				break;

			// Grade 4: Geometry
			case 3:
				// Switch for activity selection
				switch (activitySelection) {

				// Grade 3: Geometry: 3D Shapes
				case 0:

					QuestionSingleImage shapeQuestion = new QuestionSingleImage(gradeSelection, categorySelection);
					question = shapeQuestion;
					break;

				// Grade 3: Geometry: Angles
				case 1:
					createQuestionGeoAngles(gradeSelection, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;
			}
			break;
		}

		question.generateQuestion();
		answers = question.getAnswers();
		correctAnswerIndex = question.getCorrectAnswerIndex();

		// If question contains an image, display it. Else, display text
		if (question.getImage() != null) {
			int imageHeight = practicePanelImageLabel.getHeight();
			Image questionImage = new ImageIcon(this.getClass().getResource(question.getImage())).getImage()
					.getScaledInstance((int) (imageHeight * question.getImageRatio()), imageHeight,
							java.awt.Image.SCALE_SMOOTH);
			practicePanelImageLabel.setIcon(new ImageIcon(questionImage));
		} else {
			practicePanelImageLabel.setText(question.getQuestionText());
			practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 4));
		}

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE K-4: ADD, SUBTRACTION, MULTIPLICATION, DIVISION
	////////// //////////
	public void createArithmetic(int gradeSelection, int operation, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel,
			JPanel practiceOrTestPanel) {

		// Create new arithmetic object
		Arithmetic arithmetic = new Arithmetic(gradeSelection, operation);
		String[] answers = arithmetic.getAnswers();
		String[] questions = arithmetic.getQuestions();
		int correctAnswerIndex = arithmetic.getCorrectAnswerIndex();

		practicePanelImageLabel.setVisible(true);

		String question = questions[0] + "  " + questions[1] + "  " + questions[2];
		practicePanelImageLabel.setText(question);
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 2));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE K: CHOOSE HOW MANY ANIMALS THERE ARE //////////
	public void createQuestionHowMany(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

		// Create new HowMany object
		HowMany howMany = new HowMany(0);
		practicePanelTextLabel.setText(howMany.getQuestionText());
		String[] answers = howMany.getAnswers();
		int correctAnswer = howMany.getCorrectAnswer();
		int correctAnswerIndex = howMany.getCorrectAnswerIndex();
		String img = howMany.getImage();
		double imageRatio = howMany.getImageRatio();
		int imageWidth = 0;
		int imageHeight = 0;

		// Create one sub panel for each animal image
		JLabel[] practicePanelSubImage = new JLabel[correctAnswer];

		// Set image sizes based on panel. If the total number of images and width goes
		// beyond panel boundaries, resize all images to auto-fit
		double imageSize = practicePanelImageLabel.getHeight();
		int xImageCoordinate;
		int yImageCoordinate = 0;
		if (imageSize * correctAnswer > practicePanelImageLabel.getWidth()) {
			imageSize = (practicePanelImageLabel.getWidth() / (imageSize * correctAnswer) * imageSize);
			xImageCoordinate = 0;
			yImageCoordinate = (int) (practicePanelImageLabel.getHeight() - imageSize) / 2;
		} else {
			xImageCoordinate = (int) ((practicePanelImageLabel.getWidth() - imageSize * correctAnswer)
					/ (correctAnswer + 1));
		}

		// Create sub panels and draw all animal images
		for (int i = 0; i < correctAnswer; i++) {
			practicePanelSubImage[i] = new JLabel();
			practicePanelSubImage[i].setOpaque(false);
			practicePanelSubImage[i].setBounds(xImageCoordinate, yImageCoordinate, (int) imageSize, (int) imageSize);
			xImageCoordinate += (int) ((practicePanelImageLabel.getWidth() - imageSize * correctAnswer)
					/ (correctAnswer + 1)) + imageSize;
			practicePanelImageLabel.add(practicePanelSubImage[i]);
			practicePanelSubImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			practicePanelSubImage[i].setVerticalAlignment(SwingConstants.CENTER);

			if (imageRatio > 1.0) {
				imageWidth = practicePanelSubImage[i].getHeight();
				imageHeight = (int) (practicePanelSubImage[i].getHeight() / imageRatio);
			} else {
				imageWidth = (int) (practicePanelSubImage[i].getHeight() * imageRatio);
				imageHeight = practicePanelSubImage[i].getHeight();
			}

			Image questionImage = new ImageIcon(this.getClass().getResource(img)).getImage()
					.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH);
			practicePanelSubImage[i].setIcon(new ImageIcon(questionImage));
		}

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE K: CHOOSE NEXT NUMBER IN THE SEQUENCE //////////
	public void createQuestionNextSequence(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

		// Create new NextSequence object
		NextSequence nextSequence = new NextSequence();
		practicePanelTextLabel.setText(nextSequence.getQuestionText());
		String[] answers = nextSequence.getAnswers();
		int correctAnswerIndex = nextSequence.getCorrectAnswerIndex();
		practicePanelImageLabel.setText(nextSequence.getSequence());
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE K-1: Compare two numbers //////////
	public void createQuestionCompare(int gradeSelection, JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

		// Create new Compare object
		Compare compare = new Compare(gradeSelection);
		practicePanelTextLabel.setText(compare.getQuestionText());
		practicePanelImageLabel.setText(compare.getQuestion());
		String[] answers = compare.getAnswers();
		int correctAnswerIndex = compare.getCorrectAnswerIndex();
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);

	}

	////////// QUESTION: GRADE 1: WHAT TIME IS IT //////////
	public void createQuestionWhatTime(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {
		WhatTime whatTime = new WhatTime();
		practicePanelTextLabel.setText(whatTime.getQuestionText());
		String[] answers = whatTime.getAnswers();
		int correctAnswerIndex = whatTime.getCorrectAnswerIndex();
		String img = whatTime.getImage();
		Image questionImage = new ImageIcon(this.getClass().getResource(img)).getImage().getScaledInstance(
				practicePanelImageLabel.getHeight(), practicePanelImageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: CoinCount
	public void createQuestionCoinCount(int gradeSelection, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel,
			JPanel practiceOrTestPanel) {

		// Create new CoinCount
		CoinCount coincount = new CoinCount(1);
		practicePanelTextLabel.setText(coincount.getQuestionText());
		String[] answers = coincount.getAnswers();
		int correctAnswer = coincount.getCorrectAnswer();
		int correctAnswerIndex = coincount.getCorrectAnswerIndex();
		int imageWidth = 0;
		int imageHeight = 0;

		// Create one sub panel for each coin
		JLabel[] practicePanelSubImage = new JLabel[correctAnswer];

		// Set image sizes based on panel. If the total number of images and width goes
		// beyond panel boundaries, resize all images to auto-fit
		double imageSize = practicePanelImageLabel.getHeight();
		int xImageCoordinate;
		int yImageCoordinate = 0;
		if (imageSize * correctAnswer > practicePanelImageLabel.getWidth()) {
			imageSize = (practicePanelImageLabel.getWidth() / (imageSize * correctAnswer) * imageSize);
			xImageCoordinate = 0;
			yImageCoordinate = (int) (practicePanelImageLabel.getHeight() - imageSize) / 2;
		} else {
			xImageCoordinate = (int) ((practicePanelImageLabel.getWidth() - imageSize * correctAnswer)
					/ (correctAnswer + 1));
		}

		// Create sub panels and draw all coin images
		for (int i = 0; i < coincount.getImageNames().length; i++) {
			imageWidth = coincount.getImageWidths()[i];
			imageHeight = coincount.getImageHeights()[i];
			practicePanelSubImage[i] = new JLabel();
			practicePanelSubImage[i].setOpaque(false);
			practicePanelSubImage[i].setBounds(xImageCoordinate, yImageCoordinate, coincount.getImageWidths()[i],
					coincount.getImageWidths()[i]);
			xImageCoordinate += (int) ((practicePanelImageLabel.getWidth()
					- coincount.getImageWidths()[i] * coincount.getImageNames().length)
					/ (coincount.getImageNames().length + 1)) + coincount.getImageWidths()[i];
			practicePanelImageLabel.add(practicePanelSubImage[i]);
			practicePanelSubImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			practicePanelSubImage[i].setVerticalAlignment(SwingConstants.CENTER);
			Image questionImage = new ImageIcon(this.getClass().getResource(coincount.getImageNames()[i] + ".png"))
					.getImage().getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH);
			practicePanelSubImage[i].setIcon(new ImageIcon(questionImage));
		}

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE 2-3: HOW LONG IS IT //////////
	public void createQuestionMeasure(int gradeSelection, JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {
		Measure measure = new Measure(gradeSelection);
		practicePanelTextLabel.setText(measure.getQuestionText());
		String[] answers = measure.getAnswers();
		int correctAnswerIndex = measure.getCorrectAnswerIndex();
		String img = measure.getImage();
		Image questionImage = new ImageIcon(this.getClass().getResource(img)).getImage().getScaledInstance(
				(int) (practicePanelImageLabel.getHeight() * measure.getImageRatio() * 2),
				practicePanelImageLabel.getHeight() * 2, java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// QUESTION: GRADE 2-4: Name the 3D shape //////////
	public void createQuestionGeoShapes(int gradeSelection, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel,
			JPanel practiceOrTestPanel) {

		// Create new Compare object
		GeoShapes geoShapes = new GeoShapes(gradeSelection);
		practicePanelTextLabel.setText(geoShapes.getQuestionText());
		String[] answers = geoShapes.getAnswers();
		int correctAnswerIndex = geoShapes.getCorrectAnswerIndex();
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));
		int imageHeight = practicePanelImageLabel.getHeight();

		Image questionImage = new ImageIcon(this.getClass().getResource(geoShapes.getImage())).getImage()
				.getScaledInstance((int) (imageHeight * geoShapes.getImageRatio()), imageHeight,
						java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 3);

	}

	////////// QUESTION: GRADE 2-4: Name the angle //////////
	public void createQuestionGeoAngles(int gradeSelection, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel,
			JPanel practiceOrTestPanel) {

		// Create new Compare object
		GeoAngles geoAngles = new GeoAngles(gradeSelection);
		practicePanelTextLabel.setText(geoAngles.getQuestionText());
		String[] answers = geoAngles.getAnswers();
		int correctAnswerIndex = geoAngles.getCorrectAnswerIndex();
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));
		int imageHeight = practicePanelImageLabel.getHeight();

		Image questionImage = new ImageIcon(this.getClass().getResource(geoAngles.getImage())).getImage()
				.getScaledInstance((int) (imageHeight * geoAngles.getImageRatio()), imageHeight,
						java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 3);

	}

	////////// QUESTION: GRADE 4: FRACTIONS //////////
	public void createQuestionFractionEquivalences(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {
		FractionEquivalences fractionEquivalences = new FractionEquivalences();
		practicePanelTextLabel.setText(fractionEquivalences.getQuestionText());
		String[] answers = fractionEquivalences.getAnswers();
		int correctAnswerIndex = fractionEquivalences.getCorrectAnswerIndex();
		practicePanelImageLabel.setText(fractionEquivalences.getImage());
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 4));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel, 2);
	}

	////////// CREATE ANSWER BUTTONS //////////
	public void createAnswerButtons(String[] answers, JButton nextQuestion, int correctAnswerIndex,
			JLabel practicePanelLabel, JPanel practiceOrTestPanel, int textSize) {

		// Create answer selection Buttons
		JButton[] answerSelectionButtons = new JButton[3];
		int answerSelectionButtonXCoordinate = PRIMARY_RATIO * 4;

		// Run loop 3 times (once per button). Create and draw buttons
		for (int i = 0; i < 3; i++) {
			answerSelectionButtons[i] = new JButton();
			answerSelectionButtons[i].setText(String.valueOf(answers[i]));
			answerSelectionButtons[i].setBackground(new Color(0, 75, 200));
			answerSelectionButtons[i].setBounds(answerSelectionButtonXCoordinate,
					practicePanelLabel.getHeight() + PRIMARY_RATIO, layeredPane.getWidth() / 4,
					(layeredPane.getHeight() - PRIMARY_RATIO) - practicePanelLabel.getHeight());
			answerSelectionButtonXCoordinate += layeredPane.getWidth() - PRIMARY_RATIO * 4
					- (layeredPane.getWidth() / 5 * 3);
			answerSelectionButtons[i]
					.setFont(new Font("Calibri", Font.PLAIN, answerSelectionButtons[i].getHeight() / textSize));
			answerSelectionButtons[i].setForeground(Color.white);
			answerSelectionButtons[i].setBorderPainted(true);
			answerSelectionButtons[i].setBorder(raisedbevel);
			answerSelectionButtons[i].setContentAreaFilled(false);
			answerSelectionButtons[i].setFocusPainted(false);
			answerSelectionButtons[i].setOpaque(true);
			practiceOrTestPanel.add(answerSelectionButtons[i]);

			int j = i;
			answerSelectionButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if (answerSelectionButtons[j].isEnabled()) {
						answerSelectionButtons[j].setBackground(new Color(SKY_RGB[0], SKY_RGB[1], SKY_RGB[2]));
					}

				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (answerSelectionButtons[j].isEnabled()) {
						answerSelectionButtons[j].setBackground(new Color(0, 75, 200));
					}

				}
			});

			// When answer button is clicked, enable button for next question
			answerSelectionButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					answerSelectionButtons[0].setEnabled(false);
					answerSelectionButtons[1].setEnabled(false);
					answerSelectionButtons[2].setEnabled(false);

					nextQuestion.setEnabled(true);
					nextQuestion.setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));

					// If answer is right, highlight button in green and increment test score. Else,
					// highlight red and reveal correct answer button
					if (j == correctAnswerIndex) {
						answerSelectionButtons[j].setBackground(Color.GREEN);
						answerSelectionButtons[j].setFont(
								new Font("Arial Unicode MS", Font.PLAIN, answerSelectionButtons[j].getHeight() / 2));
						answerSelectionButtons[j].setText("\u2714");
						testScore++;
					} else {
						answerSelectionButtons[j].setBackground(Color.RED);
						answerSelectionButtons[3 - j - correctAnswerIndex].setBackground(Color.RED);
						answerSelectionButtons[j].setText("X");
						answerSelectionButtons[3 - j - correctAnswerIndex].setText("X");
						answerSelectionButtons[correctAnswerIndex].setBackground(Color.GREEN);
					}
				}
			});

		}
	}

	////////// BACKGROUND //////////
	public void setBackground() {

		// Set background image
		backgroundLabel = new JLabel();

		// Set background to same size as frame, minus buffer to account for external
		// frame edge region
		backgroundLabel.setBounds(0, 0, frame.getWidth() - FRAME_WIDTH_BUFFER, frame.getHeight() - FRAME_HEIGHT_BUFFER);
		frame.getContentPane().add(backgroundLabel);

		// Set background image to same size as backgroundLabel
		Image background = new ImageIcon(this.getClass().getResource("/background.png")).getImage().getScaledInstance(
				backgroundLabel.getWidth(), backgroundLabel.getHeight(), java.awt.Image.SCALE_SMOOTH);
		frame.getContentPane().setLayout(null);
		backgroundLabel.setIcon(new ImageIcon(background));

	}
}
