import java.util.Random;

public class Compare {

	private String questionText;
	private int grade;
	private int range;
	private int firstNumber;
	private int secondNumber;
	private String correctAnswer;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String[] answers;
	private int correctAnswerIndex;
	private String question;

	Random rand = new Random();

	public Compare(int grade) {

		questionText = "Compare the two numbers on the screen";

		// Set number range according to grade
		if (grade == 0) {
			range = 10;
		}

		// Set first and second number according to range
		firstNumber = rand.nextInt(range);
		secondNumber = rand.nextInt(range);

		this.grade = grade;

		if (firstNumber > secondNumber) {
			correctAnswer = ">";
			wrongAnswer1 = "<";
			wrongAnswer2 = "=";
		} else if (firstNumber < secondNumber) {
			correctAnswer = "<";
			wrongAnswer1 = ">";
			wrongAnswer2 = "=";
		} else {
			correctAnswer = "=";
			wrongAnswer1 = "<";
			wrongAnswer2 = ">";
		}

		question = (firstNumber + " ? " + secondNumber);

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

	}

	public String getQuestionText() {
		return questionText;
	}

	public String getQuestion() {
		return question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String[] getAnswers() {
		return answers;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

}