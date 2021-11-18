
/** 
 * MATH FARM
 * By Michael Reeves, Mukil Selvaraju, Jonathan Duquette
*/

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel homePanel;
	private JPanel gradePanel;
	private JPanel learnPracticePanel;
	private JPanel learnPanel;
	private JPanel practicePanel;
	private JLabel headerLabel;
	private JLabel practiceContentLabel;

	// Global constants for overall frame's ratio and buffer
	int PRIMARY_RATIO;
	int FRAME_WIDTH_BUFFER;
	int FRAME_HEIGHT_BUFFER;

	// Global int for user's grade selection
	private int gradeSelection;
	private int categorySelection;

	// Global array for panel hierarchy (used for Back button and header titles)
	private JPanel[] panelList = new JPanel[10];
	private String[] panelTitle = new String[10];
	private int panelCounter = 0;

	// Global array for clickable button RGB values
	private int[] GREEN_RGB = { 146, 208, 80 };
	private int[] SKY_RGB = { 139, 197, 219 };

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

		// Initalize layered pane
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
				PRIMARY_RATIO * 3);
		frame.getContentPane().add(headerPanel);
		headerPanel.setBackground(new Color(0, 0, 0, 0));
		headerPanel.setLayout(null);

		// Header content title label
		headerLabel = new JLabel("HOME");
		headerLabel.setVerticalAlignment(SwingConstants.CENTER);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("Calibri", Font.PLAIN, headerPanel.getHeight() / 3));
		headerLabel.setBackground(Color.ORANGE);
		headerLabel.setBounds(headerPanel.getWidth() / 4, 0, headerPanel.getWidth() / 2, headerPanel.getHeight());
		headerPanel.add(headerLabel);
		headerLabel.setOpaque(true);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		headerLabel.setBorder(raisedbevel);

		// Home button
		JButton homeButton = new JButton();
		headerPanel.add(homeButton);

		// Set button height/width to headerPanel height
		createButton(homeButton, 0, 0, headerPanel.getHeight(), headerPanel.getHeight(), SKY_RGB, "/homeButton.png",
				headerPanel.getHeight(), headerPanel.getHeight(), false);

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
				headerPanel.getHeight(), headerPanel.getHeight(), SKY_RGB, "/backButton.png", headerPanel.getHeight(),
				headerPanel.getHeight(), false);

		// When clicked, return to previous panel
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelCounter != 0) {
					switchPanel(panelList[--panelCounter]);
					headerLabel.setText(panelTitle[panelCounter]);
				}

			}
		});

	}

	////////// HOME SCREEN //////////
	public void createHomePanel() {

		homePanel = new JPanel();
		layeredPane.add(homePanel, "name_180568393921300");
		homePanel.setBackground(new Color(0, 0, 0, 0));
		homePanel.setLayout(null);

		panelList[panelCounter] = homePanel;
		panelTitle[panelCounter] = "HOME";
		headerLabel.setText(panelTitle[panelCounter]);

		// Array for each home panel animal button
		JButton[] homePanelButtons = new JButton[5];

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
			createButton(homePanelButtons[i], homePanelButtonXCoordinate, layeredPane.getHeight() / 2,
					PRIMARY_RATIO * 6, layeredPane.getHeight() / 2, GREEN_RGB, homePanelAnimalImages[i],
					(int) (PRIMARY_RATIO * 5 * homePanelAnimalRatios[i]), PRIMARY_RATIO * 5, true);
			homePanel.add(homePanelButtons[i]);
			homePanelButtonXCoordinate += (layeredPane.getWidth() - PRIMARY_RATIO * 6) / 4;

			// Set j equal to i for button click method
			int j = i;

			// When button is clicked, switch to GRADE PANEL
			homePanelButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Set user's grade selection to current index
					gradeSelection = j;

					createGradePanel(gradeSelection);
					panelList[++panelCounter] = gradePanel;
					panelTitle[panelCounter] = "GRADE";
					headerLabel.setText(panelTitle[panelCounter]);
					switchPanel(gradePanel);

				}
			});

		}

	}

	////////// GRADE PANEL //////////
	public void createGradePanel(int grade) {
		gradePanel = new JPanel();
		layeredPane.add(gradePanel, "name_180577775611500");
		gradePanel.setBackground(new Color(0, 0, 0, 0));
		gradePanel.setLayout(null);

		// Array for each grade panel button
		JButton[] gradePanelButtons = new JButton[5];

		// Array for all grade categories
		String[][] categories = { { "COUNT", "<html><center>ADD & <br>SUBTRACT</html>", "COMPARE", "SHAPES" },
				{ "<html><center>ADD & <br>SUBTRACT</html>", "<html><center>TIME & <br>MONEY</html>", "COMPARE",
						"SHAPES" },
				{ "<html><center>ADD & <br>SUBTRACT</html>", "<html><center>TIME & <br>MONEY</html>", "MEASURE",
						"GEOMETRY" },
				{ "<html><center>MULTIPLY & <br>DIVIDE</html>", "FRACTIONS", "MEASURE", "GEOMETRY" },
				{ "ARITHMETIC", "FRACTIONS", "MEASURE", "GEOMETRY" } };

		// [This will change. Insert grade images here]
		String[] gradePanelImages = { "/countButton.png", "/addSubtractButton.png", "/compareButton.png",
				"/shapesButton.png", "/homeHorse.png" };
		double[] gradePanelImageRatios = { 0.94, 2.11, 1.49, 0.92 };

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
			if (gradePanelImageRatios[i] < 1) {
				gradePanelImage = new ImageIcon(this.getClass().getResource(gradePanelImages[i])).getImage()
						.getScaledInstance((int) (PRIMARY_RATIO * 4 * gradePanelImageRatios[i]), PRIMARY_RATIO * 4,
								java.awt.Image.SCALE_SMOOTH);
			} else {
				gradePanelImage = new ImageIcon(this.getClass().getResource(gradePanelImages[i])).getImage()
						.getScaledInstance((int) (PRIMARY_RATIO * 4),
								(int) (PRIMARY_RATIO * 4 / gradePanelImageRatios[i]), java.awt.Image.SCALE_SMOOTH);
			}

			// Create labels for the image and text to be side-by-side
			JLabel buttonImage = new JLabel();
			JLabel buttonText = new JLabel();
			gradePanelButtons[i].setLayout(null);
			gradePanelButtons[i].add(buttonImage);
			gradePanelButtons[i].add(buttonText);

			// Place the image and text on the label
			buttonImage.setIcon(new ImageIcon(gradePanelImage));
			buttonImage.setHorizontalAlignment(SwingConstants.CENTER);
			buttonText.setFont(new Font("Calibri", Font.PLAIN, gradePanelButtons[i].getHeight() / 6));
			buttonText.setText(String.valueOf(categories[grade][i]));
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
					categorySelection = j;

					createLearnPracticePanel(gradeSelection);
					panelList[++panelCounter] = learnPracticePanel;
					panelTitle[panelCounter] = "LEARN OR PRACTICE";
					headerLabel.setText(panelTitle[panelCounter]);
					switchPanel(learnPracticePanel);

				}
			});

		}

	}

	////////// LEARN / PRACTICE PANEL //////////
	public void createLearnPracticePanel(int grade) {
		learnPracticePanel = new JPanel();
		layeredPane.add(learnPracticePanel, "name_180577775611500");
		learnPracticePanel.setBackground(new Color(0, 0, 0, 0));
		learnPracticePanel.setLayout(null);

		// Arrays for the Learn and Practice buttons
		// Arrays for button images and ratios
		JButton[] learnPracticeButtons = new JButton[2];
		String[][] learnPracticeImages = { { "/learnChicken.png", "/practiceChicken.png" } };
		double[][] learnPracticeImageRatios = { { 0.7, 0.61 } };

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
					layeredPane.getHeight(), GREEN_RGB, learnPracticeImages[gradeSelection][i],
					buttonWidth - PRIMARY_RATIO * 4,
					(int) ((buttonWidth - PRIMARY_RATIO * 4) / learnPracticeImageRatios[gradeSelection][i]), true);
			learnPracticePanel.add(learnPracticeButtons[i]);
		}

		// Action Listener for Learn button
		learnPracticeButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLearnPanel(gradeSelection);
				panelList[++panelCounter] = learnPanel;
				panelTitle[panelCounter] = "LEARN";
				headerLabel.setText(panelTitle[panelCounter]);
				switchPanel(learnPanel);
			}
		});

		// Action Listener for Practice button
		learnPracticeButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPracticePanel(gradeSelection);
				panelList[++panelCounter] = practicePanel;
				panelTitle[panelCounter] = "PRACTICE";
				headerLabel.setText(panelTitle[panelCounter]);
				switchPanel(practicePanel);
			}
		});

	}

	////////// LEARN PANEL //////////
	public void createLearnPanel(int grade) {
		learnPanel = new JPanel();
		layeredPane.add(learnPanel, "name_180577775611500");
		learnPanel.setBackground(new Color(0, 0, 0, 0));
		learnPanel.setLayout(null);

		// Update headerLabel text
		headerLabel.setText("LEARN");

		// Learn links button
		JButton gradeKLearnLinks = new JButton("");
		gradeKLearnLinks.setBounds(105, 25, 1000, 400);
		learnPanel.add(gradeKLearnLinks);
		gradeKLearnLinks.setBorderPainted(true);
		gradeKLearnLinks.setBorder(raisedbevel);
		gradeKLearnLinks.setContentAreaFilled(false);
		gradeKLearnLinks.setFocusPainted(false);
		gradeKLearnLinks.setOpaque(true);
		gradeKLearnLinks.setBackground(new Color(145, 210, 80));

		// Creating Hyper links
		JLabel[] hyperlinks = new JLabel[3];
		int hyperlinksXCoordinate = 150;
		int hyperlinksYCoordinate = 350;

		for (int i = 0; i < 3; i++) {
			hyperlinks[i] = new JLabel("Hyperlink number" + (i + 1));
			hyperlinks[i].setBackground(Color.BLUE);
			hyperlinks[i].setBounds(hyperlinksXCoordinate, hyperlinksYCoordinate,
					(layeredPane.getWidth() - PRIMARY_RATIO * 2) / 6, (layeredPane.getHeight() - PRIMARY_RATIO) / 5);
			hyperlinksXCoordinate = hyperlinksXCoordinate + (layeredPane.getWidth() - PRIMARY_RATIO * 2) / 4
					+ PRIMARY_RATIO;
			// System.out.println(PRIMARY_RATIO + " " + layeredPane.getWidth() + " " +
			// hyperlinksXCoordinate);
			hyperlinks[i].setOpaque(false);
			gradeKLearnLinks.add(hyperlinks[i]);
		}

	}

	////////// PRACTICE PANEL //////////
	public void createPracticePanel(int grade) {
		practicePanel = new JPanel();
		layeredPane.add(practicePanel, "name_180577775611500");
		practicePanel.setBackground(new Color(0, 0, 0, 0));
		practicePanel.setLayout(null);

		// Update headerLabel text
		headerLabel.setText("PRACTICE");

		// Practice Panel button
		JButton gradeKPracticePanelButton = new JButton("Question goes here");
		gradeKPracticePanelButton.setBounds(105, 0, 1000, 350);
		practicePanel.add(gradeKPracticePanelButton);
		gradeKPracticePanelButton.setBorderPainted(true);
		gradeKPracticePanelButton.setBorder(raisedbevel);
		gradeKPracticePanelButton.setContentAreaFilled(false);
		gradeKPracticePanelButton.setFocusPainted(false);
		gradeKPracticePanelButton.setOpaque(true);
		gradeKPracticePanelButton.setBackground(new Color(0, 176, 80));

		HowMany howMany = new HowMany(gradeSelection);
		gradeKPracticePanelButton.setText(howMany.getQuestionText());
		int[] answers = howMany.getAnswers();
		String img = howMany.getImage();
		Image questionImage = new ImageIcon(this.getClass().getResource(img)).getImage().getScaledInstance(100, 100,
				java.awt.Image.SCALE_SMOOTH);
		gradeKPracticePanelButton.setIcon(new ImageIcon(questionImage));
		// Answer Selection Buttons

		JButton[] answerSelectionButtons = new JButton[3];
		int answerSelectionButtonXCoordinate = 150;
		int answerSelectionButtonYCoordinate = 350;

		for (int i = 0; i < 3; i++) {
			answerSelectionButtons[i] = new JButton();
			answerSelectionButtons[i].setText(String.valueOf(answers[i]));
			answerSelectionButtons[i].setBackground(Color.BLUE);

			answerSelectionButtons[i].setBounds(answerSelectionButtonXCoordinate, answerSelectionButtonYCoordinate,
					(layeredPane.getWidth() - PRIMARY_RATIO * 2) / 6, (layeredPane.getHeight() - PRIMARY_RATIO) / 5);
			answerSelectionButtonXCoordinate = answerSelectionButtonXCoordinate
					+ (layeredPane.getWidth() - PRIMARY_RATIO * 2) / 4 + PRIMARY_RATIO;
			// System.out.println(PRIMARY_RATIO + " " + layeredPane.getWidth() + " " +
			// answerSelectionButtonXCoordinate);
			answerSelectionButtons[i].setBorderPainted(true);
			answerSelectionButtons[i].setBorder(raisedbevel);
			answerSelectionButtons[i].setContentAreaFilled(false);
			answerSelectionButtons[i].setFocusPainted(false);
			answerSelectionButtons[i].setOpaque(false);
			answerSelectionButtons[i].setFocusPainted(false);
			answerSelectionButtons[i].setOpaque(true);
			practicePanel.add(answerSelectionButtons[i]);
		}

	}

	// Create button with image
	public void createButton(JButton button, int x, int y, int width, int height, int[] rgb, String image,
			int imageWidth, int imageHeight, boolean bevel) {
		button.setBounds(x, y, width, height);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
		Image buttonImage = new ImageIcon(this.getClass().getResource(image)).getImage().getScaledInstance(imageWidth,
				imageHeight, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(buttonImage));
		if (bevel) {
			button.setBorder(raisedbevel);
			button.setBorderPainted(true);
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
