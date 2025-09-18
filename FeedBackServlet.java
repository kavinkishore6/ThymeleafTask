import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String book = request.getParameter("book");
        String feedback = request.getParameter("feedback");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            // Connect to DB (update username & password if needed)
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/feedback_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "root", "Kavin@6"
);



            // Insert query
            String sql = "INSERT INTO feedback (name, book_name, feedback) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, book);
            ps.setString(3, feedback);

            ps.executeUpdate();
            con.close();

            // Response
            out.println("feedback saved");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
