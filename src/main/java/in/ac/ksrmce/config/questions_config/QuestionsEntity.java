package in.ac.ksrmce.config.questions_config;

public class QuestionsEntity {
       
	private int id;
	private String question;
	private String option_one;
	private String option_two;
	private String option_three;
	private String option_four;
	private int correct_option;
	private String subject;
	
	public QuestionsEntity() {
		super();
	}
	
	public QuestionsEntity(int id, String question, String option_one, String option_two, String option_three,
			String option_four, int correct_option, String subject) {
		super();
		this.id = id;
		this.question = question;
		this.option_one = option_one;
		this.option_two = option_two;
		this.option_three = option_three;
		this.option_four = option_four;
		this.correct_option = correct_option;
		this.subject = subject;
	}

	public QuestionsEntity(int id ,String question, String option_one, String option_two, String option_three,
			String option_four, String subject) {
		super();
		this.id = id;
		this.question = question;
		this.option_one = option_one;
		this.option_two = option_two;
		this.option_three = option_three;
		this.option_four = option_four;
		this.subject = subject;
	}

	public QuestionsEntity(String question, String option_one, String option_two, String option_three,
			String option_four, int correct_option , String subject) {
		super();
		this.question = question;
		this.option_one = option_one;
		this.option_two = option_two;
		this.option_three = option_three;
		this.option_four = option_four;
		this.correct_option = correct_option;
		this.subject = subject;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption_one() {
		return option_one;
	}
	public void setOption_one(String option_one) {
		this.option_one = option_one;
	}
	public String getOption_two() {
		return option_two;
	}
	public void setOption_two(String option_two) {
		this.option_two = option_two;
	}
	public String getOption_three() {
		return option_three;
	}
	public void setOption_three(String option_three) {
		this.option_three = option_three;
	}
	public String getOption_four() {
		return option_four;
	}
	public void setOption_four(String option_four) {
		this.option_four = option_four;
	}
	public int getCorrect_option() {
		return correct_option;
	}
	public void setCorrect_option(int correct_option) {
		this.correct_option = correct_option;
	}
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "QuestionsEntity [id=" + id + ", question=" + question + ", option_one=" + option_one + ", option_two="
				+ option_two + ", option_three=" + option_three + ", option_four=" + option_four + ", correct_option="
				+ correct_option + "]";
	}
	
	
}
