package servlets;

import beans.UserSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) return;

        UserSession userSession = (UserSession) session.getAttribute("userSession");
        if (userSession == null) return;

        String message = request.getParameter("message");
        if (message != null && !message.trim().isEmpty()) {
            userSession.addMessage(userSession.getUsername() + ": " + message);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
