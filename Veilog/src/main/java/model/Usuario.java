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
    private String estado;
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
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    
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
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
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
}
