package in.ac.ksrmce.config.questions_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	public static boolean isTrue(String reference_number) {
		String isT = "";
		try {
			Connection con=QuestionsDao.getConnection();
			PreparedStatement statement=con.prepareStatement("select is_true from marks where reference_number = ?");
			statement.setString(1, reference_number);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				isT = rs.getString("is_true");
			}
			
			if(isT.equals("true")) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static List<QuestionsEntity> listrandomQuestions(String sub,String reference_number){
		List<QuestionsEntity> listallquestions = new ArrayList<>();
		
		List<QuestionsEntity> que = listquestionswithsubjects(sub);
		
		System.out.println("reference Number : ; "+ reference_number);
		if(isTrue(reference_number)) {
			System.out.println("isTrue : "+ isTrue(reference_number));
			genratingRandomNumbers("maths",reference_number);
			genratingRandomNumbers("physics",reference_number);
			genratingRandomNumbers("chemistry",reference_number);
			
			subjectOptionsPut("maths",reference_number);
			subjectOptionsPut("physics",reference_number);
			subjectOptionsPut("chemistry",reference_number);
			
			subjectColorOptionsPut("maths",reference_number);
			subjectColorOptionsPut("physics",reference_number);
			subjectColorOptionsPut("chemistry",reference_number);
		}
		
		int[] randomQuestionNumbers = questionNumbers(sub , reference_number);
		
		for(int r : randomQuestionNumbers) {
			System.out.print(r+"  ");
		}

		for (int i = 0; i < randomQuestionNumbers.length; i++) {
		    int index = randomQuestionNumbers[i]-1;
		    if (index >= 0 && index < que.size()) {
		        listallquestions.add(que.get(index));
		    }
		}

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
        
        if(sub.equals("chemistry")) {
        	try{
    			Connection con=QuestionsDao.getConnection();
    			
    			String sql = "UPDATE marks SET  is_true  = 'true' WHERE reference_number = ?";
    			PreparedStatement statement = con.prepareStatement(sql);
    			statement.setString(1, reference_number);
    			statement.executeUpdate();
    			
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
        }
	}
	
	
	public static int[] subjectOptionsGet(String subject, String reference_number) {
	    String nums = "";
	    try {
	        Connection con = QuestionsDao.getConnection();
	        String randomQ = null;
	        if (subject.equals("maths")) {
	            randomQ = "answers_m";
	        } else if (subject.equals("physics")) {
	            randomQ = "answers_p";
	        } else if (subject.equals("chemistry")) {
	            randomQ = "answers_c";
	        }

	        String sql = "SELECT " + randomQ + " FROM marks WHERE reference_number = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, reference_number);

	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            nums = rs.getString(randomQ);
	        } else {
	        }
	        System.out.println("IN THE QUESTIONSDAO NUMS " + nums);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    if (nums.isEmpty()) {
	        return new int[0]; 
	    }

	    String[] elements = nums.substring(1, nums.length() - 1).split(", ");
	    int[] numbers = new int[elements.length];
	    for (int i = 0; i < elements.length; i++) {
	        try {
	            numbers[i] = Integer.parseInt(elements[i]);
	        } catch (NumberFormatException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return numbers;
	}

	
	public static void subjectOptionsPut(String subject , String reference_number) {
		System.out.println("IN the QUESTIONS DAO subject pUT : "+ subject);
		int count = countQuestions(subject);
		int[] array = new int[count];
		for(int i = 0 ; i < count ; i++) {
			array[i] = 0;
		}
		String arr = Arrays.toString(array);
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
			
			String sql = "UPDATE marks SET " + randomQ + " = ? WHERE reference_number = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,arr);
			statement.setString(2, reference_number);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int[] subjectColorOptionsGet(String subject, String reference_number) {
		String nums = "";
		try {
			Connection con = QuestionsDao.getConnection();
			String randomQ = null;
			if (subject.equals("maths")) {
				randomQ = "color_m";
			} else if (subject.equals("physics")) {
				randomQ = "color_p";
			} else if (subject.equals("chemistry")) {
				randomQ = "color_c";
			}
			
			String sql = "SELECT " + randomQ + " FROM marks WHERE reference_number = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, reference_number);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				nums = rs.getString(randomQ);
			} else {
			}
			System.out.println("IN THE QUESTIONSDAO NUMS " + nums);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (nums.isEmpty()) {
			return new int[0]; 
		}
		
		String[] elements = nums.substring(1, nums.length() - 1).split(", ");
		int[] numbers = new int[elements.length];
		for (int i = 0; i < elements.length; i++) {
			try {
				numbers[i] = Integer.parseInt(elements[i]);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		}
		return numbers;
	}
	
	
	public static void subjectColorOptionsPut(String subject , String reference_number) {
		System.out.println("IN the QUESTIONS DAO COLOR pUT : "+ subject);
		int count = countQuestions(subject);
		int[] array = new int[count];
		for(int i = 0 ; i < count ; i++) {
			array[i] = 0;
		}
		String arr = Arrays.toString(array);
		try{
			Connection con=QuestionsDao.getConnection();
			String randomQ = null;
			if (subject.equals("maths")) {
				randomQ = "color_m";
			} else if (subject.equals("physics")) {
				randomQ = "color_p";
			} else if (subject.equals("chemistry")) {
				randomQ = "color_c";
			}
			
			String sql = "UPDATE marks SET " + randomQ + " = ? WHERE reference_number = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,arr);
			statement.setString(2, reference_number);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveMarks(String reference_number ,String answers ,String subject,String btnColor) {
		 try{
				Connection con=QuestionsDao.getConnection();
				String randomQ = null;
				String color = null;
				if (subject.equals("maths")) {
				    randomQ = "answers_m";
				    color = "color_m";
				} else if (subject.equals("physics")) {
				    randomQ = "answers_p";
				    color = "color_p";
				} else if (subject.equals("chemistry")) {
				    randomQ = "answers_c";
				    color = "color_c";
				}
				String sql = "UPDATE marks SET " + randomQ + " = ?, " + color + " = ? WHERE reference_number = ?";

				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, answers);
				statement.setString(2, btnColor);
				statement.setString(3, reference_number);
				statement.executeUpdate();
				
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

}
