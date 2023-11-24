package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscarJogo(id: String): InfoJogo? {

        val urlGame = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(urlGame))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) return null

        val json = response.body()
        val gson = Gson()

        return gson.fromJson(json, InfoJogo::class.java)
    }
}