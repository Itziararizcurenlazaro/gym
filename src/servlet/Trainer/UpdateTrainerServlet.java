package servlet.Trainer;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/update-trainer")
public class UpdateTrainerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String speciality = request.getParameter("speciality");
            int experience_years = Integer.parseInt(request.getParameter("experience_years"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE trainers SET name=?, speciality=?, experience_years=?, email=?, phone=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setString(2, speciality);
            ps.setInt(3, experience_years);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setInt(6, id);

            ps.executeUpdate();

            response.sendRedirect("trainers");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}