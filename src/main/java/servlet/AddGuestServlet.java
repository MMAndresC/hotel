package servlet;


import dao.Database;
import dao.GuestDao;
import domain.Guest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/add-guest")
public class AddGuestServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Guest guest = new Guest();
        guest.setId(request.getParameter("id"));
        guest.setName(request.getParameter("name"));
        guest.setRoom(request.getParameter("room"));
        guest.setPhone(request.getParameter("phone"));

        Database database = new Database();
        GuestDao guestDao = new GuestDao(database.getConnection());
        try {
            guestDao.add(guest);
            out.println("success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
