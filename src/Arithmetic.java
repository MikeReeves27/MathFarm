import java.util.Random;

public class Arithmetic {
	private int grade;
	private int correctAnswer;
	private int correctAnswerIndex;
	private int range;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private String[] answers;
	private String[] questions;
	private String questionText;

	static int ADDITION = 1;
	static int SUBTRACTION = 2;
	static int MULTIPLICATION = 3;
	static int DIVISION = 4;

	Random rand = new Random();

	public Arithmetic(int grade, int operation) {
		this.grade = grade;

		// If K grade is chosen, set range to 5
		if (grade == 0) {
			range = 5;
		}

		// Randomly assign 1 correct answer and 2 wrong answers
		// Run loops to ensure all 3 answers are different
		int q1 = rand.nextInt(range) + 1;
		int q2 = rand.nextInt(range) + 1;

		if (operation == SUBTRACTION || operation == DIVISION) {
			while (true) {
				q2 = rand.nextInt(range - 1);
				if (q2 < q1)
					break;
			}
		}

		questions = new String[3];

		questions[0] = String.valueOf(q1);
		questions[1] = getOperator(operation);
		questions[2] = String.valueOf(q2);

		correctAnswer = buildCorrectAnswer(operation, q1, q2);
		while (true) {
			int answer = rand.nextInt(range) + 1;
			if (answer != correctAnswer) {
				wrongAnswer1 = answer;
				break;
			}
		}

		while (true) {
			int answer = rand.nextInt(range) + 1;
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

		this.questionText = "What does this equal?";

	}

	public int buildCorrectAnswer(int operation, int operand1, int operand2) {

		if (operation == ADDITION) {
			return (operand1 + operand2);
		}
		if (operation == SUBTRACTION) {
			return (operand1 - operand2);
		}
		if (operation == MULTIPLICATION) {
			return (operand1 * operand2);
		}
		if (operation == DIVISION) {
			return (operand1 / operand2);
		}

		return (operand1 + operand2);

	}

	public String getOperator(int operation) {
		if (operation == ADDITION) {
			return "+";
		}
		if (operation == SUBTRACTION) {
			return "-";
		}
		if (operation == MULTIPLICATION) {
			return "*";
		}
		if (operation == DIVISION) {
			return "/";
		}
		return "+";
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

	public String getQuestionText() {
		return questionText;
	}

}