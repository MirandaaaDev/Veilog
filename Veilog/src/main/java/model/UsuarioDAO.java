package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import repository.DBConnection;

public class UsuarioDAO {
	public String autenticar(String email, String senha) {
	    String sql = "SELECT nivel_usuario FROM usuario WHERE email = ? AND senha = ?";
	    
	    try (Connection connection = new DBConnection().getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {
	        
	        stmt.setString(1, email);
	        stmt.setString(2, senha);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Retorna o valor do nível do usuário
	            return rs.getString("nivel_usuario");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    // Retorna null se não encontrar o usuário ou ocorrer um erro
	    return null;
	}

    public String obterNome(String email) {
        String sql = "SELECT nome FROM usuario WHERE email = ?";
        
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nome");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, uf, telefone, status, email, senha, nivel_usuario) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getCep());
            stmt.setString(5, usuario.getRua());
            stmt.setInt(6, usuario.getNumeroLocal());
            stmt.setString(7, usuario.getBairro());
            stmt.setString(8, usuario.getCidade());
            stmt.setString(9, usuario.getUf());
            stmt.setString(10, usuario.getTelefone());
            stmt.setString(11, usuario.getStatus());
            stmt.setString(12, usuario.getEmail());
            stmt.setString(13, usuario.getSenha());
            stmt.setString(14, usuario.getNivelUsuario());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Usuario> buscarUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {
            String sql = "SELECT id, nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, uf, telefone, status, email, senha, nivel_usuario FROM usuario";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = Usuario.criarUsuario(
                		rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getInt("numero_local"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("telefone"),
                        rs.getString("status"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("nivel_usuario")  
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    
    
    
    public Usuario buscarUsuarioPorId(int id) throws Exception {
        // Definir a consulta SQL para buscar o usuário pelo ID
        String sql = "SELECT id, nome, data_nascimento, cpf, cep, rua, numero_local, bairro, cidade, uf, telefone, status, email, senha, nivel_usuario FROM usuario WHERE id = ?";
        
        // Usando try-with-resources para garantir o fechamento de recursos como Connection e PreparedStatement
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            // Definir o valor do ID no PreparedStatement
            stmt.setInt(1, id);
            
            // Executar a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                
                // Verificar se o usuário foi encontrado
                if (rs.next()) {
                    // Criar o objeto Usuario com os dados recuperados
                    return Usuario.criarUsuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getInt("numero_local"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("telefone"),
                        rs.getString("status"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("nivel_usuario")
                    );
                } else {
                    return null;  // Caso o usuário não seja encontrado
                }
            }
        }
    }
  
    public boolean updateUsuario(Usuario usuario) {
    	String sql = "UPDATE usuario SET nome = ?, data_nascimento = ?, cpf = ?, cep = ?, rua = ?, numero_local = ?, bairro = ?, cidade = ?, uf = ?, telefone = ?, status = ?, email = ?, senha = ?, nivel_usuario = ? WHERE id = ?";
        
        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getCep());
            stmt.setString(5, usuario.getRua());
            stmt.setInt(6, usuario.getNumeroLocal());
            stmt.setString(7, usuario.getBairro());
            stmt.setString(8, usuario.getCidade());
            stmt.setString(9, usuario.getUf());
            stmt.setString(10, usuario.getTelefone());
            stmt.setString(11, usuario.getStatus());
            stmt.setString(12, usuario.getEmail());
            stmt.setString(13, usuario.getSenha());
            stmt.setString(14, usuario.getNivelUsuario());
            stmt.setInt(15, usuario.getId());

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}