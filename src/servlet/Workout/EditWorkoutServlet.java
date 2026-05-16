package servlet.Workout;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/edit-workout")
public class EditWorkoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM workouts WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Workout</title>");

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body>");

                out.println("<div class='container py-5'>");
                out.println("<div class='card shadow-sm p-4'>");

                out.println("<h2 class='mb-4 fw-bold'>Edit Workout</h2>");

                out.println("<form action='update-workout' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input class='form-control shadow-sm mb-3' name='name' value='" + rs.getString("name") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' name='difficulty' value='" + rs.getString("difficulty") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' type='number' name='duration' value='" + rs.getInt("duration") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' name='description' value='" + rs.getString("description") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' type='number' name='trainer_id' value='" + rs.getInt("trainer_id") + "' required>");

                out.println("<button class='btn btn-primary shadow-sm'>Update workout</button>");
                out.println("<a href='workouts' class='btn btn-secondary shadow-sm ms-2'>Back</a>");

                out.println("</form>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}