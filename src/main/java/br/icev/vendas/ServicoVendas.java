package br.icev.vendas;

import br.icev.vendas.excecoes.ErroPagamentoException;
import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;
import java.math.BigDecimal;

public class ServicoVendas {
    private final Estoque estoque;
    private final GatewayPagamento gatewayPagamento;
    
    public ServicoVendas(Estoque estoque, GatewayPagamento gatewayPagamento) {
        this.estoque = estoque;
        this.gatewayPagamento = gatewayPagamento;
    }
    
    public Pedido finalizarCompra(Carrinho carrinho, PoliticaDesconto politica) 
            throws SemEstoqueException, QuantidadeInvalidaException, ErroPagamentoException {
        
        // Validar estoque
        for (String codigo : carrinho.getItensPorCodigo().keySet()) {
            int quantidade = carrinho.getItensPorCodigo().get(codigo);
            estoque.reservar(codigo, quantidade);
        }
        
        try {
            // Calcular total com política de desconto
            BigDecimal total = carrinho.getTotalCom(politica);
            
            // Processar pagamento
            String codigoAutorizacao = gatewayPagamento.cobrar(total);
            
            // Confirmar reservas no estoque
            for (String codigo : carrinho.getItensPorCodigo().keySet()) {
                int quantidade = carrinho.getItensPorCodigo().get(codigo);
                estoque.confirmarReserva(codigo, quantidade);
            }
            
            return new Pedido(carrinho.getItensPorCodigo(), total, 
                            codigoAutorizacao, Pedido.Status.PAGO);
            
        } catch (ErroPagamentoException e) {
            // Liberar reservas em caso de erro no pagamento
            for (String codigo : carrinho.getItensPorCodigo().keySet()) {
                int quantidade = carrinho.getItensPorCodigo().get(codigo);
                estoque.liberarReserva(codigo, quantidade);
            }
            throw e;
        }
    }
}