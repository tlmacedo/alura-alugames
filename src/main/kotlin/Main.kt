import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    print("Digite um código de jogo para buscar: ")

    val busca = leitura.nextLine()

    val urlGame = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(urlGame))
        .build()
    val response = client.send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)

    val gson = Gson()

    try {
        val meuInfoJogo = gson.fromJson(
            json, InfoJogo::class.java
        )

        val meuJogo = Jogo(
            meuInfoJogo.info.title,
            meuInfoJogo.info.thumb
        )
        println(meuJogo)
    } catch (ex: Exception) {
        when (ex) {
            is JsonSyntaxException,
            is NullPointerException -> println("Jogo inexistente. Tente outro id.")
        }
    }


}