# 🛒 Sistema de Vendas - POO

## 👥 Integrantes do Grupo
- Mateus Moreira Fernandes.
- Ian Brito Ribeiro de Castro.
- Caio Vitor Campelo Alcântara.

## 📋 Funcionalidades do Projeto
Gestão de Produtos: Criação e manipulação de produtos com código, nome e preço

Carrinho de Compras: Adição de produtos, cálculo de subtotais e aplicação de descontos

Controle de Estoque: Reserva, confirmação e liberação de itens do estoque

Processamento de Pagamentos: Integração com gateways de pagamento

Gestão de Pedidos: Criação e acompanhamento de pedidos com status

## 🏗️ Estrutura Principal
Classes Principais

Classe	Função

Produto	Representa um produto para venda

Carrinho	Gerencia os itens do carrinho

Estoque	Controla o estoque dos produtos

ServicoVendas	Processa as vendas completas

Pedido	Representa um pedido finalizado

Exceções Personalizadas

SemEstoqueException - Quando não há estoque suficiente

ErroPagamentoException - Quando o pagamento falha

QuantidadeInvalidaException - Quando a quantidade é inválida

## 🚀 Exemplo Rápido
// Criar produtos

Produto camiseta = new Produto("CAM-01", "Camiseta", new BigDecimal("29.90"));

// Configurar sistema

Estoque estoque = new Estoque();

estoque.adicionarEstoque("CAM-01", 10);

Carrinho carrinho = new Carrinho();

carrinho.adicionar(camiseta, 2);

// Processar venda

ServicoVendas vendas = new ServicoVendas(estoque, valor -> "PAGO-123");

Pedido pedido = vendas.finalizarCompra(carrinho, subtotal -> subtotal);

## 🧪 Testes Incluídos
✅ Produtos

✅ Carrinho

✅ Estoque

✅ Vendas Completas

## 🛠️ Tecnologias
Java + JUnit 5 + BigDecimal
