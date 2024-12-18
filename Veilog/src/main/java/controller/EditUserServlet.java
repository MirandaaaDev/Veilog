package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuarioIdStr = request.getParameter("id");

        if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
            try {
                int usuarioId = Integer.parseInt(usuarioIdStr); 

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.buscarUsuarioPorId(usuarioId);

                if (usuario != null) {
                    // Configura o Gson com o formato de data yyyy-MM-dd
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd")
                            .create();

                    String jsonUsuario = gson.toJson(usuario);

                    response.setContentType("application/json");
                    response.getWriter().write(jsonUsuario);
                } else {   
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Usuário não encontrado\"}");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"ID inválido\"}");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"Erro ao buscar usuário: " + e.getMessage() + "\"}");
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID não fornecido\"}");
        }
    }
}
