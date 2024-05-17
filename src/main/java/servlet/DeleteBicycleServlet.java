package servlet;

import dao.BicycleDao;
import dao.Database;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete-bicycle")
public class DeleteBicycleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int cod = Integer.parseInt(request.getParameter("cod"));
        Database database = new Database();
        BicycleDao bicycleDao = new BicycleDao(database.getConnection());
        //TODO que borre tambien la imagen
        bicycleDao.delete(cod);
        response.sendRedirect("index.jsp");
    }

}
