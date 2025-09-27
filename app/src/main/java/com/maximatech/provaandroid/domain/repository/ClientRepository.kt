package com.maximatech.provaandroid.domain.repository

import android.util.Log
import com.maximatech.provaandroid.data.RetrofitInstance
import com.maximatech.provaandroid.data.dao.ClientDao
import com.maximatech.provaandroid.data.dao.ClientWithContacts
import com.maximatech.provaandroid.data.entity.ClientEntity
import com.maximatech.provaandroid.data.entity.ContactEntity

class ClientRepository(private val dao: ClientDao) {

    suspend fun findAndSaveClient() {

        val response = RetrofitInstance.api.getAllClients()
        val cliente = response.cliente

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

            dao.insertClient(clientEntity)
            dao.insertContacts(contactsEntity ?: emptyList())

        }
    }

    suspend fun getClientById(id: Int): ClientWithContacts{
        return dao.getClientById(id)
    }

    suspend fun getClients(): List<ClientWithContacts> {
        return dao.getAllClientsWithContacts()
    }

}