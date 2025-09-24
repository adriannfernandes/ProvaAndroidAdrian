package com.maximatech.provaandroid.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.maximatech.provaandroid.R

enum class LegendStatus(@DrawableRes val icon: Int, @StringRes val description: Int) {
    CUT(icon = R.drawable.ic_maxima_legenda_corte, description = R.string.legend_text_cut),
    MISSING(icon = R.drawable.ic_maxima_legenda_falta, description = R.string.legend_text_missing),
    CANCELED(icon = R.drawable.ic_maxima_legenda_cancelamento, description = R.string.legend_text_canceled),
    RETURN(icon = R.drawable.ic_maxima_legenda_devolucao, description = R.string.legend_text_return),
    TELEMARKETING(icon = R.drawable.ic_maxima_legenda_telemarketing, description = R.string.legend_text_telemarketing)
}