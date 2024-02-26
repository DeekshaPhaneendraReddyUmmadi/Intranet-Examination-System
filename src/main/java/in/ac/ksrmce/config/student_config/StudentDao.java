package in.ac.ksrmce.config.student_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ac.ksrmce.config.questions_config.QuestionsDao;

public class StudentDao {
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college_project","phanee","Mynewsql 1");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	
	public static boolean save(StudentEntity studentEntity) {

		try{
			Connection con=QuestionsDao.getConnection();
            String sql = "INSERT INTO student (name, roll, fatherName, email, mobile, gender, dob, district, passport_size_photo, hallticket_photo) VALUES (?, ?, ?,? ,?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, studentEntity.getName());
            statement.setString(2, studentEntity.getRoll());
            statement.setString(3, studentEntity.getFatherName());
            statement.setString(4, studentEntity.getEmail());
            statement.setString(5, studentEntity.getMobile());
            statement.setString(6, studentEntity.getGender());
            statement.setString(7, studentEntity.getDob());
            statement.setString(8, studentEntity.getDistrict());
            statement.setString(9, studentEntity.getPassport_size_photo());
            statement.setString(10, studentEntity.getHallticket_photo());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<StudentEntity> liststudents(){
		List<StudentEntity> liststudents = new ArrayList<StudentEntity>();
		
		try {
			Connection con=QuestionsDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				liststudents.add(new StudentEntity(rs.getLong("id"),rs.getString("name"),rs.getString("roll"),rs.getString("fatherName"),rs.getString("email"),rs.getString("mobile"),rs.getString("gender"),rs.getString("dob"),rs.getString("district"),rs.getString("passport_size_photo"),rs.getString("hallticket_photo")));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return liststudents;
	}
	
	public int count(){
		int totalRows = 0;
		try{
			Connection con=StudentDao.getConnection();
            String sql = "select count(*) from student";
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
	
//	public List<StudentEntity> liststudents1(){
//		List<StudentEntity> liststudents = new ArrayList<StudentEntity>();
//		 
//		try {
//			Connection con=StudentDao.getConnection();
//            String sql = "Select * from student";
//            PreparedStatement statement = con.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//			
//			while(rs.next()) {
//				liststudents.add(new StudentEntity(rs.getLong("id"),rs.getString("name"),rs.getString("roll"),rs.getString("fatherName"),rs.getString("email"),rs.getString("mobile"),rs.getString("gender"),rs.getString("dob"),rs.getString("district"),rs.getString("passport_size_photo"),rs.getString("hallticket_photo")));
//			}		
//		} catch (SQLException e) {
//				e.printStackTrace();
//		}
//
//		return liststudents;
//	}
}
