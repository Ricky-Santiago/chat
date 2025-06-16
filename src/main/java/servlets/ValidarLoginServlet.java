package servlets;

import dao.UsuarioJpaController;
import dto.Usuario;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ValidarLoginServlet", urlPatterns = {"/validarLogin"})
public class ValidarLoginServlet extends HttpServlet {

    private EntityManagerFactory emf;
    private UsuarioJpaController usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory("com.mycompany_chat_war_1.0-SNAPSHOTPU"); // Usa el mismo nombre de tu persistence.xml
        usuarioDAO = new UsuarioJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logi = request.getParameter("logi");
        String pass = request.getParameter("pass");

        Usuario usuario = usuarioDAO.validarUsuario(logi, pass);

        if (usuario != null) {
            // Login exitoso
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("tables.html"); // <-- REDIRECCIÓN A tables.html
        } else {
            // Login fallido
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        super.destroy();
    }
}
