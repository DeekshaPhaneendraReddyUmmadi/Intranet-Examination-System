package in.ac.ksrmce.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.ac.ksrmce.config.student_config.StudentDao;
import in.ac.ksrmce.config.student_config.StudentEntity;

import java.security.SecureRandom;

/**
 * Servlet implementation class Registeration
 */
@WebServlet("/studentRegistration")
@MultipartConfig
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Registeration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Part photo = request.getPart("photo");
		Part signature = request.getPart("signature");
		
		String photoName = getFileName(photo);
		String signatureName = getFileName(signature);
		
		InputStream photoContent = photo.getInputStream();
		InputStream signatureContent = signature.getInputStream();
		
		String photoUploadPath = "C:\\Users\\phane\\Desktop\\project - final\\program files\\servelts\\project\\src\\main\\webapp\\images\\students\\photo\\";
		String signatureUploadPath = "C:\\Users\\phane\\Desktop\\project - final\\program files\\servelts\\project\\src\\main\\webapp\\images\\students\\signature\\";
		
		try (OutputStream out = new FileOutputStream(new File(photoUploadPath  + photoName))) {
			int read;
			byte[] bytes = new byte[1024];
			while((read = photoContent.read(bytes)) != -1) {
				out.write(bytes,0,read);
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		try (OutputStream out = new FileOutputStream(new File(signatureUploadPath  + signatureName))) {
			int read;
			byte[] bytes = new byte[1024];
			while((read = signatureContent.read(bytes)) != -1) {
				out.write(bytes,0,read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String name = request.getParameter("name");
		String roll = request.getParameter("roll");
		String fatherName = request.getParameter("fatherName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String district = request.getParameter("district"); 
		String referenceNumber = generateReferenceNumber(10);
//		System.out.println("reference Number: "+referenceNumber);
		
		StudentEntity student = new StudentEntity(name, roll, fatherName, email, mobile, gender, dob, district,photoName,signatureName,referenceNumber);
		
//		System.out.println("Studnet : "+student.toString());
		
		if(StudentDao.save(student)) {
			System.out.println("Inserted Successfully (Registration.java)");
		}else {
			System.out.println("Insertion Failed(Registration.java)");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 2, content.length() - 1);
            }
        }
        return "";
    }

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static SecureRandom secureRandom = new SecureRandom();
	
	private static String generateReferenceNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
