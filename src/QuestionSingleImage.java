// Question class for:
// Grades K-4: "What shape is this?"
// Grade 1: "What time is it?"
// Grades 2-3: "How long is it?"
// Grade 3: "Pizza fractions"

import java.util.Random;

public class QuestionSingleImage extends Question {

	private int grade;
	private int category;

	public QuestionSingleImage(int grade, int category) {
		super();
		this.grade = grade;
		this.category = category;
	}

	public void generateQuestion() {

		Random rand = new Random();

		// List of possible answers
		String[][][] questionList = { { // Grade K
				{}, {}, {}, { /* Shapes */ "square", "circle", "triangle" } },
				{ // Grade 1
						{}, {}, {},
						{ /* Shapes */ "square", "circle", "triangle", "trapezoid", "pentagon", "hexagon",
								"octagon" } },
				{ // Grade 2
						{}, {},
						{ /* Measure */ "1 inch", "2 inches", "3 inches", "4 inches", "5 inches", "6 inches",
								"7 inches", "8 inches", "9 inches", "10 inches", "11 inches", "12 inches" },
						{ /* Shapes */ "cube", "sphere", "pyramid", "cone" } },
				{ // Grade 3
						{}, { /* Pizza fractions */ "11", "12", "13", "14", "18", "23", "34" },
						{ /* Measure */ "0.5 inch", "1 inch", "1.5 inch", "2 inches", "2.5 inches", "3 inches",
								"3.5 inches", "4 inches", "4.5 inches", "5 inches", "5.5 inches", "6 inches",
								"6.5 inches", "7 inches", "7.5 inches", "8 inches", "8.5 inches", "9 inches",
								"9.5 inches", "10 inches", "10.5 inches", "11 inches", "11.5 inches", "12 inches",
								"12.5 inches" },
						{ /* Shapes */ "cube", "sphere", "pyramid", "cone", "cylinder", "torus",
								"rectangular prism" } },
				{ // Grade 4
						{}, {}, {}, { /* Shapes */ "cube", "sphere", "pyramid", "cone", "cylinder", "torus",
								"rectangular prism", "triangular prism", "pentagonal prism" } } };

		double[][][] imageRatioList = { { // Grade K
				{}, {}, {}, { 1, 1, 1 } },
				{ // Grade 1
						{}, {}, {}, { 1, 1, 1, 2.19, 1.05, 1, 1 } },
				{ // Grade 2
						{}, {}, { 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86 },
						{ 1.25, 0.95, 1.79, 1.24 } },
				{ // Grade 3
						{}, { 1, 1, 1, 1, 1, 1, 1 },
						{ 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86,
								1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86, 1.86 },
						{ 1.25, 0.95, 1.79, 1.24, 1.29, 1.37, 1.39 } },
				{ // Grade 4
						{}, {}, {}, { 1.25, 0.95, 1.79, 1.24, 1.29, 1.37, 1.39, 1.99, 1.26 } } };

		// Select array of question variables from above list
		int selectionIndex = rand.nextInt(questionList[grade][category].length);
		String question = questionList[grade][category][selectionIndex];
		image += question + ".png";
		imageRatio = imageRatioList[grade][category][selectionIndex];

		// Assign wrong answers
		while (true) {
			String answer = questionList[grade][category][rand.nextInt(questionList[grade][category].length)];
			if (!answer.equals(question)) {
				wrongAnswer1 = answer;
				break;
			}
		}
		while (true) {
			String answer = questionList[grade][category][rand.nextInt(questionList[grade][category].length)];
			if (!answer.equals(question) && !answer.equals(wrongAnswer1)) {
				wrongAnswer2 = answer;
				break;
			}
		}

		// Randomly assign all 3 answers to different indicies in array. Run loops to
		// ensure all 3 are in unique places
		answers = new String[3];
		correctAnswerIndex = rand.nextInt(answers.length);
		answers[correctAnswerIndex] = question;
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = wrongAnswer1;
				break;
			}
		}
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = wrongAnswer2;
				break;
			}
		}

		// Assign correct question text prompt
		if (grade == 1 && category == 2) {
			questionText = "What time is it?";
		} else if (grade == 2 && category == 2) {
			questionText = "Choose the correct number of inches marked on the ruler";
		} else if (grade == 3 && category == 1) {

			questionText = "What is this fraction?";
			image = "/pizza" + question + ".png";

			// Rename answers to include a slash between numerator and denominator
			for (int i = 0; i < answers.length; i++) {
				char numerator = answers[i].charAt(0);
				char denominator = answers[i].charAt(1);
				answers[i] = numerator + " / " + denominator;
			}

		} else if (category == 3) {
			questionText = "Choose the name of the shape";
			answerTextSize = 3;
		}

	}

}
