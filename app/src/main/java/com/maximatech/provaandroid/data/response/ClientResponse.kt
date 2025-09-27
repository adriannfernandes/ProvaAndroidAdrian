package com.maximatech.provaandroid.data.response

import com.google.gson.annotations.SerializedName

data class ClientResponse(
     @SerializedName("cliente")
     val cliente: Client
)

data class Client(
    val id: Int,
    @SerializedName("codigo") val codigo: String,
    @SerializedName("razao_social") val razao_social: String,
    @SerializedName("nomeFantasia") val nomeFantasia: String,
    @SerializedName("cnpj") val cnpj: String,
    @SerializedName("ramo_atividade") val ramo_atividade: String,
    @SerializedName("endereco") val endereco: String,
    @SerializedName("status") val status: String,
    @SerializedName("contatos") val contatos: List<Contact> ? = emptyList()
)

data class Contact(
    @SerializedName("nome") val nome: String,
    @SerializedName("telefone") val telefone: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("conjuge") val conjuge: String?,
    @SerializedName("tipo") val tipo: String?,
    @SerializedName("time") val time: String?,
    @SerializedName("e_mail") val e_mail: String?,
    @SerializedName("data_nascimento") val data_nascimento: String?,
    @SerializedName("dataNascimentoConjuge") val dataNascimentoConjuge: String?
)