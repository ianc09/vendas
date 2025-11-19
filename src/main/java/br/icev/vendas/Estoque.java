package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;
import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Integer> estoque = new HashMap<>();

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida");
        }
        
        estoque.merge(codigo, quantidade, Integer::sum);
    }

    public int getDisponivel(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        return estoque.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade) 
            throws SemEstoqueException, QuantidadeInvalidaException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida");
        }
        
        int disponivel = getDisponivel(codigo);
        if (disponivel < quantidade) {
            throw new SemEstoqueException("Sem estoque suficiente");
        }
        
        estoque.put(codigo, disponivel - quantidade);
    }

    public void confirmarReserva(String codigo, int quantidade) {
        throw new UnsupportedOperationException("Unimplemented method 'confirmarReserva'");
    }

    public void liberarReserva(String codigo, int quantidade) {
        throw new UnsupportedOperationException("Unimplemented method 'liberarReserva'");
    }
}