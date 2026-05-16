package servlet.user;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/users")
public class ListUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {

            Connection con = DBConnection.getConnection();
            String name = request.getParameter("name");
            String membership_type = request.getParameter("membership_type");
            String email = request.getParameter("email");

            String sql = "SELECT * FROM users WHERE 1=1";

            if (name != null && !name.isEmpty()) {
                sql += " AND name LIKE '%" + name + "%'";
            }

            if (membership_type != null && !membership_type.isEmpty()) {
                sql += " AND membership_type LIKE '%" + membership_type + "%'";
            }

            if (email != null && !email.isEmpty()) {
                sql += " AND email LIKE '%" + email + "%'";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"icon\" href=\"img/favicon.ico\" type=\"image/x-icon\">");
            out.println("<title>Oh My GYM! - Fitness that hits different</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-light shadow-sm\">\n" +
                    "    <div class=\"container-fluid\">\n" +
                    "        <span class=\"navbar-brand fw-bold\"><img src=\"img/logo.png\" alt=\"OMG Logo\" height=\"50\">OH MY GYM!</span>\n" +
                    "        <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "            <ul class=\"navbar-nav ms-3\">\n" +
                    "                <li class=\"nav-item\">\n" +
                    "                    <a href=\"users\" class=\"nav-link\">Users</a>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\">\n" +
                    "                    <a href=\"trainers\" class=\"nav-link\">Trainers</a>\n" +
                    "                </li>\n" +
                    "                <li class=\"nav-item\">\n" +
                    "                    <a href=\"workouts\" class=\"nav-link\">Workouts</a>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</nav>");


            out.println("<div class='container py-5 text-center'>");
            out.println("<a href='index.html' class='btn btn-outline-secondary shadow-sm mb-3'>← Home</a>");

            out.println("<h2 class='mb-4 fw-bold'>Users List</h2>");
            out.println("<form method='get' action='users' class='mb-4'>");

            out.println("<div class='row justify-content-center'>");

            out.println("<div class='col-md-4'><input name='name' class='form-control shadow-sm' placeholder='Name'></div>");
            out.println("</div>");

            out.println("<div class='mt-3'>");
            out.println("<button class='btn btn-primary shadow-sm'>Search</button>");
            out.println("<a href='users' class='btn btn-secondary shadow-sm ms-2'>Clear</a>");
            out.println("</div>");

            out.println("</form>");

            out.println("<a href='form-user.html' class='btn btn-success shadow-sm mb-3'>Add User</a>");


            out.println("<table class='table table-hover align-middle shadow-sm'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Age</th>");
            out.println("<th>Fitness Goal</th>");
            out.println("<th>Membership Type</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("fitness_goal") + "</td>");
                out.println("<td>" + rs.getString("membership_type") + "</td>");

                out.println("<td>");
                    out.println("<a href='user-detail?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm shadow-sm'>View</a> ");
                    out.println("<a href='edit-user?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm shadow-sm' onclick=\"return confirm('Are you sure you want to edit this user?')\">Edit</a> ");
                    out.println("<a href='delete-user?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm shadow-sm' onclick=\"return confirm('Are you sure you want to delete this user?')\">Delete</a>");

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