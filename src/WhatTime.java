import java.util.Random;

public class WhatTime {

	private String questionText;
	private String image;
	private int correctAnswer;
	private int correctAnswerIndex;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private String[] answers;

	Random rand = new Random();

	public WhatTime() {

		questionText = "What time is it?";

		// Randomly assign 1 correct answer and 2 wrong answers
		// Run loops to ensure all 3 answers are different
		correctAnswer = rand.nextInt(12) + 1;
		while (true) {
			int answer = rand.nextInt(12) + 1;
			if (answer != correctAnswer) {
				wrongAnswer1 = answer;
				break;
			}
		}
		while (true) {
			int answer = rand.nextInt(12) + 1;
			if (answer != correctAnswer && answer != wrongAnswer1) {
				wrongAnswer2 = answer;
				break;
			}
		}

		// Randomly assign all 3 answers to different indicies in array. Run loops to
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

		// Append ":00" to each numeric answer to represent "o'clock"
		for (int i = 0; i < answers.length; i++) {
			answers[i] += ":00";
		}

		// Multiply each answer by 100 to make it match image file name
		correctAnswer *= 100;
		wrongAnswer1 *= 100;
		wrongAnswer2 *= 100;

		image = "/clock" + correctAnswer + ".png";

	}

	public String getQuestionText() {
		return questionText;
	}

	public String getImage() {
		return image;
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
