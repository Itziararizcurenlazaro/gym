package servlet.Workout;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/delete-workout")
public class DeleteWorkoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM workouts WHERE id=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();

            response.sendRedirect("workouts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
