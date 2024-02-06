import br.com.alura.alugames.servicos.ConsumoApi

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscarGamers()
    val listaJogoJson = consumo.buscarJogosJson()

//    println(listaGamers)
//    println(listaJogoJson)

    val gamer1 = listaGamers[3]
    val jogo1 = listaJogoJson[10]

    println(gamer1)
    println(jogo1)

    val aluguel = gamer1.alugaJogo(jogo1)
    println("${aluguel}")
}