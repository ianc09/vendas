package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida");
        }
        
        itens.merge(produto, quantidade, Integer::sum);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = itens.entrySet().stream()
                .map(entry -> {
                    BigDecimal preco = entry.getKey().getPrecoUnitario();
                    int quantidade = entry.getValue();
                    return preco.multiply(BigDecimal.valueOf(quantidade));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return UtilDinheiro.arredondar2(subtotal);
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        if (politica == null) {
            return getSubtotal();
        }
        
        BigDecimal subtotal = getSubtotal();
        BigDecimal totalComDesconto = politica.aplicar(subtotal);
        
        if (totalComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        
        return UtilDinheiro.arredondar2(totalComDesconto);
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