package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
    public enum Status { PAGO }
    
    private final Map<String, Integer> itensPorCodigo;
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private final Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) {
        if (itensPorCodigo == null) {
            throw new IllegalArgumentException("Itens não podem ser nulos");
        }
        if (totalPago == null) {
            throw new IllegalArgumentException("Total pago não pode ser nulo");
        }
        if (codigoAutorizacao == null || codigoAutorizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Código de autorização não pode ser nulo ou vazio");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        
        this.itensPorCodigo = Collections.unmodifiableMap(new HashMap<>(itensPorCodigo));
        this.totalPago = totalPago;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    public BigDecimal getTotalPago() { return totalPago; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public Status getStatus() { return status; }
    
    public int getQuantidadeItem(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("Código não pode ser nulo");
        }
        return itensPorCodigo.getOrDefault(codigo, 0);
    }
}