package com.maximatech.provaandroid.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.maximatech.provaandroid.R

enum class ReviewStatus(@DrawableRes val icon: Int, @StringRes val description: Int) {

    WAITING(icon = R.drawable.ic_maxima_aguardando_critica, description = R.string.review_text_waiting),
    SUCCESS(icon = R.drawable.ic_maxima_critica_sucesso, description = R.string.review_text_success),
    PARTIAL_FAILURE(icon = R.drawable.ic_maxima_critica_alerta, description = R.string.review_text_partial_failure),
    TOTAL_FAILURE(icon = R.drawable.ic_maxima_legenda_cancelamento, description = R.string.review_text_total_failure)
}