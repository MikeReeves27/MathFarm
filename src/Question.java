public class Question {

	protected String questionText;
	protected int correctAnswerIndex;
	protected String wrongAnswer1;
	protected String wrongAnswer2;
	protected String[] answers;
	protected int answerTextSize;
	protected String image;
	protected double imageRatio;
	// protected boolean singleImage;

	public Question() {
		this.answerTextSize = 2;
		this.image = "/";
	}

	public void generateQuestion() {
	}

	public String getQuestionText() {
		return questionText;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public String getWrongAnswer1() {
		return wrongAnswer1;
	}

	public String getWrongAnswer2() {
		return wrongAnswer2;
	}

	public String[] getAnswers() {
		return answers;
	}

	public String getImage() {
		return image;
	}

	public double getImageRatio() {
		return imageRatio;
	}

//	public boolean isSingleImage() {
//		return singleImage;
//	}

}
