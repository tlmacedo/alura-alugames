import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Scanner

fun main() {

    val leitura = Scanner(System.`in`)
    print("Digite um código de jogo para buscar: ")

    val busca = leitura.nextLine()

    val urlGame = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(urlGame))
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    val json = response.body()
    println("Retorno Site:\n$json")

    val gson = Gson()
    val meuInfoJogo = gson.fromJson(
        json, InfoJogo::class.java
    )

    val meuJogo = Jogo(
        meuInfoJogo.info.title,
        meuInfoJogo.info.thumb
    )

    println(meuJogo)

}