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
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/add-bicycle")
@MultipartConfig
public class AddBicycleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Crear en tomcat -> webapp el directorio hotel-data o la insercion fallara porque no podra guardar la imagen
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Bicycle bicycle = new Bicycle();
        bicycle.setCod(0);
        bicycle.setBrand(request.getParameter("brand"));
        bicycle.setModel(request.getParameter("model"));
        bicycle.setType(request.getParameter("type"));
        bicycle.setSize(request.getParameter("size"));
        String fileName;
        String imagePath = request.getServletContext().getInitParameter("image-path");
        String image = request.getParameter("image");
        if( image != "" ){
            Part imagePart = request.getPart("image");
            String imgName = imagePart.getSubmittedFileName();
            int index = imgName.lastIndexOf('.');
            String extension = imgName.substring(index);
            if(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")){
                fileName = UUID.randomUUID() + extension;
                InputStream fileStream = imagePart.getInputStream();
                Files.copy(fileStream, Path.of(imagePath + File.separator + fileName));
            }else{
                fileName = "default_bicycle.jpg";
            }
        }else{
            fileName = "default_bicycle.jpg";
        }
        bicycle.setImage(fileName);
        Database database = new Database();
        BicycleDao bicycleDao = new BicycleDao(database.getConnection());
        try {
            bicycleDao.add(bicycle);
            out.println("success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
