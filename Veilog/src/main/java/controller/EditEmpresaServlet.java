package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empresa;
import model.EmpresaDAO;

@WebServlet("/EditEmpresaServlet")
public class EditEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empresaIdStr = request.getParameter("id");

        if (empresaIdStr != null && !empresaIdStr.isEmpty()) {
            try {
                int empresaId = Integer.parseInt(empresaIdStr); 

                EmpresaDAO empresaDAO = new EmpresaDAO();
                Empresa empresa = empresaDAO.buscarEmpresaPorId(empresaId);

                if (empresa != null) {
                    // Configura o Gson com o formato de data yyyy-MM-dd
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd")
                            .create();

                    String jsonEmpresa = gson.toJson(empresa);

                    response.setContentType("application/json");
                    response.getWriter().write(jsonEmpresa);
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
