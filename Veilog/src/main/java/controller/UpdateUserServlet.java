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
 * Servlet para atualizar os dados de um usuário
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String dataNascimento = request.getParameter("dataNascimento");
            String cpf = request.getParameter("cpf");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            int numeroLocal = Integer.parseInt(request.getParameter("numeroLocal"));
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String telefone = request.getParameter("telefone");
            String status = request.getParameter("status");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nivelUsuario = request.getParameter("nivelUsuario");

            try (Connection connection = new DBConnection().getConnection()) {
                String sql = "UPDATE usuario SET nome = ?, data_nascimento = ?, cpf = ?, cep = ?, rua = ?, numero_local = ?, bairro = ?, cidade = ?, estado = ?, telefone = ?, status = ?, email = ?, senha = ?, nivel_usuario = ? WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, nome);
                stmt.setString(2, dataNascimento);
                stmt.setString(3, cpf);
                stmt.setString(4, cep);
                stmt.setString(5, rua);
                stmt.setInt(6, numeroLocal);
                stmt.setString(7, bairro);
                stmt.setString(8, cidade);
                stmt.setString(9, estado);
                stmt.setString(10, telefone);
                stmt.setString(11, status);
                stmt.setString(12, email);
                stmt.setString(13, senha);
                stmt.setString(14, nivelUsuario);
                stmt.setInt(15, id);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    response.sendRedirect("listusers.jsp");
                } else {
                    out.println("<p>Erro ao atualizar os dados.</p>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Erro ao atualizar o usuário.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Erro ao processar os dados.</p>");
        }
    }
}
