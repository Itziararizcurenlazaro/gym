package servlet.user;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM users WHERE id=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();


            response.sendRedirect("users");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}