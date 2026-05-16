package servlet.Workout;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/update-workout")
public class UpdateWorkoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String difficulty = request.getParameter("difficulty");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String description = request.getParameter("description");
            int trainer_id = Integer.parseInt(request.getParameter("trainer_id"));
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE workouts SET name=?, difficulty=?, duration=?, description=?, trainer_id=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setString(2, difficulty);
            ps.setInt(3, duration);
            ps.setString(4, description);
            ps.setInt(5, trainer_id);
            ps.setInt(6, id);


            ps.executeUpdate();

            response.sendRedirect("workouts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}