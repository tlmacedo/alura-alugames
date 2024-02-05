package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoGamerJson
import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import criarGamer
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscarJogo(id: String): InfoJogo? {
        val urlGame = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val json = buscarUrl(urlGame)
        val gson = Gson()

        return gson.fromJson(json, InfoJogo::class.java)
    }

    fun buscarGamers(): List<Gamer> {
        val urlGame = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val json = buscarUrl(urlGame)
        val gson = Gson()

        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type
        val listaGamer: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)

        val listaGamerConvertida = listaGamer.map { infoGamerJson ->
            infoGamerJson.criarGamer()
        }

        return listaGamerConvertida
    }

    private fun buscarUrl(url: String): String? {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) return null

        val json = response.body()
        return json
    }
}