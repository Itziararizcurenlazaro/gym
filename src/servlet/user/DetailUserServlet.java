package servlet.user;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/user-detail")
public class DetailUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

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
                        "        <span class=\"navbar-brand fw-bold\"><img src=\"img/logo.png\" alt=\"OMG Logo\" weight=\"50\" height=\"50\">OH MY GYM!</span>\n" +
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
                out.println("<div class='card shadow-sm p-4'>");
                out.println("<h2 class='mb-4 fw-bold'>User Details</h2>");

                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Name:</b> " + rs.getString("name") + "</p>");
                out.println("<p><b>Email:</b> " + rs.getString("email") + "</p>");
                out.println("<p><b>Age:</b> " + rs.getInt("age") + "</p>");
                out.println("<p><b>Fitness goal:</b> " + rs.getString("fitness_goal") + "</p>");
                out.println("<p><b>Membership Type:</b> " + rs.getString("membership_type") + "</p>");
                out.println("<div class='mt-4'>");

                out.println("<a href='edit-user?id=" + rs.getInt("id") + "' class='btn btn-warning shadow-sm' onclick=\"return confirm('Are you sure you want to edit this user?')\">Edit</a> ");
                out.println("<a href='delete-user?id=" + rs.getInt("id") + "' class='btn btn-danger shadow-sm' onclick=\"return confirm('Are you sure you want to delete this user?')\">Delete</a>");


                out.println("<a href='users' class='btn btn-secondary shadow-sm ms-2'>Back</a>");

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
