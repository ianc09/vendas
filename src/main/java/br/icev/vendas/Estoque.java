package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;
import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Integer> estoque;
    private final Map<String, Integer> reservas;
    
    public Estoque() {
        this.estoque = new HashMap<>();
        this.reservas = new HashMap<>();
    }

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        estoque.merge(codigo, quantidade, Integer::sum);
    }

    public int getDisponivel(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        
        int estoqueAtual = estoque.getOrDefault(codigo, 0);
        int reservado = reservas.getOrDefault(codigo, 0);
        return estoqueAtual - reservado;
    }

    public void reservar(String codigo, int quantidade)
            throws SemEstoqueException, QuantidadeInvalidaException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        int disponivel = getDisponivel(codigo);
        if (disponivel < quantidade) {
            throw new SemEstoqueException("Estoque insuficiente para o produto: " + codigo);
        }
        
        reservas.merge(codigo, quantidade, Integer::sum);
    }
    
    public void liberarReserva(String codigo, int quantidade) {
        if (codigo != null && reservas.containsKey(codigo)) {
            int reservaAtual = reservas.get(codigo);
            int novaReserva = reservaAtual - quantidade;
            if (novaReserva <= 0) {
                reservas.remove(codigo);
            } else {
                reservas.put(codigo, novaReserva);
            }
        }
    }
    
    public void confirmarReserva(String codigo, int quantidade) {
        if (codigo != null && estoque.containsKey(codigo)) {
            int estoqueAtual = estoque.get(codigo);
            estoque.put(codigo, estoqueAtual - quantidade);
            liberarReserva(codigo, quantidade);
        }
    }
}