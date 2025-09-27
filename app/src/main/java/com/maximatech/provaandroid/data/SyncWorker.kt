package com.maximatech.provaandroid.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.maximatech.provaandroid.data.entity.ClientEntity
import com.maximatech.provaandroid.data.entity.ContactEntity
import com.maximatech.provaandroid.data.entity.OrderEntity
import com.maximatech.provaandroid.data.response.ClientResponse
import com.maximatech.provaandroid.data.response.OrderResponse

class SyncWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    private val dbClient = AppDatabase.getDatabase(appContext).clientDao()
    private val dbOrder = AppDatabase.getDatabase(appContext).orderDao()
    private val api: ApiService = RetrofitInstance.api

    override suspend fun doWork(): Result {
        return try {

            val responseClients = api.getAllClients()


            val responseOrders = api.getAllOrders()

            val clients = responseClients
            val orders = responseOrders

            saveClientsToDb(clients)
            saveOrdersToDb(orders)

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    private suspend fun saveClientsToDb(clients: ClientResponse) {

        Log.d("SyncWorker", "Salvando clientes no banco de dados...")

        val cliente = clients.cliente

        cliente.let { client ->
            val clientEntity = ClientEntity(
                id = client.id,
                codigo = client.codigo,
                razaoSocial = client.razao_social,
                nomeFantasia = client.nomeFantasia,
                cnpj = client.cnpj,
                ramoAtividade = client.ramo_atividade,
                endereco = client.endereco,
                status = client.status
            )

            val contactsEntity = client.contatos?.map {
                ContactEntity(
                    clienteId = client.id,
                    nome = it.nome,
                    telefone = it.telefone,
                    celular = it.celular,
                    conjuge = it.conjuge,
                    tipo = it.tipo,
                    time = it.time,
                    email = it.e_mail,
                    dataNascimento = it.data_nascimento,
                    dataNascimentoConjuge = it.dataNascimentoConjuge
                )
            }

            dbClient.insertClient(clientEntity)
            dbClient.insertContacts(contactsEntity ?: emptyList())

        }
    }

    private suspend fun saveOrdersToDb(orders: OrderResponse) {
        val orders = orders.orders

        Log.d("SyncWorker", "Salvando pedidos no banco de dados...")

        orders.forEach { order ->
            val orderEntity = OrderEntity(
                id = order.id,
                numeroPedErp = order.numeroPedErp!!,
                numeroPedRca = order.numeroPedRca!!,
                codigoCliente = order.codigoCliente!!,
                nomeCliente = order.nomeCliente,
                data = order.data!!,
                status = order.status,
                critica = order.critica,
                tipo = order.tipo,
                legendas = order.legendas
            )
            dbOrder.insertOrder(orderEntity)
        }
    }
}