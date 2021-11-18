import java.util.Random;

public class HowMany {

	private int grade;
	private String questionText;
	private String element;
	private String image;
	private int correctAnswer;
	private int range;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private int[] answers;

	Random rand = new Random();

	public HowMany(int grade) {
		this.grade = grade;
		String[] elements = { "lambs", "roosters", "piggies" };
		element = elements[rand.nextInt(elements.length)];
		image = "/" + element + ".png";
		questionText = "How many " + element + " are there?";

		// If K grade is chosen, set range to 5
		if (grade == 0) {
			range = 5;
		}

		// Randomly assign 1 correct answer and 2 wrong answers
		// Run loops to ensure all 3 answers are different
		correctAnswer = rand.nextInt(range) + 1;
		while (true) {
			int answer = rand.nextInt(range) + 1;
			if (answer != correctAnswer) {
				wrongAnswer1 = answer;
				break;
			}
		}
		while (true) {
			int answer = rand.nextInt(range) + 1;
			if (answer != correctAnswer && answer != wrongAnswer2) {
				wrongAnswer1 = answer;
				break;
			}
		}

		// Randomly assign all 3 answers to different indicies in array
		// Run loops to ensure all 3 are in unique places
		answers = new int[3];
		answers[rand.nextInt(answers.length)] = correctAnswer;
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == 0) {
				answers[i] = wrongAnswer1;
				break;
			}
		}
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == 0) {
				answers[i] = wrongAnswer2;
				break;
			}
		}

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

	public int[] getAnswers() {
		return answers;
	}

}
