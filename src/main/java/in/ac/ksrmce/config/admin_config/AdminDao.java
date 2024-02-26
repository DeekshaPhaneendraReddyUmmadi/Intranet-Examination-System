package in.ac.ksrmce.config.admin_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminDao {
	
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college_project","phanee","Mynewsql 1");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	
	public static int save(AdminEntity e){
		int status=0;
		try{
			Connection con=AdminDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into admin(name,password) values (?,?)");
			ps.setString(1,e.getUser_name());
			ps.setString(2,e.getPassword());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		return status;
	}
	
	public static AdminEntity getEmployeeByName(String user_name){
		AdminEntity e=new AdminEntity();
		try{
			Connection con=AdminDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from admin where user_name=?");
			ps.setString(1,user_name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setUser_name(rs.getString(2));
				e.setPassword(rs.getString(3));
			}
//			System.out.println(user_name);
//			System.out.println(e);
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
//		System.out.println(e);
		return e;
	}


}
