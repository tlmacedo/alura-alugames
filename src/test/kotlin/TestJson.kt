import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.modelo.PlanoAssinatura
import br.com.alura.alugames.servicos.ConsumoApi
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
    val jogo4 = listaJogoJson[11]

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
//    println(gamer1.jogosAlugados)
//    println(
//        "Total do aluguel foi de R$${
//            gamer1.jogosAlugados.sumOf { aluguel ->
//                aluguel.valorDoAluguel.toBigDecimal(
//                    MathContext(4, RoundingMode.UP)
//                )
//            }
//        }"
//    )

    val gamer2 = listaGamers[5]
    gamer2.plano = PlanoAssinatura("PRATA", 9.90, 3, 0.15)
    gamer2.alugaJogo(jogo1, periodo1)
    gamer2.alugaJogo(jogo2, periodo2)
    gamer2.alugaJogo(jogo3, periodo3)
    gamer2.alugaJogo(jogo4, periodo3)
//    println(gamer2.jogosAlugados)
//    println(
//        "Total do aluguel foi de R$${
//            gamer2.jogosAlugados.sumOf { aluguel ->
//                aluguel.valorDoAluguel.toBigDecimal(
//                    MathContext(4, RoundingMode.UP)
//                )
//            }
//        }"
//    )

    gamer2.recomendar(7)
    gamer2.recomendar(10)
    gamer2.recomendar(8)
//    println(gamer2)

    gamer2.alugaJogo(jogo1, periodo1)
//    println(gamer2.jogosAlugados)

    gamer2.recomendarJogo(jogo1, 7)
    gamer2.recomendarJogo(jogo3, 10)

    gamer1.recomendarJogo(jogo1, 8)
    gamer1.recomendarJogo(jogo3, 9)

//    println(gamer2.jogosRecomendados)
//    println(gamer1.jogosRecomendados)

    val gamerCamila = listaGamers.get(5)
    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogoJson.get(10)
    val jogoSpider = listaJogoJson.get(13)
    val jogoTheLastOfUs = listaJogoJson.get(2)
    val jogoDandara = listaJogoJson.get(5)
    val jogoAssassins = listaJogoJson.get(4)
    val jogoCyber = listaJogoJson.get(6)
    val jogoGod = listaJogoJson.get(7)
    val jogoSkyrim = listaJogoJson.get(18)

    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCamila.recomendarJogo(jogoAssassins, 8)
    gamerCamila.recomendarJogo(jogoCyber, 7)
    gamerCamila.recomendarJogo(jogoGod, 10)
    gamerCamila.recomendarJogo(jogoDandara, 8)
    gamerCamila.recomendarJogo(jogoSkyrim, 8)
    gamerCamila.recomendarJogo(jogoSpider, 6)

    println(gamerCamila.jogosRecomendados)
    println(gamerCaroline.jogosRecomendados)

}