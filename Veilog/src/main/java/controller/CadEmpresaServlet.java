package controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empresa;
import model.EmpresaDAO;
import model.ResponseMessage;

@WebServlet("/CadEmpresaServlet")
public class CadEmpresaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        
        // Obtendo os parâmetros da requisição
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String tipoPessoa = request.getParameter("tipoPessoa");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        int numeroLocal = Integer.parseInt(request.getParameter("numeroLocal"));
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        
     // Criando o objeto Empresa usando o método estático
        Empresa empresa = Empresa.criarEmpresa(
            0, // ID será gerado pelo banco
            nome,
            cnpj,
            tipoPessoa,
            cep,
            rua,
            numeroLocal,
            bairro,
            cidade,
            uf,
            telefone,   
            email
        );

        try {
            EmpresaDAO empresaDAO = new EmpresaDAO();
            boolean sucesso = empresaDAO.save(empresa);

         
            Gson gson = new Gson();
            String jsonResponse;

            if (sucesso) {
                jsonResponse = gson.toJson(new ResponseMessage("success", "Empresa cadastrada com sucesso."));
            } else {
                jsonResponse = gson.toJson(new ResponseMessage("error", "Erro ao cadastrar empresa."));
            }

            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();  
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(new ResponseMessage("error", "Erro interno no servidor."));
            response.getWriter().write(jsonResponse);
        }
    }
}