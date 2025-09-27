package com.maximatech.provaandroid.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.theme.LegendTelemarketing
import com.maximatech.provaandroid.app.theme.OrderAssembled
import com.maximatech.provaandroid.app.theme.OrderBlocked
import com.maximatech.provaandroid.app.theme.OrderCanceled
import com.maximatech.provaandroid.app.theme.OrderInvoiced
import com.maximatech.provaandroid.app.theme.OrderPending
import com.maximatech.provaandroid.app.theme.OrderProcessing
import com.maximatech.provaandroid.app.theme.OrderQuote
import com.maximatech.provaandroid.app.theme.OrderRejected
import com.maximatech.provaandroid.app.theme.OrderReleased
import com.maximatech.provaandroid.app.theme.OrderText
import com.maximatech.provaandroid.core.enums.LegendStatus
import com.maximatech.provaandroid.core.enums.OrderStatus
import com.maximatech.provaandroid.core.enums.ReviewStatus

@Composable
fun LegendDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(13.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_dialog_cation),
                    style = MaterialTheme.typography.headlineLarge,
                    color = OrderText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.order_status_text_dialog),
                    style = MaterialTheme.typography.labelMedium,
                    color = OrderText
                )
                Spacer(modifier = Modifier.height(5.dp))
                LegendItem("...", OrderStatus.EM_PROCESSAMENTO.description, OrderProcessing)
                LegendItem("!", OrderStatus.RECUSADO.description, OrderRejected)
                LegendItem("P", OrderStatus.PENDENTE.description, OrderPending)
                LegendItem("B", OrderStatus.BLOQUEADO.description, OrderBlocked)
                LegendItem("L", OrderStatus.LIBERADO.description, OrderReleased)
                LegendItem("M", OrderStatus.MONTADO.description, OrderAssembled)
                LegendItem("F", OrderStatus.FATURADO.description,OrderInvoiced)
                LegendItem("C", OrderStatus.CANCELADO.description, OrderCanceled)
                LegendItem("O", OrderStatus.ORCAMENTO.description, OrderQuote)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.review_text_dialog),
                    style = MaterialTheme.typography.labelMedium,
                    color = OrderText
                )
                Spacer(modifier = Modifier.height(5.dp))
                GroupLegendItems(items = ReviewStatus.entries.toTypedArray(), iconProvider = {it.icon}, descriptionProvider = {it.description})
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.legend_text_dialog),
                    style = MaterialTheme.typography.labelMedium,
                    color = OrderText
                )
                Spacer(modifier = Modifier.height(5.dp))
                GroupLegendItems(items = LegendStatus.entries.toTypedArray(), iconProvider = {it.icon!!}, descriptionProvider = {it.description!!})
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = stringResource(R.string.button_text_close).uppercase(),
                    color = LegendTelemarketing,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDismiss() }
                )
            }
        }
    }
}

@Composable
fun LegendItem(symbol: String, description: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 1.dp).padding(start = 5.dp)
    ) {

        if (symbol == "...") {
            Icon(
                painter = painterResource(R.drawable.ic_maxima_em_processamento),
                contentDescription = null,
                modifier = Modifier.size(24.dp).background(shape = CircleShape, color = color),
                tint = Color.White
            )
        } else {
            Box(
                modifier = Modifier
                    .size(25.dp)
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
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.labelMedium,
            color = OrderText
        )
    }
}

@Composable
fun <T> GroupLegendItems(
    items: Array<T>,
    iconProvider: (T) -> Int,
    descriptionProvider: (T) -> Int
) {
    items.forEach { item ->
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 5.dp)) {
            Icon(
                painter = painterResource(id = iconProvider(item)),
                contentDescription = stringResource(id = descriptionProvider(item)),
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(id = descriptionProvider(item)),
                style = MaterialTheme.typography.labelMedium,
                color = OrderText
            )
        }
        Spacer(Modifier.height(3.dp))
    }
}


