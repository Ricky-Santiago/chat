package servlets;

import beans.Message;
import storage.ChatStorage;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

@WebServlet("/chat-messages")
public class ChatMessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Map<String,Object> result = new HashMap<>();
        result.put("users", ChatStorage.getUsers());
        result.put("messages", ChatStorage.getMessages());
        resp.getWriter().write(new Gson().toJson(result));
    }
}