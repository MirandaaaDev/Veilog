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

/**
 * Servlet para editar os detalhes de um usuário
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("id");
        
        if (userId != null) {
            try (Connection connection = new DBConnection().getConnection()) {
                String sql = "SELECT id, nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, estado, telefone, status, email, senha, nivel_usuario FROM usuario WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(userId));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Pega os dados do usuário para preencher o formulário de edição
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

                    // Exibir os dados na página de edição
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h2>Editar Usuário</h2>");
                    out.println("<form action='./UpdateUserServlet' method='POST'>");
                    out.println("<input type='hidden' name='id' value='" + userId + "'/>");
                    out.println("Nome: <input type='text' name='nome' value='" + nome + "'/><br>");
                    out.println("Data de Nascimento: <input type='text' name='dataNascimento' value='" + dataNascimento + "'/><br>");
                    out.println("CPF: <input type='text' name='cpf' value='" + cpf + "'/><br>");
                    out.println("CEP: <input type='text' name='cep' value='" + cep + "'/><br>");
                    out.println("Rua: <input type='text' name='rua' value='" + rua + "'/><br>");
                    out.println("Número Local: <input type='text' name='numeroLocal' value='" + numeroLocal + "'/><br>");
                    out.println("Bairro: <input type='text' name='bairro' value='" + bairro + "'/><br>");
                    out.println("Cidade: <input type='text' name='cidade' value='" + cidade + "'/><br>");
                    out.println("Estado: <input type='text' name='estado' value='" + estado + "'/><br>");
                    out.println("Telefone: <input type='text' name='telefone' value='" + telefone + "'/><br>");
                    out.println("Status: <input type='text' name='status' value='" + status + "'/><br>");
                    out.println("Email: <input type='text' name='email' value='" + email + "'/><br>");
                    out.println("Senha: <input type='password' name='senha' value='" + senha + "'/><br>");
                    out.println("Nível de Usuário: <input type='text' name='nivelUsuario' value='" + nivelUsuario + "'/><br>");
                    out.println("<button type='submit'>Salvar</button>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("<p>Usuário não encontrado.</p>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Erro ao buscar dados do usuário.</p>");
            }
        } else {
            out.println("<p>ID do usuário não fornecido.</p>");
        }
    }
}
