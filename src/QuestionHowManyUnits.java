// Question class for:
// Grade 2 Measure: eg, "How many seconds are in a minute?"
// Grade 4 Measure: eg, "How many grams are in a kilogram?"

import java.util.Random;

public class QuestionHowManyUnits extends Question {

	private int grade;

	public QuestionHowManyUnits(int grade) {
		super();
		this.grade = grade;
	}

	public void generateQuestion() {

		Random rand = new Random();

		// List of possible question variables
		String[][][] questionList = { { /* K */ }, { /* 1 */ },
				{ { "seconds", "a minute", "60" }, { "minutes", "an hour", "60" }, { "hours", "a day", "24" },
						{ "days", "a week", "7" }, { "days", "a year", "365" }, { "months", "a year", "12" },
						{ "days", "", "" } },
				{ /* 3 */ },
				{ { "grams", "a kilogram", "1000" }, { "inches", "a foot", "12" }, { "inches", "two feet", "24" },
						{ "inches", "three feet", "36" }, { "inches", "four feet", "48" },
						{ "grams", "two kilograms", "2000" }, { "ounces", "a pound", "16" },
						{ "ounces", "two pounds", "32" }, { "meters", "a kilometer", "1000" } } };

		// List of possible answers
		String[][] answerList = { { /* K */ }, { /* 1 */ }, { "7", "12", "24", "28", "30", "31", "60", "365" },
				{ /* 3 */ }, { "12", "24", "16", "32", "36", "48", "1000", "2000" } };

		// List of possible question variables (if month is chosen)
		String[][] monthQuestionList = { { "January", "31" }, { "February", "28" }, { "March", "31" },
				{ "April", "30" }, { "May", "31" }, { "June", "30" }, { "July", "31" }, { "August", "31" },
				{ "September", "30" }, { "October", "31" }, { "November", "30" }, { "December", "31" } };

		// List of possible answers (if month is chosen)
		String[] monthAnswerList = { "28", "30", "31" };

		// Select array of question variables from above list
		String[] question = questionList[grade][rand.nextInt(questionList[grade].length)];

		// Check if question is not a month array
		if (question != questionList[2][questionList[2].length - 1]) {

			// Assign wrong answers
			while (true) {
				int answer = rand.nextInt(answerList[grade].length);
				if (!answerList[grade][answer].equals(question[2])) {
					wrongAnswer1 = answerList[grade][answer];
					break;
				}
			}
			while (true) {
				int answer = rand.nextInt(answerList[grade].length);
				if (!answerList[grade][answer].equals(question[2]) && !answerList[grade][answer].equals(wrongAnswer1)) {
					wrongAnswer2 = answerList[grade][answer];
					break;
				}
			}

		} else {

			// Assign right and wrong answers
			String[] month = monthQuestionList[rand.nextInt(monthQuestionList.length)];
			question[1] = month[0];
			question[2] = month[1];

			while (true) {
				int answer = rand.nextInt(monthAnswerList.length);
				if (!monthAnswerList[answer].equals(question[2])) {
					wrongAnswer1 = monthAnswerList[answer];
					break;
				}
			}
			while (true) {
				int answer = rand.nextInt(monthAnswerList.length);
				if (!monthAnswerList[answer].equals(question[2]) && !monthAnswerList[answer].equals(wrongAnswer1)) {
					wrongAnswer2 = monthAnswerList[answer];
					break;
				}
			}
		}

		// Randomly assign all 3 answers to different indicies in array. Run loops to
		// ensure all 3 are in unique places
		answers = new String[3];
		correctAnswerIndex = rand.nextInt(answers.length);
		answers[correctAnswerIndex] = question[2];
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

		// Assign question text for user
		questionText = ("<html>How many <font color='yellow'>" + question[0]
				+ "<font color='white'> are there in <font color='yellow'>" + question[1]
				+ "<font color='white'>?</html>");

	}

}
