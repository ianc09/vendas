package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        
        itens.merge(produto, quantidade, Integer::sum);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            BigDecimal precoTotal = produto.getPrecoUnitario().multiply(new BigDecimal(quantidade));
            subtotal = subtotal.add(precoTotal);
        }
        return UtilDinheiro.arredondar2(subtotal);
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        BigDecimal subtotal = getSubtotal();
        BigDecimal totalComDesconto = politica.aplicar(subtotal);
        
        if (totalComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        
        return UtilDinheiro.arredondar2(totalComDesconto);
    }

    public int getTotalItens() {
        return itens.values().stream().mapToInt(Integer::intValue).sum();
    }
}