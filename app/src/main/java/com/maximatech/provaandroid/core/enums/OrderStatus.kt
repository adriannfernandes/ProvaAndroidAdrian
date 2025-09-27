package com.maximatech.provaandroid.core.enums

enum class OrderStatus (val description: String, val status: String) {

    EM_PROCESSAMENTO("Em processamento por parte do FV", "Em processamento"),
    RECUSADO("Pedido recusado pelo ERP", "Recusado"),
    PENDENTE("Posicão no ERP Pendente", "Pendente"),
    BLOQUEADO("Posicão no ERP Bloqueado", "Bloqueado"),
    LIBERADO("Posicão no ERP Liberado", "Processado"),
    MONTADO("Posicão no ERP Montado", "Montado"),
    FATURADO("Posicão no ERP Faturado", "Faturado"),
    CANCELADO("Posicão no ERP Cancelado", "Cancelado"),
    ORCAMENTO("Orçamento", "Orçamento")
}