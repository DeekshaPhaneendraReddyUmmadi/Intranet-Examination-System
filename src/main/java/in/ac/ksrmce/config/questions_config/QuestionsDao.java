package in.ac.ksrmce.config.questions_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		 
//		 System.out.println("in the save servlet : ");
//		 System.out.println("question : "+question.getQuestion());
//		 System.out.println("option 1 : "+question.getOption_one());
//		 System.out.println("option 2 : "+question.getOption_two());
//		 System.out.println("option 3 : "+question.getOption_three());
//		 System.out.println("option 4 : "+question.getOption_four());
//		 System.out.println("option 5 : "+question.getCorrect_option());
//		 System.out.println(question);
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
	
	public static List<QuestionsEntity> listquestions(){
		List<QuestionsEntity> listquestions = new ArrayList<QuestionsEntity>();
		
		try {
			Connection con=QuestionsDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from questions");
			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				listquestions.add(new QuestionsEntity(rs.getInt("id"),rs.getString("question"),rs.getString("option_one"),rs.getString("option_two"),rs.getString("option_three"),rs.getString("option_four"),rs.getInt("correct_option"),rs.getString("subject")));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return listquestions;
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
	
	public static int countofsubject(String sub){
		int totalRows = 0;
		try{
			Connection con=StudentDao.getConnection();
			String sql = "SELECT COUNT(*) FROM questions WHERE subject = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, sub);
			ResultSet rs = statement.executeQuery();
			
			
			
			while(rs.next()) {
				totalRows = rs.getInt("count(*)");
			}
			
//			System.out.println(totalRows);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRows;
	}
	
	
	public static List<QuestionsEntity> randomquesions(){
		List<QuestionsEntity> randomquesions = new ArrayList<QuestionsEntity>();
		try {
	        Connection con=QuestionsDao.getConnection();
	        PreparedStatement ps=con.prepareStatement("SELECT * FROM questions ORDER BY RAND() LIMIT "+count());
	        ResultSet rs=ps.executeQuery();
	        
	        
	        while(rs.next()) {
	        	randomquesions.add(new QuestionsEntity(rs.getInt("id"),rs.getString("question"),rs.getString("option_one"),rs.getString("option_two"),rs.getString("option_three"),rs.getString("option_four"),rs.getInt("correct_option"),rs.getString("subject")));
			}

	    } catch (SQLException e) {
	            e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		return randomquesions;
	}
	public static List<QuestionsEntity> randomquesionswithsubject(String sub){
		List<QuestionsEntity> randomquesions = new ArrayList<QuestionsEntity>();
		try {
			Connection con=QuestionsDao.getConnection();
			
			String sql = "SELECT * FROM questions WHERE subject=? ORDER BY RAND() LIMIT ?";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, sub);
            ps.setInt(2, countofsubject(sub));
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				randomquesions.add(new QuestionsEntity(rs.getInt("id"),rs.getString("question"),rs.getString("option_one"),rs.getString("option_two"),rs.getString("option_three"),rs.getString("option_four"),rs.getInt("correct_option"),rs.getString("subject")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return randomquesions;
	}

}
