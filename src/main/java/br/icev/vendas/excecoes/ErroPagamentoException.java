package br.icev.vendas.excecoes;

public class ErroPagamentoException extends Exception {
    public ErroPagamentoException(String mensagem) {
        super(mensagem);
    }
}