package com.maximatech.provaandroid.app.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.theme.CriticaPartialFail
import com.maximatech.provaandroid.app.theme.CriticaPending
import com.maximatech.provaandroid.app.theme.CriticaSuccess
import com.maximatech.provaandroid.app.theme.CriticaTotalFail
import com.maximatech.provaandroid.app.theme.LegendCanceled
import com.maximatech.provaandroid.app.theme.LegendCut
import com.maximatech.provaandroid.app.theme.LegendMissing
import com.maximatech.provaandroid.app.theme.LegendReturned
import com.maximatech.provaandroid.app.theme.LegendTelemarketing
import com.maximatech.provaandroid.app.theme.OrderAssembled
import com.maximatech.provaandroid.app.theme.OrderBlocked
import com.maximatech.provaandroid.app.theme.OrderCanceled
import com.maximatech.provaandroid.app.theme.OrderInvoiced
import com.maximatech.provaandroid.app.theme.OrderLabel
import com.maximatech.provaandroid.app.theme.OrderPending
import com.maximatech.provaandroid.app.theme.OrderProcessing
import com.maximatech.provaandroid.app.theme.OrderQuote
import com.maximatech.provaandroid.app.theme.OrderRejected
import com.maximatech.provaandroid.app.theme.OrderReleased
import com.maximatech.provaandroid.app.theme.OrderText
import com.maximatech.provaandroid.core.enums.LegendStatus
import com.maximatech.provaandroid.core.enums.OrderStatus
import com.maximatech.provaandroid.core.enums.ReviewStatus
import com.maximatech.provaandroid.core.util.formatSmartDate
import com.maximatech.provaandroid.data.response.Order


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderMainItem(order: Order) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (order.status?.equals(OrderStatus.EM_PROCESSAMENTO.status, ignoreCase = true) == true) {
            Icon(
                painter = painterResource(R.drawable.ic_maxima_em_processamento),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .background(shape = CircleShape, color = OrderProcessing),
                tint = Color.White
            )
        } else {
            StatusCircle(order.status)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            OrderHeader(order)
            OrderDetails(order)
        }
    }
    Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.LightGray)
}

@Composable
private fun StatusCircle(status: String?) {
    val (symbol, color) = when (status) {
        OrderStatus.RECUSADO.status -> "!" to OrderRejected
        OrderStatus.PENDENTE.status -> "P" to OrderPending
        OrderStatus.BLOQUEADO.status -> "B" to OrderBlocked
        OrderStatus.LIBERADO.status -> "L" to OrderReleased
        OrderStatus.MONTADO.status -> "M" to OrderAssembled
        OrderStatus.FATURADO.status -> "F" to OrderInvoiced
        OrderStatus.CANCELADO.status -> "C" to OrderCanceled
        OrderStatus.ORCAMENTO.status -> "O" to OrderQuote
        else -> "" to Color.Transparent
    }

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color = color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun OrderHeader(order: Order) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = if (order.status != OrderStatus.ORCAMENTO.status)
                    stringResource(R.string.label_text_ped_rca_erp_order)
                else
                    stringResource(R.string.label_text_orc_rca_erp_order),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = OrderLabel
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${order.numeroPedRca}/${order.numeroPedErp}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = OrderText
            )
        }


        Text(
            text = formatSmartDate(order.data!!),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = OrderLabel
        )
    }
}

@Composable
private fun OrderDetails(order: Order) {

    var legendIcon: Painter = painterResource(R.drawable.ic_maxima_ferramentas)
    var legendIconColor = Color.Transparent
    if (!order.legendas.isNullOrEmpty()){
        when(order.legendas[0]){
            LegendStatus.PEDIDO_SOFREU_CORTE.name -> {
                legendIcon = painterResource(R.drawable.ic_maxima_legenda_corte)
                legendIconColor = LegendCut }
            LegendStatus.PEDIDO_COM_FALTA.name -> {
                legendIcon = painterResource(R.drawable.ic_maxima_legenda_falta)
                legendIconColor = LegendMissing }
            LegendStatus.PEDIDO_CANCELADO_ERP.name -> {
                legendIcon = painterResource(R.drawable.ic_maxima_legenda_cancelamento)
                legendIconColor = LegendCanceled }
            LegendStatus.PEDIDO_COM_DEVOLUCAO.name -> {
                legendIcon = painterResource(R.drawable.ic_maxima_legenda_devolucao)
                legendIconColor = LegendReturned }
            LegendStatus.PEDIDO_FEITO_TELEMARKETING.name -> {
                legendIcon = painterResource(R.drawable.ic_maxima_legenda_telemarketing)
                legendIconColor = LegendTelemarketing }
            else -> { }
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = stringResource(R.string.label_text_client_order),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = OrderLabel
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${order.codigoCliente} - ${order.nomeCliente}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = OrderText
            )
        }

        Icon(
            painter = legendIcon,
            contentDescription = null,
            tint = legendIconColor,
            modifier = Modifier.size(20.dp)
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = order.status.orEmpty(),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = OrderText
        )

        Spacer(modifier = Modifier.width(4.dp))

        if (!order.critica.isNullOrBlank()) {
            var reviewIcon: Painter = painterResource(R.drawable.ic_maxima_ferramentas)
            var reviewIconColor = Color.Transparent
            when(order.critica){
                ReviewStatus.AGUARDANDO.name -> {
                    reviewIcon = painterResource(R.drawable.ic_maxima_aguardando_critica)
                    reviewIconColor = CriticaPending
                }
                ReviewStatus.SUCESSO.name -> {
                    reviewIcon = painterResource(R.drawable.ic_maxima_critica_sucesso)
                    reviewIconColor = CriticaSuccess
                }
                ReviewStatus.FALHA_PARCIAL.name -> {
                    reviewIcon = painterResource(R.drawable.ic_maxima_critica_alerta)
                    reviewIconColor = CriticaPartialFail
                }
                ReviewStatus.FALHA_TOTAL.name -> {
                    reviewIcon = painterResource(R.drawable.ic_maxima_legenda_cancelamento)
                    reviewIconColor = CriticaTotalFail
                }
                else -> { }
            }

            Text(
                text = stringResource(R.string.label_text_review_order),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = OrderLabel
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = reviewIcon,
                contentDescription = null,
                tint = reviewIconColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

