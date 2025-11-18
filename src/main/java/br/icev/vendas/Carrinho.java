package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens;
    
    public Carrinho() {
        this.itens = new HashMap<>();
    }

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        itens.merge(produto, quantidade, Integer::sum);
    }

    public BigDecimal getSubtotal() {
        return itens.entrySet().stream()
                .map(entry -> {
                    BigDecimal preco = entry.getKey().getPrecoUnitario();
                    int quantidade = entry.getValue();
                    return preco.multiply(BigDecimal.valueOf(quantidade));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        if (politica == null) {
            return getSubtotal();
        }
        BigDecimal subtotal = getSubtotal();
        return politica.aplicar(subtotal);
    }

    public int getTotalItens() {
        return itens.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
    
    public Map<String, Integer> getItensPorCodigo() {
        Map<String, Integer> itensPorCodigo = new HashMap<>();
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            itensPorCodigo.put(entry.getKey().getCodigo(), entry.getValue());
        }
        return itensPorCodigo;
    }
}