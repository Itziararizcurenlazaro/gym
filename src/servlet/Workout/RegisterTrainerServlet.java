package servlet.Workout;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register-trainer")
public class RegisterTrainerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String name = request.getParameter("name");
            String speciality = request.getParameter("speciality");
            int experience_years= Integer.parseInt(request.getParameter("experience_years"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO trainers (name, speciality, experience_years, email, phone) VALUES (?, ?, ?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, speciality);
            ps.setInt(3, experience_years);
            ps.setString(4, email);
            ps.setString(5, phone);

            ps.executeUpdate();

            response.sendRedirect("trainers");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
