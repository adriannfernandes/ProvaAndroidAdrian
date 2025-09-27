package com.maximatech.provaandroid.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maximatech.provaandroid.app.theme.CardBackground
import com.maximatech.provaandroid.app.theme.ClientRazaoSocial
import com.maximatech.provaandroid.app.theme.ClientText
import com.maximatech.provaandroid.data.dao.ClientWithContacts

@Composable
fun ClientMainItem(onClientSelect: () -> Unit, clientWithContacts: ClientWithContacts){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(77.dp)
            .padding(horizontal = 5.dp, vertical = 4.dp)
            .clickable { onClientSelect() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(CardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${clientWithContacts.client.codigo} - ${clientWithContacts.client.razaoSocial.uppercase()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ClientText
                )
                Text(
                    text = clientWithContacts.client.nomeFantasia.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ClientRazaoSocial
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}