package in.ac.ksrmce.adminbackend;

import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Image() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part file = request.getPart("file");
		
		String imgFileName = file.getSubmittedFileName();
		System.out.println("selected image file name : " + imgFileName);
		
		String uploadPath = "C:/Users/phane/Desktop/project - final/program files/servelts/project/src/main/java/in/ac/ksrmce/uploadedImages";
		System.out.println("uploaded path : "+ uploadPath +"/"+ imgFileName);
		
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			is.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
