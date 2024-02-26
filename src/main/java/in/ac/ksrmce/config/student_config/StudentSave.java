package in.ac.ksrmce.config.student_config;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveStudent")
public class StudentSave extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentSave() {
        super();
    }

    static int i = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");;
        String roll = request.getParameter("roll");;
        String fatherName = request.getParameter("fathername");;
        String email = request.getParameter("email");;
        String mobile = request.getParameter("mobile");;
        String gender = request.getParameter("gender");;
        String dob = request.getParameter("dob");;
        String district = request.getParameter("district");;
        String passport_size_photo = request.getParameter("passport_size_photo");;
        String hallticket_photo = request.getParameter("hallticket_photo");;

        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setRoll(roll);
        student.setFatherName(fatherName);
        student.setEmail(email);
        student.setMobile(mobile);
        student.setGender(gender);
        student.setDob(dob);
        student.setDistrict(district);
        student.setPassport_size_photo(passport_size_photo);
        student.setHallticket_photo(hallticket_photo);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (StudentDao.save(student)) {
            out.println("<h2>Question saved successfully!</h2>");
        } else {
            out.println("<h2>Failed to save question. Please try again.</h2>");
        }
    }

}
