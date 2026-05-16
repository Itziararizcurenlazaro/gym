package servlet.Workout;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/workouts")
public class ListWorkoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.getConnection();
            String name = request.getParameter("name");

            String sql = "SELECT * FROM workouts WHERE 1=1";

            if (name != null && !name.isEmpty()) {
                sql += " AND name LIKE '%" + name + "%'";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/morph/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<nav class='navbar navbar-light bg-light shadow-sm'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand fw-bold' href='index.html'>Gym</a>");
            out.println("</div>");
            out.println("</nav>");

            out.println("<div class='container py-5'>");
            out.println("<a href='index.html' class='btn btn-outline-secondary shadow-sm mb-3'>← Home</a>");

            out.println("<h2 class='mb-4 fw-bold'>Workout List</h2>");
            out.println("<form method='get' action='workouts' class='mb-4'>");

            out.println("<div class='row'>");

            out.println("<div class='col-md-3'><input name='name' class='form-control' placeholder='Name'></div>");

            out.println("</div>");

            out.println("<div class='mt-3'>");
            out.println("<button class='btn btn-primary shadow-sm'>Search</button>");
            out.println("<a href='workouts' class='btn btn-secondary shadow-sm ms-2'>Clear</a>");
            out.println("</div>");

            out.println("</form>");

            out.println("<a href='form-workout.html' class='btn btn-success shadow-sm mb-3'>Add Workout</a>");

            out.println("<table class='table table-hover align-middle shadow-sm'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Difficulty</th>");
            out.println("<th>Duration</th>");
            out.println("<th>Description</th>");
            out.println("<th>Trainer ID</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("difficulty") + "</td>");
                out.println("<td>" + rs.getInt("duration") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("<td>" + rs.getInt("trainer_id") + "</td>");

                out.println("<td>");
                out.println("<a href='workout-detail?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm shadow-sm'>View</a> ");
                out.println("<a href='edit-workout?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm shadow-sm' onclick=\"return confirm('Are you sure you want to edit this workout?')\">Edit</a> ");
                out.println("<a href='delete-workout?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm shadow-sm' onclick=\"return confirm('Are you sure you want to delete this workout?')\">Delete</a>");

                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}