package controller;

import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;
import model.ResponseMessage;

@WebServlet("/CadUserServlet")
public class CadUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");

        // Obtendo os parâmetros da requisição
        String nome = request.getParameter("nome");
        String dataNascimento = request.getParameter("dataNascimento");
        String cpf = request.getParameter("cpf");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        int numeroLocal = Integer.parseInt(request.getParameter("numeroLocal"));
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nivelUsuario = request.getParameter("nivelUsuario");
        String status = "ativo"; // valor fixo para status

        // Criando o usuário
        Usuario usuario = Usuario.criarUsuario(
            0, 
            nome,
            java.sql.Date.valueOf(dataNascimento),
            cpf,
            cep,
            rua,
            numeroLocal,
            bairro,
            cidade,
            uf,
            telefone,
            status,
            email,
            senha,
            nivelUsuario
        );

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean sucesso = usuarioDAO.save(usuario);

            // Criando a resposta JSON com Gson
            Gson gson = new Gson();
            String jsonResponse;

            if (sucesso) {
                jsonResponse = gson.toJson(new ResponseMessage("success", "Usuário cadastrado com sucesso."));
            } else {
                jsonResponse = gson.toJson(new ResponseMessage("error", "Erro ao cadastrar usuário."));
            }

            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();  // Adiciona o stack trace para ajudar no diagnóstico
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(new ResponseMessage("error", "Erro interno no servidor."));
            response.getWriter().write(jsonResponse);
        }
    }
}
