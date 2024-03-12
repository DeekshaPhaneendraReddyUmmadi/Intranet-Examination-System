package in.ac.ksrmce.config.student_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ac.ksrmce.config.admin_config.AdminDao;
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
	
	public static boolean saveReferenceNumber(String ref) {
		try {
			Connection con=StudentDao.getConnection();
            String sql = "INSERT INTO marks(reference_number) VALUES (?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ref);
            int rowInserted = statement.executeUpdate();
            return rowInserted>0;
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	
	
	public static boolean save(StudentEntity studentEntity) {

		try{
			saveReferenceNumber(studentEntity.getReferenceNumber());
			Connection con=StudentDao.getConnection();
            String sql = "INSERT INTO students(name, roll, father_name, email, mobile, gender, dob, district, student_photo, signature,reference_number) VALUES (?, ?, ?,? ,?, ?, ?, ?, ?,? , ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, studentEntity.getName());
            statement.setString(2, studentEntity.getRoll());
            statement.setString(3, studentEntity.getFatherName());
            statement.setString(4, studentEntity.getEmail());
            statement.setString(5, studentEntity.getMobile());
            statement.setString(6, studentEntity.getGender());
            statement.setString(7, studentEntity.getDob());
            statement.setString(8, studentEntity.getDistrict());
            statement.setString(9, studentEntity.getPhoto());
            statement.setString(10, studentEntity.getSignature());
            statement.setString(11, studentEntity.getReferenceNumber());
//            System.out.println("student ENITY : "+ studentEntity.toString());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public List<StudentEntity> liststudents(){
		List<StudentEntity> liststudents = new ArrayList<StudentEntity>();
		
		try {
			Connection con=QuestionsDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from students");
			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				liststudents.add(new StudentEntity(rs.getLong("id"),rs.getString("name"),rs.getString("roll"),rs.getString("fatherName"),rs.getString("email"),rs.getString("mobile"),rs.getString("gender"),rs.getString("dob"),rs.getString("district"),rs.getString("photo"),rs.getString("signature"),rs.getString("reference_number")));
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
	
	
	public static StudentEntity getstudentByName(String name){
		StudentEntity e=new StudentEntity();
		try{
			Connection con=AdminDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from students where name=?");
			ps.setString(1,name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getLong(1));
				e.setName(rs.getString(2));
				e.setDob(rs.getString(8));
				e.setPhoto(rs.getString(10));
				e.setReferenceNumber(rs.getString(12));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		return e;
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
