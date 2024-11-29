package controller;

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

/**
 * Servlet implementation class CadUser
 */
@WebServlet("/CadUserServlet")
public class CadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Configurar o tipo de resposta como JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Obter parâmetros do formulário
        String nome = request.getParameter("nome");
        String dataNascimento = request.getParameter("data_nascimento");
        String cpf = request.getParameter("cpf");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        String numeroLocal = request.getParameter("numero_local");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String telefone = request.getParameter("telefone");
        String status = request.getParameter("status");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nivelUsuario = request.getParameter("nivel_usuario");

        try (Connection connection = new DBConnection().getConnection()) {
            // Query para inserir o novo usuário
            String sql = "INSERT INTO usuario (nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, estado, telefone, status, email, senha, nivel_usuario) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, cpf);
            stmt.setString(4, cep);
            stmt.setString(5, rua);
            stmt.setString(6, numeroLocal);
            stmt.setString(7, bairro);
            stmt.setString(8, cidade);
            stmt.setString(9, estado);
            stmt.setString(10, telefone);
            stmt.setString(11, status);
            stmt.setString(12, email);
            stmt.setString(13, senha);
            stmt.setString(14, nivelUsuario);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                out.println("{\"status\":\"success\", \"message\":\"Usuário cadastrado com sucesso!\"}");
            } else {
                out.println("{\"status\":\"error\", \"message\":\"Falha ao cadastrar o usuário.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"status\":\"error\", \"message\":\"Erro interno no servidor.\"}");
        }
	}
}