import java.util.Random;

public class Arithmetic {
	private int grade;
	private int correctAnswer;
	private int correctAnswerIndex;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private String[] answers;
	private String[] questions;

//	static int ADDITION = 1;
//	static int SUBTRACTION = 2;
//	static int MULTIPLICATION = 3;
//	static int DIVISION = 4;

	Random rand = new Random();

	public Arithmetic(int grade, int operation) {
		this.grade = grade;

		int[][] numberRanges = { { 5, 10 }, { 10, 20 }, { 10, 20 }, { 0, 0, 8, 8 }, { 0, 0, 10, 10 } };

		// range = getRange(grade);
		// Randomly assign 1 correct answer and 2 wrong answers
		// Run loops to ensure all 3 answers are different
		int q1 = rand.nextInt(numberRanges[grade][operation]) + 1;
		int q2 = rand.nextInt(numberRanges[grade][operation]) + 1;

		if (operation == 1) {
			while (true) {
				q2 = rand.nextInt(numberRanges[grade][operation] - 1);
				if (q2 < q1)
					break;
			}
		}
		if (operation == 2) {
			q1 += 2;
			q2 += 2;
		}
		if (operation == 3) {
			q1 += 2;
			q2 += 2;
			q1 *= q2;

		}

		questions = new String[3];

		String[] operators = { "+", "-", "x", "/" };
		questions[0] = String.valueOf(q1);
		questions[1] = operators[operation];
		questions[2] = String.valueOf(q2);

		correctAnswer = buildCorrectAnswer(operation, q1, q2);
		while (true) {
			int answer = rand.nextInt(numberRanges[grade][operation]) + 1;
			if (operation == 2) {
				answer *= q1;
			}
			if (answer != correctAnswer) {
				wrongAnswer1 = answer;
				break;
			}
		}

		while (true) {
			int answer = rand.nextInt(numberRanges[grade][operation]) + 1;
			if (operation == 2) {
				answer *= q2;
			}
			if (answer != correctAnswer && answer != wrongAnswer1) {
				wrongAnswer2 = answer;
				break;
			}
		}

		// Randomly assign all 3 answers to different indices in array. Run loops to
		// ensure all 3 are in unique places
		answers = new String[3];
		correctAnswerIndex = rand.nextInt(answers.length);
		answers[correctAnswerIndex] = String.valueOf(correctAnswer);
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = String.valueOf(wrongAnswer1);
				break;
			}
		}
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = String.valueOf(wrongAnswer2);
				break;
			}
		}

	}

	public int buildCorrectAnswer(int operation, int operand1, int operand2) {

		if (operation == 0) {
			return (operand1 + operand2);
		}
		if (operation == 1) {
			return (operand1 - operand2);
		}
		if (operation == 2) {
			return (operand1 * operand2);
		}
		if (operation == 3) {
			return (operand1 / operand2);
		}

		return (operand1 + operand2);

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

	public String[] getQuestions() {
		return questions;
	}

}
