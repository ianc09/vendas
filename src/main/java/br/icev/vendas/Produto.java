package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final String codigo;
    private final String nome;
    private final BigDecimal precoUnitario;

    public Produto(String codigo, String nome, BigDecimal precoUnitario) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (precoUnitario == null) {
            throw new NullPointerException("Preço unitário não pode ser nulo");
        }
        if (precoUnitario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}