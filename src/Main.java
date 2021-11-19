
/** 
 * MATH FARM
 * By Michael Reeves, Mukil Selvaraju, Jonathan Duquette
*/

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

		// Arrays for the Learn/Practice buttons, images, and ratios
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
		learnPanel = new JPanel(new GridLayout());
		layeredPane.add(learnPanel, "name_180577775611500");
		learnPanel.setBackground(new Color(0, 0, 0, 0));
		learnPanel.setLayout(null);

		// Update headerLabel text
		headerLabel.setText("LEARN");

		// Learn links list
		DefaultListModel<String> dlm = new DefaultListModel<>();
		JList<String> gradeKLearnLinks = new JList<>(dlm);
		gradeKLearnLinks.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) e.getSource();
				if (!e.getValueIsAdjusting()) {
					int index = list.getSelectedIndex();
					if (index != -1) {
						String address = list.getSelectedValue();
						Browser.openWebpage(address);
					}
				}
			}
		});

		// DefaultListCellRenderer dlcf =
		// (DefaultListCellRenderer)gradeKLearnLinks.getCellRenderer();
		// dlcf.setHorizontalAlignment(JLabel.CENTER);
		gradeKLearnLinks.setBounds(755, 50, 332, 380);
		// JScrollPane jsp = new JScrollPane(gradeKLearnLinks);
		// jsp.setPreferredSize(new Dimension(1000,800));
		learnPanel.add(gradeKLearnLinks);
		// learnPanel.add(jsp);
		// gradeKLearnLinks.setBorderPainted(true);
		gradeKLearnLinks.setBorder(raisedbevel);
		// gradeKLearnLinks.setContentAreaFilled(false);
		// gradeKLearnLinks.setFocusPainted(false);
		gradeKLearnLinks.setOpaque(true);
		gradeKLearnLinks.setBackground(new Color(145, 210, 80));

		// Creating Hyper links
		String[] hyperlinks = { "https://www.youtube.com/watch?v=mvOkMYCygps&ab_channel=KhanAcademy",
				"https://www.youtube.com/watch?v=MTzTqvzWzm8&ab_channel=KhanAcademy",
				"https://www.youtube.com/watch?v=i6sbjtJjJ-A&ab_channel=TheOrganicChemistryTutor",
				"https://www.youtube.com/watch?v=sOE8Slo3Pqw&ab_channel=ScienceandMathsbyPrimroseKitten",
				"https://www.youtube.com/watch?v=Vuj5CZDy-Pc&ab_channel=MathandScience" };
		JLabel[] labels = new JLabel[hyperlinks.length];

		for (int i = 0; i < hyperlinks.length; i++) {
			labels[i] = new JLabel(hyperlinks[i]);
			labels[i].setBackground(Color.BLUE);

			dlm.addElement(hyperlinks[i]);
		}

		learnPanel.setVisible(true);
	}

	////////// PRACTICE PANEL //////////
	public void createPracticePanel(int grade) {
		practicePanel = new JPanel();
		layeredPane.add(practicePanel, "name_180577775611500");
		practicePanel.setBackground(new Color(0, 0, 0, 0));
		practicePanel.setLayout(null);

		// Update headerLabel text
		headerLabel.setText("PRACTICE");

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

				createPracticePanel(gradeSelection);
				switchPanel(practicePanel);
			}
		});

		// Choose a random question based on user's selected grade an cateogry
		chooseQuestion(gradeSelection, categorySelection, practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
				practicePanelLabel);

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
		Image buttonImage = new ImageIcon(this.getClass().getResource(image)).getImage().getScaledInstance(imageWidth,
				imageHeight, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(buttonImage));
		if (bevel) {
			button.setBorder(raisedbevel);
			button.setBorderPainted(true);
		}
	}

	////////// CHOOSE QUESTION //////////
	public void chooseQuestion(int gradeSelection, int categorySelection, JLabel practicePanelImageLabel,
			JLabel practicePanelTextLabel, JButton nextQuestion, JLabel practicePanelLabel) {
		Random rand = new Random();

		// Switch for grade level (0-4)
		switch (gradeSelection) {

		// Grade K:
		case 0:

			// Switch for category selection
			switch (categorySelection) {

			// Grade K: Count
			case 0:
				int activity = rand.nextInt(2);
				switch (activity) {

				// Grade K: Count: How many
				case 0:
					createQuestionHowMany(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel);
					break;

				// Grade K: Count: Next sequence
				case 1:
					createQuestionNextSequence(practicePanelImageLabel, practicePanelTextLabel, nextQuestion,
							practicePanelLabel);
					break;
				}
			}
		}
	}

	////////// QUESTION: GRADE K: CHOOSE HOW MANY ANIMALS THERE ARE //////////
	public void createQuestionHowMany(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel) {

		// Create new HowMany object
		HowMany howMany = new HowMany(gradeSelection);
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
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel);
	}

	////////// QUESTION: GRADE K: CHOOSE NEXT NUMBER IN THE SEQUENCE //////////
	public void createQuestionNextSequence(JLabel practicePanelImageLabel, JLabel practicePanelTextLabel,
			JButton nextQuestion, JLabel practicePanelLabel) {

		// Create new NextSequence object
		NextSequence nextSequence = new NextSequence();
		practicePanelTextLabel.setText(nextSequence.getQuestionText());
		String[] answers = nextSequence.getAnswers();
		int correctAnswerIndex = nextSequence.getCorrectAnswerIndex();
		practicePanelImageLabel.setText(nextSequence.getSequence());
		practicePanelImageLabel.setFont(new Font("Calibri", Font.PLAIN, practicePanelImageLabel.getHeight() / 3));

		// Create answer buttons
		createAnswerButtons(answers, nextQuestion, correctAnswerIndex, practicePanelLabel);
	}

	////////// CREATE ANSWER BUTTONS //////////
	public void createAnswerButtons(String[] answers, JButton nextQuestion, int correctAnswerIndex,
			JLabel practicePanelLabel) {

		// Create answer selection Buttons
		JButton[] answerSelectionButtons = new JButton[3];
		int answerSelectionButtonXCoordinate = PRIMARY_RATIO * 4;

		// Run loop 3 times (once per button). Create and draw buttons
		for (int i = 0; i < 3; i++) {
			answerSelectionButtons[i] = new JButton();
			answerSelectionButtons[i].setText(String.valueOf(answers[i]));
			answerSelectionButtons[i].setBackground(Color.BLUE);
			answerSelectionButtons[i].setBounds(answerSelectionButtonXCoordinate,
					practicePanelLabel.getHeight() + PRIMARY_RATIO, layeredPane.getWidth() / 5,
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
			practicePanel.add(answerSelectionButtons[i]);

			int j = i;

			// When answer button is clicked, enable button for next question
			answerSelectionButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					nextQuestion.setEnabled(true);
					nextQuestion.setBackground(new Color(GREEN_RGB[0], GREEN_RGB[1], GREEN_RGB[2]));

					// If answer is right, highlight button in greed. Else, highlight red and reveal
					// correct answer button
					if (j == correctAnswerIndex) {
						answerSelectionButtons[j].setBackground(Color.GREEN);
					} else {
						answerSelectionButtons[j].setBackground(Color.RED);
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
