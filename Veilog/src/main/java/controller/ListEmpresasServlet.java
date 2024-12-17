// Atualizando ListEmpresasServlet.java
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empresa;
import model.EmpresaDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ListEmpresasServlet")
public class ListEmpresasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            EmpresaDAO empresaDAO = new EmpresaDAO();
            List<Empresa> empresas = empresaDAO.buscarEmpresas();

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < empresas.size(); i++) {
                json.append(empresas.get(i).toJson());
                if (i < empresas.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            out.println(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"status\":\"error\", \"message\":\"Erro ao recuperar dados.\"}");
        }
    }
    
}
