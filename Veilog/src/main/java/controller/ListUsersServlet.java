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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ListUsersServlet
 */
@WebServlet("/ListUsersServlet")
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        List<String> users = new ArrayList<>();
        
        try (Connection connection = new DBConnection().getConnection()) {
            String sql = "SELECT id, nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, estado, telefone, status, email, senha, nivel_usuario FROM usuario";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                String cpf = rs.getString("cpf");
                String cep = rs.getString("cep");
                String rua = rs.getString("rua");
                int numeroLocal = rs.getInt("numero_local");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String telefone = rs.getString("telefone");
                String status = rs.getString("status");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String nivelUsuario = rs.getString("nivel_usuario");
                
                // Montando o JSON para cada usuário
                users.add("{\"id\":\"" + id + "\", " +
                          "\"nome\":\"" + nome + "\", " +
                          "\"dataNascimento\":\"" + dataNascimento + "\", " +
                          "\"cpf\":\"" + cpf + "\", " +
                          "\"cep\":\"" + cep + "\", " +
                          "\"rua\":\"" + rua + "\", " +
                          "\"numeroLocal\":\"" + numeroLocal + "\", " +
                          "\"bairro\":\"" + bairro + "\", " +
                          "\"cidade\":\"" + cidade + "\", " +
                          "\"estado\":\"" + estado + "\", " +
                          "\"telefone\":\"" + telefone + "\", " +
                          "\"status\":\"" + status + "\", " +
                          "\"email\":\"" + email + "\", " +
                          "\"senha\":\"" + senha + "\", " +
                          "\"nivelUsuario\":\"" + nivelUsuario + "\"}");
            }
            
            if (!users.isEmpty()) {
                out.println("[ " + String.join(",", users) + " ]");
            } else {
                out.println("[]");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"status\":\"error\", \"message\":\"Erro ao recuperar dados.\"}");
        }
    }
}