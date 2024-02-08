import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.modelo.PlanoAssinatura
import br.com.alura.alugames.servicos.ConsumoApi
import java.math.MathContext
import java.math.RoundingMode
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscarGamers()
    val listaJogoJson = consumo.buscarJogosJson()

//    println(listaGamers)
//    println(listaJogoJson)

    val gamer1 = listaGamers[3]
    val jogo1 = listaJogoJson[10]
    val jogo2 = listaJogoJson[13]
    val jogo3 = listaJogoJson[2]

//    println(gamer1)
//    println(jogo1)
//    println(jogo2)
//    println(jogo3)

    val periodo1 = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
    val periodo2 = Periodo(LocalDate.now(), LocalDate.now().plusDays(3))
    val periodo3 = Periodo(LocalDate.now(), LocalDate.now().plusDays(10))

    gamer1.alugaJogo(jogo1, periodo1)
    gamer1.alugaJogo(jogo2, periodo2)
    gamer1.alugaJogo(jogo3, periodo3)
    println(gamer1.jogosAlugados)
    println(
        "Total do aluguel foi de R$${
            gamer1.jogosAlugados.sumOf { aluguel ->
                aluguel.valorDoAluguel.toBigDecimal(
                    MathContext(4, RoundingMode.UP)
                )
            }
        }"
    )

    val gamer2 = listaGamers[5]
    gamer2.plano = PlanoAssinatura("PRATA", 9.90, 3)
    gamer2.alugaJogo(jogo1,periodo1)
    gamer2.alugaJogo(jogo2,periodo2)
    gamer2.alugaJogo(jogo3,periodo3)
    println(gamer2.jogosAlugados)
    println(
        "Total do aluguel foi de R$${
            gamer2.jogosAlugados.sumOf { aluguel ->
                aluguel.valorDoAluguel.toBigDecimal(
                    MathContext(4, RoundingMode.UP)
                )
            }
        }"
    )
}