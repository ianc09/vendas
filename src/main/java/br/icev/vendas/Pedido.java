package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class Pedido {
    public enum Status { PAGO }
    
    private final Map<String, Integer> itensPorCodigo;
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private final Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) {
        if (itensPorCodigo == null || itensPorCodigo.isEmpty()) {
            throw new IllegalArgumentException("Itens não podem ser nulos ou vazios");
        }
        if (totalPago == null || totalPago.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total pago deve ser positivo");
        }
        if (codigoAutorizacao == null || codigoAutorizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Código de autorização não pode ser nulo ou vazio");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        
        this.itensPorCodigo = Collections.unmodifiableMap(itensPorCodigo);
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
    
    public Map<String, Integer> getItensPorCodigo() {
        return itensPorCodigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(codigoAutorizacao, pedido.codigoAutorizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoAutorizacao);
    }
}