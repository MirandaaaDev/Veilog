package model;

public class Veiculo {
    private int id;
    private String modelo;
    private String marca;
    private String placa;
    private String categoria;
    private int anoDeFabricacao; // Representa o ano de fabricação como um inteiro
    private String tag;
    private double pesoVeiculo;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getPesoVeiculo() {
        return pesoVeiculo;
    }

    public void setPesoVeiculo(double pesoVeiculo) {
        this.pesoVeiculo = pesoVeiculo;
    }
}
