package servlet.user;

import utils.DBConnection;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register-user")
@MultipartConfig
public class RegisterUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int age= Integer.parseInt(request.getParameter("age"));
            String fitness_goal = request.getParameter("fitness_goal");
            String membership_type = request.getParameter("membership_type");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users (name, email, age, fitness_goal, membership_type) VALUES (?, ?, ?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            ps.setString(4, fitness_goal);
            ps.setString(5, membership_type);
            ps.executeUpdate();

            response.sendRedirect("users");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}