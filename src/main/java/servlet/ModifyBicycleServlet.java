package servlet;

import dao.BicycleDao;
import dao.Database;
import domain.Bicycle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/modify-bicycle")
@MultipartConfig
public class ModifyBicycleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Bicycle bicycle = new Bicycle();
        bicycle.setCod(Integer.parseInt(request.getParameter("cod")));
        bicycle.setBrand(request.getParameter("brand"));
        bicycle.setModel(request.getParameter("model"));
        bicycle.setType(request.getParameter("type"));
        bicycle.setSize(request.getParameter("size"));
        String newImg = request.getParameter("newImg");
        String image = request.getParameter("image");
        //Toda la movida para cambiar la imagen
        String fileName = image;
        if(image != newImg){
            Part imagePart = request.getPart("newImg");
            String imgName = imagePart.getSubmittedFileName();
            if(!(imgName.equalsIgnoreCase("default_bicycle.jpg"))) {
                int index = imgName.lastIndexOf('.');
                String extension = imgName.substring(index);
                if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
                    String imagePath = request.getServletContext().getInitParameter("image-path");
                    fileName = UUID.randomUUID() + extension;
                    InputStream fileStream = imagePart.getInputStream();
                    //Copia la nueva imagen y elimina la antigua siempre que no sea la default
                    Files.copy(fileStream, Path.of(imagePath + File.separator + fileName));
                    if(!(image.equalsIgnoreCase("default_bicycle.jpg"))){
                        Files.delete(Paths.get(imagePath + File.separator + image));
                    }
                    bicycle.setSize(fileName);
                }
            }
        }
        //Guardar los cambios en la BD
        Database database = new Database();
        BicycleDao bicycleDao = new BicycleDao(database.getConnection());
        try {
            bicycleDao.modify(bicycle.getCod(), bicycle);
            out.println("<div class='alert alert-success' role='alert'>Successfully modified</div>");
        } catch (SQLException e) {
            out.println("<div class='alert alert-danger' role='alert'> Failed to save changes</div>");
            throw new RuntimeException(e);
        }
    }
}
