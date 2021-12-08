
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
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Main {

	private JFrame frame;
	private JPanel headerPanel;
	private JLayeredPane layeredPane;
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

	// Global ints for user's test score
	private int testScore = 0;
	private int testCounter = 0;

	// Global array for panel hierarchy (used for Back button and header titles)
	private JPanel[] panelList = new JPanel[10];
	private String[] panelTitle = new String[10];
	private int panelCounter = 0;

	// Global array for tracking if badges are unlocked;
	private boolean[] badgeUnlocked = new boolean[5];

	// Global array for clickable button RGB values
	private int[] GREEN_RGB = { 146, 208, 80 };
	private int[] SKY_RGB = { 139, 197, 219 };
	private int[] DARK_GREEN_RGB = { 0, 176, 80 };

	// Set raised bevel border variable
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();

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

		// Initialize header panel
		createHeaderPanel();

		// Initialize layered pane
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(PRIMARY_RATIO, headerPanel.getHeight() + PRIMARY_RATIO * 2,
				frame.getWidth() - FRAME_WIDTH_BUFFER - PRIMARY_RATIO * 2,
				frame.getHeight() - FRAME_HEIGHT_BUFFER - PRIMARY_RATIO * 3 - headerPanel.getHeight());
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		// Initialize home panel
		createHomePanel();

		// Set background
		setBackground();

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

				// As of now it's going back to home page; will be fixed once login screen is
				// created
				if (panelCounter != 0) {
					switchPanel(homePanel);
					headerLabel.setText(panelTitle[panelCounter]);
				}

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
			if (badgeUnlocked[i] == true) {
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
				{ "ARITHMETIC", "FRACTIONS", "MEASURE", "GEOMETRY" } };

		// [This will change. Insert grade images here]
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
			testPanelImageLabel.setText(testScore + " / 10");

			// If user scores successful score, unlocked grade's badge
			if (testScore > 7) {
				badgeUnlocked[gradeSelection] = true;
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

		String[][][] hyperlinks = { {

				{ "https://www.youtube.com/watch?v=mjlsSYLLOSE&t=53s", "https://www.youtube.com/watch?v=rqiu_xcvSk4" },
				{ "https://www.youtube.com/watch?v=x6D51-pz2A4", "https://www.youtube.com/watch?v=2X6FSDrWDEo",
						"https://www.youtube.com/watch?v=UsbZ-1VmNvw", "https://www.youtube.com/watch?v=dihlSqall3k" },
				{ "https://www.youtube.com/watch?v=ka9zbPcqXBI" },
				{ "https://www.youtube.com/watch?v=1VBW6rdvRks", "https://www.youtube.com/watch?v=uaUnTxoF4hU",
						"https://www.youtube.com/watch?v=teif6M9FjHE" } },
				{ { "https://www.youtube.com/watch?v=bGetqbqDVaA", "https://www.youtube.com/watch?v=3iQqmmG8wQQ" },
						{ "https://www.youtube.com/watch?v=xdR7s8mwyp8", "https://www.youtube.com/watch?v=9p_Ca_Yb0zQ",
								"https://www.youtube.com/watch?v=pnXJGNo08v0",
								"https://www.youtube.com/watch?v=djwclHApLFo" },
						{ "COMPARE" }, { "SHAPES" } },
				{ { "ADD SUBTRACT" }, { "TIME MONEY" }, { "MEASURE" },
						{ "https://www.youtube.com/watch?v=w27TpLQexbc",
								"https://www.youtube.com/watch?v=guNdJ5MtX1A" } },
				{ { "https://www.youtube.com/watch?v=IwW0GJWKH98" },
						{ "https://www.youtube.com/watch?v=p33BYf1NDAE", "https://www.youtube.com/watch?v=KlfxIbO-KJs",
								"https://www.youtube.com/watch?v=SZaXtOHNh6s" },
						{ "MEASURE" }, { "GEOMETRY" } },
				{ { "ARITHMETIC" }, { "FRACTIONS" }, { "MEASURE" }, { "GEOMETRY" } } };

		String[][][] videoName = {
				{ { "Basic Math Addition", "Subtractions for kids" },
						{ "Count 1 to 10", "What Number is Missing", "Farm Animal Counting",
								"Learn to count with Number Farm" },
						{ "The Greater Than Less Than Song" },
						{ "Learn Shapes With Wooden Toy Truck", "Shape Song", "Learn Shapes for Kids" } },
				{ { "Big Numbers Song", "Even and Odd Numbers for Kids" },
						{ "Easy Time", "Telling the Time for Kids", "The Money Song", "Money & Making Change" },
						{ "COMPARE" }, { "SHAPES" } },
				{ { "ADD SUBTRACT" }, { "TIME MONEY" }, { "MEASURE" }, { "Learn About 3D Shapes", "3D Shapes Song" } },
				{ { "What is Arithmetic?" },
						{ "Fractions for Kids", "Equivalent Fractions", "Fractions on a Number Line Song" },
						{ "MEASURE" }, { "GEOMETRY" } },
				{ { "ARITHMETIC" }, { "FRACTIONS" }, { "MEASURE" }, { "GEOMETRY" } } };

		// Creating Hyper links
//		String[] hyperlinks = { "https://www.youtube.com/watch?v=mvOkMYCygps&ab_channel=KhanAcademy",
//				"https://www.youtube.com/watch?v=MTzTqvzWzm8&ab_channel=KhanAcademy",
//				"https://www.youtube.com/watch?v=i6sbjtJjJ-A&ab_channel=TheOrganicChemistryTutor",
//				"https://www.youtube.com/watch?v=sOE8Slo3Pqw&ab_channel=ScienceandMathsbyPrimroseKitten" };
		// "https://www.youtube.com/watch?v=Vuj5CZDy-Pc&ab_channel=MathandScience" };

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
				{ { "/plusSign.png", "/minusSign.png" }, { "/clock100.png" }, { /* COMPARE */ }, { "/shapes.png" } },
				{ { "/plusSign.png", "/minusSign.png" }, { "/calendar.png" }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { "/pizza23.png" }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { "/fractionEquivalences.png" }, { /* MEASURE */ },
						{ /* GEOMETRY */ } } };
		double[][][] activityImageRatios = { { { 1.0, 2.0 }, { 0.93, 3.92 }, { 0.62 }, { 0.93 } },
				{ { 1.0, 2.0 }, { 1.0 }, { /* COMPARE */ }, { 0.93 } },
				{ { 1.0, 2.0 }, { 1.18 }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { 1.0 }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { 1.48 }, { /* MEASURE */ }, { /* GEOMETRY */ } } };
		String[][][] activityText = {
				{ { "ADD", "SUBTRACT" }, { "HOW MANY", "WHAT'S NEXT" }, { "COMPARE" }, { "NAME THE SHAPE" } },
				{ { "ADD", "SUBTRACT" }, { "WHAT TIME" }, { /* COMPARE */ }, { "NAME THE SHAPE" } },
				{ { "ADD", "SUBTRACT" }, { "HOW MUCH TIME" }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { "PIZZA FRACTIONS" }, { /* MEASURE */ }, { /* GEOMETRY */ } },
				{ { /* MULTIPLY */, /* DIVIDE */ }, { "FRACTIONS" }, { /* MEASURE */ }, { /* GEOMETRY */ } } };

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
					createAdditionQuestions(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade K: Addition/Subtraction: Subtraction
				case 1:
					createAdditionQuestions(gradeSelection, 2, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
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
				createQuestionShapes(gradeSelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);

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
					createAdditionQuestions(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 1: Addition/Subtraction: Subtraction
				case 1:
					createAdditionQuestions(gradeSelection, 2, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 1: Time and Money
			case 1:
				createQuestionWhatTime(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 1: Compare
			case 2:
				// ??????
				break;

			// Grade 1: Shapes
			case 3:
				// ??????
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
					createAdditionQuestions(gradeSelection, 1, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 2: Addition/Subtraction: Subtraction
				case 1:
					createAdditionQuestions(gradeSelection, 2, practicePanelImageLabel, practicePanelTextLabel,
							nextQuestion, practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 2: Time and Money
			case 1:
				createQuestionHowMuchTime(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 2: Measure
			case 2:
				// ??????
				break;

			// Grade 2: Geometry
			case 3:
				// ??????
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
					// createMultDivisionQuestions(gradeSelection, 3, practicePanelImageLabel,
					// practicePanelTextLabel, nextQuestion,
					// practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 3: Multiplication/Division: Division
				case 1:
					// createMultDivisionQuestions(gradeSelection, 4, practicePanelImageLabel,
					// practicePanelTextLabel, nextQuestion,
					// practicePanelLabel, practiceOrTestPanel);
					break;
				}
				break;

			// Grade 3: Fractions
			case 1:
				createQuestionFractions(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
						practicePanelLabel, practiceOrTestPanel);
				break;

			// Grade 3: Measure
			case 2:
				// ??????
				break;

			// Grade 3: Geometry
			case 3:
				// ??????
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
					// createMultDivisionQuestions(gradeSelection, 3, practicePanelImageLabel,
					// practicePanelTextLabel, nextQuestion,
					// practicePanelLabel, practiceOrTestPanel);
					break;

				// Grade 4: Multiplication/Division: Division
				case 1:
					// createMultDivisionQuestions(gradeSelection, 4, practicePanelImageLabel,
					// practicePanelTextLabel, nextQuestion,
					// practicePanelLabel, practiceOrTestPanel);
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
				// ??????
				break;

			// Grade 4: Geometry
			case 3:
				// ??????
				break;
			}
			break;
		}
	}

	////////// QUESTION: GRADE K: ADDITION //////////
	public void createAdditionQuestions(int gradeSelection, int operation, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel,
			JPanel practiceOrTestPanel) {

		// Create new ADDITION OR SUBTRACTION object
		Arithmetic arithmetic = new Arithmetic(gradeSelection, operation);
		practicePanelTextLabel.setText(arithmetic.getQuestionText());
		String[] answers = arithmetic.getAnswers();
		String[] questions = arithmetic.getQuestions();
		int correctAnswerIndex = arithmetic.getCorrectAnswerIndex();

		practicePanelImageLabel.setVisible(true);

		String question = questions[0] + "  " + questions[1] + "  " + questions[2];
		practicePanelImageLabel.setText(question);
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 2));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);

	}

	////////// QUESTION: GRADE K-1: Name the shape //////////
	public void createQuestionShapes(int gradeSelection, JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

		// Create new Compare object
		Shapes shapes = new Shapes(0);
		practicePanelTextLabel.setText(shapes.getQuestionText());
		String[] answers = shapes.getAnswers();
		int correctAnswerIndex = shapes.getCorrectAnswerIndex();
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));
		int imageHeight = practicePanelImageLabel.getHeight();

		Image questionImage = new ImageIcon(this.getClass().getResource(shapes.getImage())).getImage()
				.getScaledInstance(imageHeight, imageHeight, java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);

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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
	}

	////////// QUESTION: GRADE 2: HOW MUCH TIME //////////
	public void createQuestionHowMuchTime(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {
		HowMuchTime howMuchTime = new HowMuchTime();
		String[] answers = howMuchTime.getAnswers();
		int correctAnswerIndex = howMuchTime.getCorrectAnswerIndex();
		practicePanelImageLabel.setText(howMuchTime.getQuestionText());
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 4));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
	}

	////////// QUESTION: GRADE 3: FRACTIONS //////////
	public void createQuestionFractions(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel, JPanel practiceOrTestPanel) {
		Fractions fractions = new Fractions();
		practicePanelTextLabel.setText(fractions.getQuestionText());
		String[] answers = fractions.getAnswers();
		int correctAnswerIndex = fractions.getCorrectAnswerIndex();
		String img = fractions.getImage();
		Image questionImage = new ImageIcon(this.getClass().getResource(img)).getImage().getScaledInstance(
				practicePanelImageLabel.getHeight(), practicePanelImageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH);
		practicePanelImageLabel.setIcon(new ImageIcon(questionImage));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel, practiceOrTestPanel);
	}

	////////// CREATE ANSWER BUTTONS //////////
	public void createAnswerButtons(String[] answers, JButton nextQuestion, int correctAnswerIndex,
			JLabel practicePanelLabel, JPanel practiceOrTestPanel) {

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
					.setFont(new Font("Calibri", Font.PLAIN, answerSelectionButtons[i].getHeight() / 2));
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
					answerSelectionButtons[j].setBackground(new Color(SKY_RGB[0], SKY_RGB[1], SKY_RGB[2]));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					answerSelectionButtons[j].setBackground(new Color(0, 75, 200));
				}
			});

			// When answer button is clicked, enable button for next question
			answerSelectionButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

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
		JLabel backgroundLabel = new JLabel();

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
