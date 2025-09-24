package com.maximatech.provaandroid.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.maximatech.provaandroid.R

enum class OrderStatus (val description: String) {
    IN_PROCESS("Em processamento por parte do FV"),
    REFUSED("Pedido recusado pelo ERP"),
    PENDING("Posicão no ERP Pendente"),
    BLOCK("Posicão no ERP Bloqueado"),
    RELEASED("Posicão no ERP Liberado"),
    MOUNTED("Posicão no ERP Montado"),
    INVOICED("Posicão no ERP Faturado"),
    CANCELED("Posicão no ERP Cancelado"),
    BUDGET("Orçamento")
}