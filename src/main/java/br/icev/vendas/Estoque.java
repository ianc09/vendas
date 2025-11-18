package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;
import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Integer> estoque = new HashMap<>();

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        estoque.merge(codigo, quantidade, Integer::sum);
    }

    public int getDisponivel(String codigo) {
        return estoque.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade) throws SemEstoqueException, QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        int disponivel = getDisponivel(codigo);
        if (disponivel < quantidade) {
            throw new SemEstoqueException("Estoque insuficiente para " + codigo);
        }
        
        estoque.put(codigo, disponivel - quantidade);
    }
}