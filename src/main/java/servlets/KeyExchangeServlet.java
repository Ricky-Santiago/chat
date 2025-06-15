package servlets;

import beans.UserSession;
import dh.DHKeyExchange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigInteger;

@WebServlet("/key-exchange")
public class KeyExchangeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");

        if (userSession == null) {
            userSession = new UserSession();
            session.setAttribute("userSession", userSession);
        }

        if (userSession.getDh() == null) {
            userSession.initDH();
        }

        String action = request.getParameter("action");

        if ("exchange".equals(action)) {
            // Obtener la clave pública del otro usuario
            String otherPublicKeyStr = request.getParameter("otherPublicKey");

            if (otherPublicKeyStr != null && !otherPublicKeyStr.isEmpty()) {
                BigInteger otherPublicKey = new BigInteger(otherPublicKeyStr);
                userSession.getDh().generateSharedKey(otherPublicKey);
                userSession.setKeyExchanged(true);
                userSession.addMessage("🔐 Clave compartida generada: " +
                        userSession.getDh().getSharedKey());
            }

            // Responder con la clave pública del usuario actual
            response.setContentType("text/plain");
            response.getWriter().write(userSession.getDh().getPublicKey().toString());
        }
    }

    // Para probar si el servlet está activo desde el navegador
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().write("✅ KeyExchangeServlet está funcionando correctamente.");
    }
}
