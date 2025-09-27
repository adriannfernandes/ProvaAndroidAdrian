package com.maximatech.provaandroid.data

import com.maximatech.provaandroid.data.response.ClientResponse
import com.maximatech.provaandroid.data.response.OrderResponse
import retrofit2.http.GET

interface ApiService {

    @GET("cliente")
    suspend fun getAllClients(): ClientResponse

    @GET("pedido")
    suspend fun getAllOrders(): OrderResponse

}

