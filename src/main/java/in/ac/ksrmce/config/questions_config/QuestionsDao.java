package in.ac.ksrmce.config.questions_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.ac.ksrmce.config.student_config.StudentDao;


public class QuestionsDao {
	public static Connection getConnection() throws  ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_project","phanee","Mynewsql 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean save(QuestionsEntity question) {
		try{
			Connection con=QuestionsDao.getConnection();
            String sql = "INSERT INTO questions(question, option_one, option_two, option_three, option_four, correct_option,subject) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getOption_one());
            statement.setString(3, question.getOption_two());
            statement.setString(4, question.getOption_three());
            statement.setString(5, question.getOption_four());
            statement.setInt(6, question.getCorrect_option());
            statement.setString(7, question.getSubject());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
    }
	
	
	public boolean saveSubject(QuestionsEntity question) {
		try{
			Connection con=QuestionsDao.getConnection();
			String sql = "INSERT INTO "+ question.getSubject()+"(question, option_one, option_two, option_three, option_four, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, question.getQuestion());
			statement.setString(2, question.getOption_one());
			statement.setString(3, question.getOption_two());
			statement.setString(4, question.getOption_three());
			statement.setString(5, question.getOption_four());
			statement.setInt(6, question.getCorrect_option());
			int rowsInserted = statement.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static List<QuestionsEntity> listquestionswithsubjects(String sub){
		List<QuestionsEntity> listquestions = new ArrayList<QuestionsEntity>();
		
		try {
			Connection con=QuestionsDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from "+sub);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				listquestions.add(new QuestionsEntity(rs.getInt("id"),rs.getString("question"),rs.getString("option_one"),rs.getString("option_two"),rs.getString("option_three"),rs.getString("option_four"),rs.getInt("correct_option")));		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return listquestions;
	}
	
	public static List<QuestionsEntity> listrandomQuestions(String sub,String reference_number){
		List<QuestionsEntity> listallquestions = new ArrayList<>();
		
		List<QuestionsEntity> que = listquestionswithsubjects(sub);
		
		QuestionsDao.genratingRandomNumbers("maths",reference_number);
		QuestionsDao.genratingRandomNumbers("physics",reference_number);
		QuestionsDao.genratingRandomNumbers("chemistry",reference_number);
		
//		int count = countQuestions(sub);
//		System.out.println("i''m que : "+que.toString());
		int[] randomQuestionNumbers = questionNumbers(sub , reference_number);
		
//		System.out.println(randomQuestionNumbers.toString());
		for(int r : randomQuestionNumbers) {
			System.out.print(r+"  ");
		}
//		for(QuestionsEntity r : que) {
//			System.out.println(r+"  ");
//		}
//		System.out.println("que(size) : "+que.size()+" |   num size : ;"+ randomQuestionNumbers.length);
//		for(int i = 0 ;i < que.size() ; i++ ) {
////			System.out.println("im in teh adding  block : : i + "+ (i));
//			listallquestions.add(que.get(randomQuestionNumbers[i]));
//		}
		
		
		for (int i = 0; i < randomQuestionNumbers.length; i++) {
//			System.out.println("i = "+ i);
		    int index = randomQuestionNumbers[i]-1;
		    if (index >= 0 && index < que.size()) {
//		    	System.out.println("index = "+ index);
		        listallquestions.add(que.get(index));
		    }
		}
		
//		System.out.println("length : "+listallquestions.size());
//		
//		for(QuestionsEntity r : listallquestions) {
//			System.out.println(r+"  ");
//		}
		
//		System.out.println("i'm printing : "+listallquestions.toString());
		
		return listallquestions;
	}
	
	public static int[] questionNumbers(String sub , String reference_number) {
		
		
		String randomQ = null;
		if (sub.equals("maths")) {
		    randomQ = "random_question_numbers_m";
		} else if (sub.equals("physics")) {
		    randomQ = "random_question_numbers_p";
		} else if (sub.equals("chemistry")) {
		    randomQ = "random_question_numbers_c";
		}
		
		String list = null;
		try{
			Connection con=StudentDao.getConnection();
            String sql = "select  "+randomQ +" from marks where reference_number = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, reference_number);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				list = rs.getString(randomQ);
			}
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
		
//		for convert from a list using to store with the help of toString method like [1,2,3,4,5] to an array like array = {1,2,3,4,5}
		String[] elements = list.substring(1, list.length() - 1).split(", ");
		
		int[] numbers = new int[elements.length];
		for (int i = 0; i < elements.length; i++) {
			numbers[i] = Integer.parseInt(elements[i]);
		}
		
		
		return numbers;
	}
	
	
	public static int count(){
		int totalRows = 0;
		try{
			Connection con=StudentDao.getConnection();
            String sql = "select count(*) from questions";
            PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				totalRows = rs.getInt("count(*)");
			}
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return totalRows;
	}
	
	public static int countQuestions(String sub){
		int totalRows = 0;
		try{
			Connection con=StudentDao.getConnection();
			String sql = "select count(*) from "+sub;
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				totalRows = rs.getInt("count(*)");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRows;
	}
		
	public static void genratingRandomNumbers(String sub,String reference_number) {
		List<QuestionsEntity> questions = QuestionsDao.listquestionswithsubjects(sub);
		List<Integer> sequence = new ArrayList<>();
		Collections.shuffle(questions);

        for(QuestionsEntity q : questions) {
        	int num = q.getId();
        	sequence.add(num);
        }
        Collections.shuffle(sequence);
        
        try{
			Connection con=QuestionsDao.getConnection();
			String randomQ = null;
			if (sub.equals("maths")) {
			    randomQ = "random_question_numbers_m";
			} else if (sub.equals("physics")) {
			    randomQ = "random_question_numbers_p";
			} else if (sub.equals("chemistry")) {
			    randomQ = "random_question_numbers_c";
			}
			
			String sql = "UPDATE marks SET " + randomQ + " = ? WHERE reference_number = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, sequence.toString());
			statement.setString(2, reference_number);
			statement.executeUpdate();
			
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		return sequence.toString();
	}
	
	public static void saveMarks(String reference_number ,String answers ,String subject) {
		 try{
				Connection con=QuestionsDao.getConnection();
				String randomQ = null;
				if (subject.equals("maths")) {
				    randomQ = "answers_m";
				} else if (subject.equals("physics")) {
				    randomQ = "answers_p";
				} else if (subject.equals("chemistry")) {
				    randomQ = "answers_c";
				}
				System.out.println("in the questions  answers :  "+ answers);
				String sql = "UPDATE marks SET " + randomQ + " = ? WHERE reference_number = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, answers);
				statement.setString(2, reference_number);
				statement.executeUpdate();
				
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

}
