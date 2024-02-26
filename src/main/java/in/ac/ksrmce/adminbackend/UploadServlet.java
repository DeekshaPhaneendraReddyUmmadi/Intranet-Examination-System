package in.ac.ksrmce.adminbackend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/*
 * @MultipartConfig
 * 
 * @WebServlet("/UploadServlet") public class UploadServlet extends HttpServlet
 * { private static final long serialVersionUID = 1L; // private static final
 * String UPLOAD_DIRECTORY = "../questions"; private static final String
 * UPLOAD_DIRECTORY = "questionone";
 * 
 * 
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { Part file =
 * request.getPart("file");
 * 
 * String imgFileName = file.getSubmittedFileName();
 * System.out.println("selected image file name : " + imgFileName);
 * 
 * String uploadPath = request.getContextPath()
 * +"/src/main/java/in/ac/ksrmce/uploadedImages";
 * System.out.println("uploaded path : "+ uploadPath);
 * 
 * // try { // FileOutputStream fos = new FileOutputStream(uploadPath); // } }
 * 
 * 
 * 
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // Create upload directory
 * if it doesn't exist File uploadDir = new File(UPLOAD_DIRECTORY); if
 * (!uploadDir.exists()) { uploadDir.mkdir(); }
 * 
 * // Process the uploaded file Part filePart = request.getPart("file"); String
 * fileName = getFileName(filePart); OutputStream out = null; InputStream
 * fileContent = null;
 * 
 * try { out = new FileOutputStream(new File(UPLOAD_DIRECTORY + "/" +
 * fileName)); System.out.println(UPLOAD_DIRECTORY + "/" + fileName);
 * fileContent = filePart.getInputStream();
 * 
 * int read; final byte[] bytes = new byte[1024]; while ((read =
 * fileContent.read(bytes)) != -1) { out.write(bytes, 0, read); }
 * 
 * response.getWriter().println("File " + fileName + " uploaded successfully!");
 * } catch (FileNotFoundException e) { e.printStackTrace();
 * response.getWriter().println("File upload failed: " + e.getMessage()); }
 * finally { if (out != null) { out.close(); } if (fileContent != null) {
 * fileContent.close(); } } }
 * 
 * private String getFileName(Part part) { String contentDisposition =
 * part.getHeader("content-disposition"); String[] tokens =
 * contentDisposition.split(";"); for (String token : tokens) { if
 * (token.trim().startsWith("filename")) { return
 * token.substring(token.indexOf('=') + 2, token.length() - 1); } } return ""; }
 * 
 * }
 */







@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        InputStream fileContent = filePart.getInputStream();

        // Specify the directory to save the uploaded file
        String uploadDirectory = "C:\\Users\\phane\\Desktop\\project - final\\program files\\servelts\\project\\src\\main\\java\\in\\ac\\ksrmce\\questions\\";
//        String uploadDirectory = getServletContext().getRealPath("/questions/");
       
//        Path path = Paths.get();
//        System.out.println("absolute path : "+path.toAbsolutePath());
        
		/*
		 * System.out.println("context path :"+ request.getContextPath() );
		 * 
		 * String load = getServletContext().getRealPath("/questions/");
		 * System.out.println("Resolved path: " + load);
		 * 
		 * // Get the temporary deployment directory String deploymentDirectory =
		 * getServletContext().getRealPath("/");
		 * 
		 * // Specify the relative path within the deployment directory String
		 * relativePath = "project/questions/";
		 * 
		 * // Construct the absolute path by concatenating the deployment directory and
		 * relative path String load2 = deploymentDirectory + relativePath;
		 * System.out.println("Resolved path:(load2	) " + load2);
		 * 
		 * System.out.println("servlet path :"+getServletContext().getRealPath(
		 * "/questions/")); System.out.println("UPloaded folder : "+uploadDirectory);
		 */
        try (OutputStream out = new FileOutputStream(new File(uploadDirectory + fileName))) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            
            
            
            response.getWriter().println("File " + fileName + " uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("File upload failed: " + e.getMessage());
        }
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