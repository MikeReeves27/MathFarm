import java.util.Random;

public class HowMany {

	private int grade;
	private String questionText;
	private String element;
	private String image;
	private double imageRatio;
	private int correctAnswer;
	private int correctAnswerIndex;
	private int range;
	private int wrongAnswer1;
	private int wrongAnswer2;
	private String[] answers;

	Random rand = new Random();

	public HowMany(int grade) {
		this.grade = grade;

		// Declare element names and their image ratios
		String[] elements = { "lambs", "roosters", "piggies" };
		double[] imageRatios = { 0.93, 0.83, 1.20 };

		// Choose an element from the array at random
		int index = rand.nextInt(elements.length);
		element = elements[index];
		imageRatio = imageRatios[index];
		image = "/" + element + ".png";
		questionText = "How many " + element + " are there?";

		// If K grade is chosen, set range to 10
		if (grade == 0) {
			range = 10;
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

	}

	public String getQuestionText() {
		return questionText;
	}

	public String getImage() {
		return image;
	}

	public double getImageRatio() {
		return imageRatio;
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
