package br.com.alura.alugames.modelo

import java.math.MathContext
import java.math.RoundingMode

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo
) {
    val valorDoAluguel = jogo.preco * periodo.emDias
    override fun toString(): String {
        return "Aluguel do ${jogo.titulo} por ${gamer.nome} pelo valor R$${valorDoAluguel.toBigDecimal(MathContext(5,RoundingMode.UP))}"
    }
}
