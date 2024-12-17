package controller;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UsuarioDAO;
import repository.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Obter o nível do usuário
        String nivelUsuario = usuarioDAO.autenticar(email, senha);

        if (nivelUsuario != null) {
            // Criar sessão e salvar atributos
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("nivelUsuario", nivelUsuario);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{\"status\":\"success\", \"nivelUsuario\":\"" + nivelUsuario + "\", \"email\":\"" + email + "\"}");
        } else {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{\"status\":\"error\", \"message\":\"Email ou senha inválidos.\"}");
        }
    }
}