package in.ac.ksrmce.student;

import in.ac.ksrmce.config.student_config.StudentDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/insertData")
@MultipartConfig
public class StudentsInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tableName = "students_info"; // Specify your table name here
        InputStream inputStream = null;
        Part filePart = request.getPart("file");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        Connection con = null;
        try {
        	con = StudentDao.getConnection();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String sql = "INSERT INTO " + tableName + "(name,age) VALUES (?, ?)"; // Adjust the query as per your table structure
                PreparedStatement statement = con.prepareStatement(sql);
                for (int i = 0; i < values.length; i++) {
                    statement.setString(i + 1, values[i]);
                }
                statement.executeUpdate();
            }
            response.getWriter().println("Data inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

