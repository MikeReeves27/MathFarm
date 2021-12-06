import java.util.Random;

public class FractionEquivalences {

	private String questionText;
	private String image;
	private String correctAnswer;
	private int correctAnswerIndex;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String[] answers;

	Random rand = new Random();

	public FractionEquivalences() {

		int[] answerList = { 2, 3, 4, 5, 6, 8, 10, 12 };

		// Randomly choose a denominator from assigned list and a numerator less than
		// denominator
		int questionDenominator = answerList[rand.nextInt(answerList.length)];
		int questionNumerator = rand.nextInt(questionDenominator - 1) + 1;
		image = questionNumerator + " / " + questionDenominator;

		// Randomly choose a multiplier of 2x or 3x
		int multiplier = rand.nextInt(2) + 2;

		// Multiply the question's numerator and denominator by the multiplier
		int correctDenominator = questionDenominator * multiplier;
		int correctNumerator = questionNumerator * multiplier;
		correctAnswer = correctNumerator + " / " + correctDenominator;

		// Create a decimal value of question's fraction
		double questionDecimal = (double) correctNumerator / correctDenominator;

		// Choose random wrong answers that haven't already been used by comparing their
		// decimals to the questions' decimal
		double answer1Decimal;
		while (true) {
			int denominator = answerList[rand.nextInt(answerList.length)];
			int numerator = rand.nextInt(denominator - 1) + 1;
			answer1Decimal = (double) numerator / denominator;
			if (answer1Decimal != questionDecimal) {
				wrongAnswer1 = numerator + " / " + denominator;
				break;
			}
		}
		double answer2Decimal;
		while (true) {
			int denominator = answerList[rand.nextInt(answerList.length)];
			int numerator = rand.nextInt(denominator - 1) + 1;
			answer2Decimal = (double) numerator / denominator;
			if (answer2Decimal != questionDecimal && answer2Decimal != answer1Decimal) {
				wrongAnswer2 = numerator + " / " + denominator;
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

		questionText = "What is this fraction equivalent to?";

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
