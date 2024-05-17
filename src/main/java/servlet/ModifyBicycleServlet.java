package servlet;

import domain.Bicycle;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modify-bicycle")
public class ModifyBicycleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Bicycle bicycle = new Bicycle();
        bicycle.setCod(Integer.parseInt(request.getParameter("brand")));
        bicycle.setBrand(request.getParameter("brand"));
        bicycle.setModel(request.getParameter("model"));
        bicycle.setType(request.getParameter("type"));
        bicycle.setSize(request.getParameter("size"));
        String newImg = request.getParameter("newImg");
        String image = request.getParameter("image");
        //bicycle.setSize(request.getParameter("image"));

    }
}
