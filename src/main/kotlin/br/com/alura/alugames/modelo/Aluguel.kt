package br.com.alura.alugames.modelo

import java.math.RoundingMode

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo
) {
    val valorDoAluguel = gamer.plano.obterValor(this)
    override fun toString(): String {
        return "${gamer.nome} alugou: ${jogo.titulo} por ${periodo.emDias} dias na diária de R$${jogo.preco} total de R$${
            valorDoAluguel.setScale(2, RoundingMode.HALF_EVEN)
        }"
    }
}
