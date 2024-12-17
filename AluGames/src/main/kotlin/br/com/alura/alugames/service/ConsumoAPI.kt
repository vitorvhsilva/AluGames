package br.com.alura.alugames.service

import com.google.gson.Gson
import org.example.br.com.alura.alugames.modelo.InfoJogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoAPI {
    fun buscarJogo(id: String): InfoJogo? {
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
            .build()
        val response = client.send(request, BodyHandlers.ofString())

        val json = response.body()


        val gson = Gson()

        var meuInfoJogo: InfoJogo? = null

        val resultado = runCatching {
            meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        }

        resultado.onFailure {
            println("Jogo inexistente. Tente outro id.")
            return null
        }

        return meuInfoJogo
    }
}