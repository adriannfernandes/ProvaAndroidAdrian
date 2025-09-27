package com.maximatech.provaandroid.feature.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.navigation.SharedUiViewModel
import com.maximatech.provaandroid.app.theme.CardBackground
import com.maximatech.provaandroid.app.theme.ClientLabel
import com.maximatech.provaandroid.app.theme.ClientRazaoSocial
import com.maximatech.provaandroid.app.theme.ClientText
import com.maximatech.provaandroid.app.theme.EmailButton
import com.maximatech.provaandroid.app.theme.PhoneButton
import com.maximatech.provaandroid.data.dao.ClientWithContacts
import com.maximatech.provaandroid.data.entity.ContactEntity

@Composable
fun InfoClientScreen (viewModel: ClientViewModel = viewModel(), idClient: Int, uiViewModel: SharedUiViewModel) {

    var selectedClient by remember { mutableStateOf<ClientWithContacts?>(null) }

    LaunchedEffect(selectedClient) {
        selectedClient = viewModel.getClientById(idClient)
        selectedClient.let {
            uiViewModel.setTitle(selectedClient?.client!!.nomeFantasia)
        }

    }

    selectedClient?.let {
        Column (modifier = Modifier.padding(top = 10.dp)) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(CardBackground)
            ){
                Row(modifier = Modifier.padding(horizontal = 7.dp)) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.title_text_data_client),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = ClientText
                        )
                        Divider(modifier = Modifier.fillMaxWidth().padding(top = 3.dp), thickness = 1.dp, color = Color.LightGray)
                    }
                }
                Column(modifier = Modifier.padding(start = 7.dp)) {
                    Text(
                        text = "${selectedClient!!.client.codigo} - ${selectedClient!!.client.razaoSocial.uppercase()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ClientText
                    )
                    Text(
                        text = selectedClient!!.client.nomeFantasia.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ClientRazaoSocial
                    )
                }
                Column(modifier = Modifier.padding(start = 7.dp)) {
                    Row {
                        Text(
                            text = stringResource(R.string.label_text_cnpj_client),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientLabel
                        )

                        Text(
                            text = selectedClient!!.client.cnpj,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientText
                        )
                    }
                    Row {
                        Text(
                            text = stringResource(R.string.label_text_ramo_client),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientLabel
                        )

                        Text(
                            text = selectedClient!!.client.ramoAtividade,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientText
                        )
                    }
                    Row {
                        Text(
                            text = stringResource(R.string.label_text_address_client),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientLabel
                        )

                        Text(
                            text = selectedClient!!.client.endereco,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = ClientText
                        )
                    }
                }
            }
            CardContactInfo(contacts = selectedClient!!.contacts)
        }
    }




}

@Composable
fun CardContactInfo(contacts: List<ContactEntity>){

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(CardBackground)
    ){

        contacts.forEach { contactEntity ->
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.title_text_contact),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = ClientText
                    )
                    Divider(modifier = Modifier.fillMaxWidth().padding(top = 3.dp), thickness = 1.dp, color = Color.LightGray)
                }
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(
                    text = contactEntity.nome!!,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = ClientText
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column (modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(1.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = stringResource(R.string.label_text_phone_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.telefone ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )

                            if (contactEntity.telefone != null){
                                Icon(
                                    painter = painterResource(R.drawable.ic_maxima_telefone),
                                    modifier = Modifier.size(11.dp),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                )
                            }

                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.label_text_cellphone_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.celular ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )

                            if (contactEntity.celular != null){
                                Icon(
                                    painter = painterResource(R.drawable.ic_maxima_telefone),
                                    modifier = Modifier.size(11.dp),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.label_text_spouse_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.conjuge ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.label_text_type_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.tipo ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )
                        }

                    }

                    Column(modifier = Modifier.wrapContentSize()) {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = stringResource(R.string.label_text_email_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.email ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText,
                                overflow = TextOverflow.Ellipsis
                            )

                            if (contactEntity.email != null){
                                Icon(
                                    painter = painterResource(R.drawable.ic_maxima_email),
                                    modifier = Modifier.size(11.dp),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.label_text_birthday_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.dataNascimento ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.label_text_spouse_birthday_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.dataNascimentoConjuge ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.label_text_club_contact),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientLabel
                            )

                            Text(
                                text = contactEntity.time ?: stringResource(R.string.label_text_not_informed),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = ClientText
                            )
                        }
                    }
                }

            }
        }

    }
}
