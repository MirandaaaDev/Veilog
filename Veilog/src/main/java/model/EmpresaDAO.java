package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import repository.DBConnection;

public class EmpresaDAO {

    public boolean save(Empresa empresa) throws Exception {
    	  String sql = "INSERT INTO empresa (nome, cnpj, tipo_pessoa, cep, rua, numero_local, bairro, cidade, uf, telefone, email) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCnpj());
            stmt.setString(3, empresa.getTipoPessoa());
            stmt.setString(4, empresa.getCep());
            stmt.setString(5, empresa.getRua());
            stmt.setInt(6, empresa.getNumeroLocal());
            stmt.setString(7, empresa.getBairro());
            stmt.setString(8, empresa.getCidade());
            stmt.setString(9, empresa.getUf());
            stmt.setString(10, empresa.getTelefone());
            stmt.setString(11, empresa.getEmail());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Empresa> buscarEmpresas() throws Exception {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {
            String sql = "SELECT id, nome, cnpj, tipo_pessoa, cep, rua, numero_local, bairro, cidade, uf, telefone, email FROM empresa";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Empresa empresa = Empresa.criarEmpresa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("tipo_pessoa"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getInt("numero_local"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                empresas.add(empresa);
            }
        }
        return empresas;
    }
    
    public Empresa buscarEmpresaPorId(int id) throws Exception {
        String sql = "SELECT id, nome, cnpj, tipo_pessoa, cep, rua, numero_local, bairro, cidade, uf, telefone, email FROM empresa WHERE id = ?";
        
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Empresa.criarEmpresa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("tipo_pessoa"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getInt("numero_local"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("telefone"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null; // Retorna null se a empresa não for encontrada
    }

    
    public boolean updateEmpresa(Empresa empresa) {
        String sql = "UPDATE empresa SET nome = ?, cnpj = ?, tipo_pessoa = ?, cep = ?, rua = ?, numero_local = ?, bairro = ?, cidade = ?, uf = ?, telefone = ?, email = ? WHERE id = ?";
        
        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCnpj());
            stmt.setString(3, empresa.getTipoPessoa());
            stmt.setString(4, empresa.getCep());
            stmt.setString(5, empresa.getRua());
            stmt.setInt(6, empresa.getNumeroLocal());
            stmt.setString(7, empresa.getBairro());
            stmt.setString(8, empresa.getCidade());
            stmt.setString(9, empresa.getUf());
            stmt.setString(10, empresa.getTelefone());
            stmt.setString(11, empresa.getEmail());
            stmt.setInt(12, empresa.getId());

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}