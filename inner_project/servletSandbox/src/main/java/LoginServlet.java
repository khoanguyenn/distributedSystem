import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("username") + request.getParameter("password"));
        if (request.getParameter("username").equals("nhu") && request.getParameter("password").equals("khoa")) {
            response.setContentType("text/html");
            request.setAttribute();
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<h1>Login successfully!</h1>");
        }
    }
}
