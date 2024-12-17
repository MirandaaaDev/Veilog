package model;

public class Empresa {
    private int id;
    private String nome;
    private String cnpj;
    private String tipoPessoa; // Enum 'Física' ou 'Jurídica'
    private String cep;
    private String rua;
    private int numeroLocal;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private String email;
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    
    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }
    
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
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public static Empresa criarEmpresa(
            int id,
            String nome,
            String cnpj,
            String tipoPessoa,
            String cep,
            String rua,
            int numeroLocal,
            String bairro,
            String cidade,
            String uf,
            String telefone,
            String email
        ) {
            Empresa empresa = new Empresa();
            empresa.setId(id);
            empresa.setNome(nome);
            empresa.setTipoPessoa(tipoPessoa);
            empresa.setCnpj(cnpj);
            empresa.setCep(cep);
            empresa.setRua(rua);
            empresa.setNumeroLocal(numeroLocal);
            empresa.setBairro(bairro);
            empresa.setCidade(cidade);
            empresa.setUf(uf);
            empresa.setTelefone(telefone);
            empresa.setEmail(email);
            return empresa;
        }
    
    public String toJson() {
        return "{" +
            "\"id\":" + this.getId() + "," +
            "\"nome\":\"" + this.getNome() + "\"," +
            "\"cnpj\":\"" + this.getCnpj() + "\"," +
            "\"tipoPessoa\":\"" + this.getTipoPessoa() + "\"," +
            "\"cep\":\"" + this.getCep() + "\"," +
            "\"rua\":\"" + this.getRua() + "\"," +
            "\"numeroLocal\":" + this.getNumeroLocal() + "," +
            "\"bairro\":\"" + this.getBairro() + "\"," +
            "\"cidade\":\"" + this.getCidade() + "\"," +
            "\"uf\":\"" + this.getUf() + "\"," +
            "\"telefone\":\"" + this.getTelefone() + "\"," +
            "\"email\":\"" + this.getEmail() + "\"" +
            "}";
    }
}