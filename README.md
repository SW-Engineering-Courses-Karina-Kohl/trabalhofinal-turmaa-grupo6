[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/RBBavBFg)
#  Calculadora de Frete

## Visão Geral
Sistema de cálculo de frete **orientado a dados**, projetado para processar pedidos e gerar opções de entrega com base em diferentes companhias.

## Objetivo
Receber:
- Configurações de uma companhia de frete
- Lista de pedidos pendentes

Retornar:
- Valor de frete para cada pedido
- Ordenadas por prioridade
- Com data estimada de entrega

## Modelagem Conceitual
A modelagem foi estruturada para refletir diretamente o contexto da aplicação e o formato dos dados de entrada.

### Pedido
Representa a unidade de entrada do sistema.

**Responsabilidades:**
- Armazenar dados relevantes (distância, peso, tipo de serviço, etc.)
- Servir como base para o cálculo de frete

### Companhia de Frete
Representa uma estratégia de cálculo aplicada aos pedidos.

**Responsabilidades:**
- Interpretar configurações
- Aplicar regras específicas de cálculo
- Definir:
  - Valor do frete
  - Prazo de entrega
  - Restrições operacionais

### Frete
Representa o resultado do processamento.

**Responsabilidades:**
- Associar pedido e companhia
- Armazenar:
  - Valor calculado
  - Prazo de entrega
  - Prioridade

## Fluxo do Sistema

1. Dados são obtidos a partir de fontes externas
2. Pedidos e companhias são disponibilizados ao sistema
3. Um componente de cálculo processa os pedidos:
   - Aplica cada companhia disponível
   - Gera opções de frete válidas
4. Os resultados são organizados:
   - Por prioridade
   - Por data de entrega
5. A resposta é retornada

## Padrão Arquitetural
O projeto segue o padrão **MVC (Model-View-Controller)**, conforme definido na proposta.

- **Model**: representa o contexto (`Pedido`, `CompanhiaFrete`, `Frete`)
- **Controller**: recebe entradas e aciona o processamento
- **View**: representa os resultados

## Estrutura do Projeto

```
src/
 ├── controller/
 │    └── 
```

## Regras de Negócio

- Cada companhia define seus próprios parâmetros de cálculo
- Um pedido pode gerar múltiplas opções de frete para companhias diferentes
- Uma companhia só gera resultado se atender às restrições do pedido
- Os resultados são ordenados por:
  1. Prioridade
  2. Data de entrega

## Entrada de Dados

O sistema trabalha com dados estruturados, como:

- Configurações de companhias
- Pedidos pendentes
- Resultados de logística

Esses dados podem ser fornecidos por diferentes fontes.

## Possíveis Extensões

- Suporte a múltiplos formatos de dados
- Integração com serviços externos
- Persistência de resultados

## Autoria

preencher
