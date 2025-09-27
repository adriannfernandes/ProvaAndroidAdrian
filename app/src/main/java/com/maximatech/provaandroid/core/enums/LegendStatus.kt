package com.maximatech.provaandroid.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.maximatech.provaandroid.R

enum class LegendStatus(@DrawableRes val icon: Int, @StringRes val description: Int) {

    PEDIDO_SOFREU_CORTE(icon = R.drawable.ic_maxima_legenda_corte, description = R.string.legend_text_cut,),
    PEDIDO_COM_FALTA(icon = R.drawable.ic_maxima_legenda_falta, description = R.string.legend_text_missing),
    PEDIDO_CANCELADO_ERP(icon = R.drawable.ic_maxima_legenda_cancelamento, description = R.string.legend_text_canceled),
    PEDIDO_COM_DEVOLUCAO(icon = R.drawable.ic_maxima_legenda_devolucao, description = R.string.legend_text_return),
    PEDIDO_FEITO_TELEMARKETING(icon = R.drawable.ic_maxima_legenda_telemarketing, description = R.string.legend_text_telemarketing)
}