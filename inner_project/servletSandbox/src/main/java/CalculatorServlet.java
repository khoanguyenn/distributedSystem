import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberA = Integer.parseInt(request.getParameter("a"));
        int numberB = Integer.parseInt(request.getParameter("b"));
        String operator = request.getParameter("operator");
        PrintWriter printWriter = response.getWriter();
        switch(operator) {
            case "+":
                printWriter.println(numberA + numberB);
                break;
            case "-":
                printWriter.println(numberA - numberB);
                break;
            case "*":
                printWriter.println(numberA * numberB);
                break;
            case "/":
                printWriter.println(numberA / numberB);
                break;
        }
    }
}
