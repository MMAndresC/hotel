package servlet;

import dao.Database;
import dao.GuestDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete-guest")
public class DeleteGuestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        Database database = new Database();
        GuestDao guestDao = new GuestDao(database.getConnection());
        guestDao.delete(id);
        response.sendRedirect("guest.jsp");
    }

}
