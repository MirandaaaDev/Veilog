package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private Integer idEmpresa;
    private Integer idVeiculo;
    private Integer idMotorista;
    private Integer idItensPedido;
    private BigDecimal pesoCarga;
    private BigDecimal pesoTotal;
    private String tipoOperacao; // 'entrada' ou 'saida'
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataPedido;
    private LocalDateTime prazoMaximo;
    private String status; // 'pendente', 'em andamento', etc.

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Integer getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Integer idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public BigDecimal getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(BigDecimal pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

