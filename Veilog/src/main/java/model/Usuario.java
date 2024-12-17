package model;

import java.util.Date;

public class Usuario {
    private int id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String cep;
    private String rua;
    private int numeroLocal;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private String status;
    private String email;
    private String senha;
    private String nivelUsuario;
	
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }
    
    public int getNumeroLocal() { return numeroLocal; }
    public void setNumeroLocal(int numeroLocal) { this.numeroLocal = numeroLocal; }
    
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getNivelUsuario() { return nivelUsuario; }
    public void setNivelUsuario(String nivelUsuario) { this.nivelUsuario = nivelUsuario; }
    
    public static Usuario criarUsuario(
            int id,
            String nome,
            Date dataNascimento,
            String cpf,
            String cep,
            String rua,
            int numeroLocal,
            String bairro,
            String cidade,
            String uf,
            String telefone,
            String status,
            String email,
            String senha,
            String nivelUsuario
        ) {
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setDataNascimento(dataNascimento);
            usuario.setCpf(cpf);
            usuario.setCep(cep);
            usuario.setRua(rua);
            usuario.setNumeroLocal(numeroLocal);
            usuario.setBairro(bairro);
            usuario.setCidade(cidade);
            usuario.setUf(uf);
            usuario.setTelefone(telefone);
            usuario.setStatus(status);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setNivelUsuario(nivelUsuario);
            return usuario;
        }
    
    public String toJson() {
    	return "{" +
    	        "\"id\":" + this.getId() + "," +
    	        "\"nome\":\"" + this.getNome() + "\"," +
    	        "\"cpf\":\"" + this.getCpf() + "\"," +
    	        "\"dataNascimento\":\"" + getDataNascimento() + "\"," +
    	        "\"cep\":\"" + this.getCep() + "\"," +
    	        "\"rua\":\"" + this.getRua() + "\"," +
    	        "\"numeroLocal\":" + this.getNumeroLocal() + "," +
    	        "\"bairro\":\"" + this.getBairro() + "\"," +
    	        "\"cidade\":\"" + this.getCidade() + "\"," +
    	        "\"uf\":\"" + this.getUf() + "\"," +
    	        "\"telefone\":\"" + this.getTelefone() + "\"," +
    	        "\"email\":\"" + this.getEmail() + "\"," +
    	        "\"status\":\"" + this.getStatus() + "\"," +
    	        "\"senha\":\"" + this.getSenha() + "\"," +
    	        "\"nivelUsuario\":\"" + this.getNivelUsuario() + "\"" +
    	    "}";
    }
}


