import java.util.Random;

public class Fractions {

	private String questionText;
	private String image;
	private String correctAnswer;
	private int correctAnswerIndex;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String[] answers;

	Random rand = new Random();

	public Fractions() {

		String[] answerList = { "11", "12", "13", "14", "18", "23", "34" };

		correctAnswer = answerList[rand.nextInt(answerList.length)];

		// Choose random wrong answers that haven't already been used
		while (true) {
			int answer = rand.nextInt(answerList.length);
			if (!answerList[answer].equals(correctAnswer)) {
				wrongAnswer1 = answerList[answer];
				break;
			}
		}
		while (true) {
			int answer = rand.nextInt(answerList.length);
			if (!answerList[answer].equals(correctAnswer) && !answerList[answer].equals(wrongAnswer1)) {
				wrongAnswer2 = answerList[answer];
				break;
			}
		}

		// Randomly assign all 3 answers to different indicies in array. Run loops to
		// ensure all 3 are in unique places
		answers = new String[3];
		correctAnswerIndex = rand.nextInt(answers.length);
		answers[correctAnswerIndex] = correctAnswer;
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

		questionText = "What is this fraction?";

		// Assign correct image
		image = "/pizza" + correctAnswer + ".png";

		// Rename answers to include a slash between numerator and denominator
		for (int i = 0; i < answers.length; i++) {
			char numerator = answers[i].charAt(0);
			char denominator = answers[i].charAt(1);
			answers[i] = numerator + " / " + denominator;
		}

	}

	public String getQuestionText() {
		return questionText;
	}

	public String getImage() {
		return image;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public String[] getAnswers() {
		return answers;
	}

}
