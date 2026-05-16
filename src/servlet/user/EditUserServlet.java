package servlet.user;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/edit-user")
@MultipartConfig
public class EditUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                out.println("<title>Edit User</title>");

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body>");


                out.println("<div class='container py-5'>");
                out.println("<div class='card shadow-sm p-4'>");

                out.println("<h2 class='mb-4 fw-bold'>Edit User</h2>");

                out.println("<form action='update-user' method='post' enctype='multipart/form-data'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input class='form-control shadow-sm mb-3' name='name' value='" + rs.getString("name") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' type='email' name='email' value='" + rs.getString("email") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' type='number' name='age' value='" + rs.getInt("age") + "' required>");
                out.println("<input class='form-control shadow-sm mb-3' name='fitness_goal' value='" + rs.getString("fitness_goal") + "' required>");
                out.println("<select class='form-select shadow-sm mb-4' name='membership_type'>");

                out.println("<option " + (rs.getString("membership_type").equals("Basic") ? "selected" : "") + ">Basic</option>");
                out.println("<option " + (rs.getString("membership_type").equals("Premium") ? "selected" : "") + ">Premium</option>");
                out.println("<option " + (rs.getString("membership_type").equals("VIP") ? "selected" : "") + ">VIP</option>");

                out.println("</select>");


                out.println("<button class='btn btn-primary shadow-sm'>Update User</button>");
                out.println("<a href='users' class='btn btn-secondary shadow-sm ms-2'>Back</a>");

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