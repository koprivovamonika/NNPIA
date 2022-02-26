package cz.upce.fei.nnpia.cv02.nnpia_cv02_tomcat;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello! Log in please.";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<form method=\"post\">"
                + "<input type=\"text\" id=\"username\" name=\"username\" placeholder=\"Username\"><br>"
                + "<input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\"><br>"
                + "<input type=\"submit\">"
                + "</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String html = "<html>";
        html += "<h2>Hello " + req.getParameter("username") + "</h2>";
        html += "</html>";

        out.println(html);
    }

    public void destroy() {
    }
}