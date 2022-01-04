// Question class for:
// Grade 2 Measure: eg, "How many seconds are in a minute?"
// Grade 4 Measure: eg, "How many grams are in a kilogram?"

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
						{}, {}, {}, { /* Shapes */ "cube", "sphere", "pyramid", "cone" } },
				{ // Grade 3
						{}, { /* Pizza fractions */ "11", "12", "13", "14", "18", "23", "34" }, {},
						{ /* Shapes */ "cube", "sphere", "pyramid", "cone", "cylinder", "torus",
								"rectangular prism" } },
				{ // Grade 4
						{}, {}, {}, { /* Shapes */ "cube", "sphere", "pyramid", "cone", "cylinder", "torus",
								"rectangular prism", "triangular prism", "pentagonal prism" } } };

		double[][][] imageRatioList = { { {}, {}, {}, { 1, 1, 1 } }, { {}, {}, {}, { 1, 1, 1, 2.19, 1.05, 1, 1 } },
				{ {}, {}, {}, { 1.25, 0.95, 1.79, 1.24 } },
				{ {}, { 1, 1, 1, 1, 1, 1, 1 }, {}, { 1.25, 0.95, 1.79, 1.24, 1.29, 1.37, 1.39 } },
				{ {}, {}, {}, { 1.25, 0.95, 1.79, 1.24, 1.29, 1.37, 1.39, 1.99, 1.26 } } };

		// Select array of question variables from above list

		int selectionIndex = rand.nextInt(questionList[grade][category].length);
		String question = questionList[grade][category][selectionIndex];
		image += question + ".png";
		imageRatio = imageRatioList[grade][category][selectionIndex];
		// String[] elements = allElements[grade];

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

		if (grade == 3 && category == 1) {

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
