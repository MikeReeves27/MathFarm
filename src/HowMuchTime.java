import java.util.Random;

public class HowMuchTime {

	// private String[][] questionList;
	private String questionText;
	private int correctAnswer;
	private int correctAnswerIndex;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String[] answers;

	Random rand = new Random();

	public HowMuchTime() {

		String[][] questionList = { { "seconds", "a minute", "60" }, { "minutes", "an hour", "60" },
				{ "hours", "a day", "24" }, { "days", "a week", "7" }, { "days", "a year", "365" },
				{ "months", "a year", "12" }, { "days", "", "" } };

		String[] answerList = { "7", "12", "24", "28", "30", "31", "60", "365" };
		String[][] monthQuestionList = { { "January", "31" }, { "February", "28" }, { "March", "31" },
				{ "April", "30" }, { "May", "31" }, { "June", "30" }, { "July", "31" }, { "August", "31" },
				{ "September", "30" }, { "October", "31" }, { "November", "30" }, { "December", "31" } };
		String[] monthAnswerList = { "28", "30", "31" };

		String[] question = questionList[rand.nextInt(questionList.length)];

		if (question != questionList[questionList.length - 1]) {
			while (true) {
				int answer = rand.nextInt(answerList.length);
				if (!answerList[answer].equals(question[2])) {
					wrongAnswer1 = answerList[answer];
					break;
				}
			}
			while (true) {
				int answer = rand.nextInt(answerList.length);
				if (!answerList[answer].equals(question[2]) && !answerList[answer].equals(wrongAnswer1)) {
					wrongAnswer2 = answerList[answer];
					break;
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
		} else {
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
		}

		questionText = ("<html>How many <font color='yellow'>" + question[0]
				+ "<font color='white'> are there in <font color='yellow'>" + question[1]
				+ "<font color='white'>?</html>");

	}

	public String getQuestionText() {
		return questionText;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public String[] getAnswers() {
		return answers;
	}

}
