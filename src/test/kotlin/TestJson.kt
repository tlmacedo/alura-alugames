import br.com.alura.alugames.servicos.ConsumoApi

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscarGamers()
    val jogoApi = consumo.buscarJogo("151")
    println(listaGamers)
    println(jogoApi)
}