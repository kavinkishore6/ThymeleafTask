import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        
        String name = request.getParameter("name");
        String book = request.getParameter("book");
        String feedback = request.getParameter("feedback");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");


           
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/feedback_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "root", "Kavin@6"
);



          
            String sql = "INSERT INTO feedback (name, book_name, feedback) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, book);
            ps.setString(3, feedback);

            ps.executeUpdate();
            con.close();

            
            out.println("feedback saved");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
