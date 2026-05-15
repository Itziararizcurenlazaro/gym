package servlet.Trainer;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/edit-trainer")
public class EditTrainerServlet extends HttpServlet {

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
                out.println("<title>Edit Trainer</title>");

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/vapor/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body>");


                out.println("<div class='container py-5'>");
                out.println("<div class='card shadow-sm p-4'>");

                out.println("<h2 class='mb-4 fw-bold'>Edit Trainer</h2>");

                out.println("<form action='update-trainer' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input class='form-control shadow-sm mb-3' name='name' value='" + rs.getString("name") + "'>");
                out.println("<input class='form-control shadow-sm mb-3' name='speciality' value='" + rs.getString("speciality") + "'>");
                out.println("<input class='form-control shadow-sm mb-3' name='experience_years' value='" + rs.getInt("experience_years") + "'>");
                out.println("<input class='form-control shadow-sm mb-3' name='email' value='" + rs.getString("email") + "'>");
                out.println("<input class='form-control shadow-sm mb-3' name='phone' value='" + rs.getDouble("phone") + "'>");

                out.println("<button class='btn btn-primary shadow-sm'>Edit Trainer</button>");
                out.println("<a href='trainers' class='btn btn-secondary shadow-sm ms-2'>Back</a>");

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