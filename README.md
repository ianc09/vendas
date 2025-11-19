🛒 Sistema de Vendas Java
Um sistema simples de gerenciamento de vendas desenvolvido em Java para estudo de POO e testes unitários.

👥 Integrantes do Grupo
Ian Brito Ribeiro

Mateus Moreira

Caio Vitor Campelo Alcântara

🎯 Funcionalidades
✅ Cadastro de produtos

✅ Carrinho de compras

✅ Controle de estoque

✅ Processamento de vendas

✅ Sistema de descontos

✅ Tratamento de erros

🏗️ Estrutura Principal
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

🚀 Como Usar

Exemplo Básico
java
// Criar produtos
Produto produto1 = new Produto("P001", "Camiseta", new BigDecimal("29.90"));
Produto produto2 = new Produto("P002", "Calça", new BigDecimal("89.90"));

// Configurar estoque
Estoque estoque = new Estoque();
estoque.adicionarEstoque("P001", 10);
estoque.adicionarEstoque("P002", 5);

// Adicionar ao carrinho
Carrinho carrinho = new Carrinho();
carrinho.adicionar(produto1, 2);
carrinho.adicionar(produto2, 1);

// Processar venda
GatewayPagamento gateway = valor -> "AUTH-123";
ServicoVendas servico = new ServicoVendas(estoque, gateway);

Pedido pedido = servico.finalizarCompra(carrinho, subtotal -> subtotal);
🧪 Testes
O sistema inclui testes para todas as funcionalidades:

ProdutoTeste - Testa criação de produtos

CarrinhoTeste - Testa o carrinho de compras

EstoqueTeste - Testa controle de estoque

CheckoutTeste - Testa fluxo completo de venda

📦 Tecnologias
Java 8+

JUnit 5 - Para testes unitários

BigDecimal - Para cálculos monetários precisos

🔧 Executando
bash
# Compilar
javac -cp . br/icev/vendas/*.java br/icev/vendas/excecoes/*.java

# Executar testes
java -cp . org.junit.runner.JUnitCore br.icev.vendas.ProdutoTeste
