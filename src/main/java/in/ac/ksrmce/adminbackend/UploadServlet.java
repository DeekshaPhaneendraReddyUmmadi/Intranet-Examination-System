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

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        InputStream fileContent = filePart.getInputStream();

        String uploadDirectory = "C:\\Users\\phane\\Desktop\\project - final\\program files\\servelts\\project\\src\\main\\java\\in\\ac\\ksrmce\\questions\\";

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