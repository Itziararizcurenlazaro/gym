package servlet.Trainer;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/trainers")
public class ListTrainerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {

            Connection con = DBConnection.getConnection();
            String name = request.getParameter("name");
            String speciality = request.getParameter("speciality");
            String experience_years = request.getParameter("experience_years");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            String sql = "SELECT * FROM trainers WHERE 1=1";

            if (name != null && !name.isEmpty()) {
                sql += " AND name LIKE '%" + name + "%'";
            }

            if (speciality != null && !speciality.isEmpty()) {
                sql += " AND speciality LIKE '%" + speciality + "%'";
            }

            if (experience_years != null && !experience_years.isEmpty()) {
                sql += " AND experience_years LIKE '%" + experience_years + "%'";
            }

            if (email != null && !email.isEmpty()) {
                sql += " AND email LIKE '%" + email + "%'";
            }

            if (phone != null && !phone.isEmpty()) {
                sql += " AND phone LIKE '%" + phone + "%'";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<nav class='navbar navbar-light bg-light shadow-sm'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand fw-hold' href='index.html'>Gym</a>");
            out.println("</div>");
            out.println("</nav>");

            out.println("<div class='container py-5'>");
            out.println("<a href='index.html' class='btn btn-outline-secondary shadow-sm mb-3'>← Home</a>");

            out.println("<h2 class='mb-4 fw-bold'>Trainers List</h2>");
            out.println("<form method='get' action='trainers' class='mb-4'>");

            out.println("<div class='row'>");

            out.println("<div class='col-md-4'><input name='name' class='form-control' placeholder='Name'></div>");


            out.println("</div>");

            out.println("<div class='mt-3'>");
            out.println("<button class='btn btn-primary shadow-sm'>Search</button>");
            out.println("<a href='trainers' class='btn btn-secondary shadow-sm ms-2'>Clean</a>");
            out.println("</div>");

            out.println("</form>");


            out.println("<a href='form-trainer.html' class='btn btn-success shadow-sm mb-3'>Add Trainer</a>");

            out.println("<table class='table table-hover align-middle shadow-sm'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Speciality</th>");
            out.println("<th>Experience years</th>");
            out.println("<th>Email</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("speciality") + "</td>");
                out.println("<td>" + rs.getString("experience_years") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");

                out.println("<td>");
                out.println("<a href='detail-trainer?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm'>View</a> ");

                out.println("<a href='edit-trainer?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm' onclick=\"return confirm('Are you sure you want to edit this trainer?')\">Edit</a> ");
                out.println("<a href='delete-trainer?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm' onclick=\"return confirm('Are you sure you want to delete this trainer?\\n\\ \\n\\WARNING: If deleted, all associated workouts will also be permanently removed.')\">Delete</a>");

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