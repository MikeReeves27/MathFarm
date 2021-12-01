import java.util.Random;

public class Shapes {

	private String questionText;
	private int grade;
	private String element;
	private String image;
	private double imageRatio;
	private String correctAnswer;
	private int correctAnswerIndex;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private int range;
	private String[] answers;

	Random rand = new Random();

	public Shapes(int grade) {

		questionText = "Choose the name of the shape";

		// Array of shape elements
		String[] elements = { "square", "circle", "triangle" };
		double[] imageRatios = { 0.93, 0.83, 1.20 };

		int index = rand.nextInt(elements.length);
		element = elements[index];
		imageRatio = imageRatios[index];

		questionText = "What is the name of the shape?";

		// If K grade is chosen, set range to 10
		if (grade == 0) {
			range = 3;
		}

		// Randomly assign 1 correct answer and 2 wrong answers
		// Run loops to ensure all 3 answers are different
		int correctIndex = rand.nextInt(range);
		int wrongIndex = 0;
		correctAnswer = elements[correctIndex];
		image = "/" + correctAnswer + ".png";
		for (int i = 0; i < elements.length; i++) {
			if (i != correctIndex) {
				wrongAnswer1 = elements[i];
				wrongIndex = i;
			}
		}
		for (int i = 0; i < elements.length; i++) {
			if (i != correctIndex && i != wrongIndex) {
				wrongAnswer2 = elements[i];
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