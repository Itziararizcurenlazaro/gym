package servlet.Trainer;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/detail-trainer")
public class DetailTrainerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM trainers WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Trainers Details</title>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container py-5'>");
                out.println("<div class='card shadow-sm p-4'>");
                out.println("<h2 class='mb-4 fw-bold'>Trainers Details</h2>");

                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Name:</b> " + rs.getString("name") + "</p>");
                out.println("<p><b>Speciality:</b> " + rs.getString("speciality") + "</p>");
                out.println("<p><b>Experience years:</b> " + rs.getString("experience_years") + "</p>");
                out.println("<p><b>Email:</b> " + rs.getString("email") + "</p>");
                out.println("<p><b>Phone:</b> " + rs.getString("phone") + "</p>");
                out.println("<div class='mt-4'>");

                out.println("<a href='edit-trainer?id=" + rs.getInt("id") + "' class='btn btn-warning shadow-sm' onclick=\"return confirm('Are you sure you want to edit this trainer?')\">Edit</a> ");
                out.println("<a href='delete-trainer?id=" + rs.getInt("id") + "' class='btn btn-danger shadow-sm' onclick=\"return confirm('Are you sure you want to delete this trainer?')\">Delete</a>");

                out.println("<a href='trainers' class='btn btn-secondary shadow-sm ms-2'>Back</a>");

                out.println("</div>");
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