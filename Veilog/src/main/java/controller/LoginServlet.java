package controller;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        try (Connection connection = new DBConnection().getConnection()) {
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.setContentType("application/json");
                response.getWriter().println("{\"status\":\"success\", \"nome\":\"" + rs.getString("nome") + "\", \"email\":\"" + email + "\"}");
            } else {
                response.getWriter().println("{\"status\":\"error\", \"message\":\"Email ou senha inválidos.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().println("{\"status\":\"error\", \"message\":\"Erro interno no servidor.\"}");
        }
	}
}
