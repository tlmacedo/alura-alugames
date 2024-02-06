import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.servicos.ConsumoApi
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscarGamers()
    val listaJogoJson = consumo.buscarJogosJson()

//    println(listaGamers)
//    println(listaJogoJson)

    val gamer1 = listaGamers[2]
    val jogo1 = listaJogoJson[2]

    println(gamer1)
    println(jogo1)

    val periodo = Periodo(LocalDate.now(), LocalDate.now().plusDays(5))

    val aluguel = gamer1.alugaJogo(jogo1, periodo)
    println("${aluguel}")
}