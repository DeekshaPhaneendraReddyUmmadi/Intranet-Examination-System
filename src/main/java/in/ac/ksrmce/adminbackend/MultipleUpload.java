package in.ac.ksrmce.adminbackend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.ac.ksrmce.config.questions_config.QuestionsDao;
import in.ac.ksrmce.config.questions_config.QuestionsEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/multiImage")
@MultipartConfig
public class MultipleUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MultipleUpload() {
        super();
    }
   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[] imgNames = new String[5];
    	String action = request.getParameter("action");
    	
        
        for (int i = 1; i <= 5; i++) {

            Part filePart = request.getPart("image" + i);
            String fileName = getFileName(filePart);
            
            
            imgNames[i-1] = fileName;
            
            InputStream fileContent = filePart.getInputStream();
            String uploadPath = "C:\\Users\\phane\\Desktop\\project - final\\program files\\servelts\\project\\src\\main\\webapp\\images\\questions\\";
            try (OutputStream out = new FileOutputStream(new File(uploadPath  + fileName))) {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = fileContent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String question = imgNames[0];      
        String option_one = imgNames[1];
        String option_two = imgNames[2];
        String option_three = imgNames[3];
        String option_four = imgNames[4];
        int correct_option = Integer.parseInt(request.getParameter("correct_option"));
        String subject = request.getParameter("subject");
        
//        System.out.println("subject : " + subject);
        QuestionsEntity questions = new QuestionsEntity(question,option_one,option_two,option_three,option_four,correct_option,subject);
        QuestionsDao dao = new QuestionsDao();
//        System.out.println(request.getContextPath()+"/html/admin/add_mul_questions.jsp");
        
        
        if(dao.saveSubject(questions)) {
        	response.getWriter().println("Images uploaded successfully!");
        }else {
        	response.getWriter().println("Images uploaded successfully! but not on database !!");
        }
//        System.out.println(action);
//        if ("Upload".equals(action)) {
//            if(dao.save(questions)) {
//	        	response.getWriter().println("Images uploaded successfully!");
//	        }else {
//	        	response.getWriter().println("Images uploaded successfully! but not on database !!");
//	        }
//        } else if("Upload & New".equals(action)) {
//        	response.sendRedirect(request.getContextPath()+"/html/admin/add_mul_questions.jsp");
//        }
        
        
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

}
