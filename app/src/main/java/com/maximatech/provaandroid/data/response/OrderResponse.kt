package com.maximatech.provaandroid.data.response

import com.google.gson.annotations.SerializedName


data class OrderResponse(
    @SerializedName("pedidos")
    val orders: List<Order> = emptyList()
)

data class Order(
    val id: Int,
    @SerializedName("numero_ped_Rca") val numeroPedRca: String? = "",
    @SerializedName("numero_ped_erp") val numeroPedErp: String? = "",
    @SerializedName("codigoCliente") val codigoCliente: String? = "",
    @SerializedName("NOMECLIENTE") val nomeCliente: String? = "",
    @SerializedName("data") val data: String? = "",
    @SerializedName("status") val status: String? = "",
    @SerializedName("critica") val critica: String? = "",
    @SerializedName("tipo") val tipo: String? = "",
    @SerializedName("legendas") val legendas: List<String>? = emptyList()

)

