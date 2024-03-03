package br.com.alura.alugames.modelo

import java.math.BigDecimal
import java.math.RoundingMode

sealed class Plano(val tipo: String) {

    open fun obterValor(aluguel: Aluguel): BigDecimal {
        return aluguel.jogo.preco.multiply(aluguel.periodo.emDias.toBigDecimal()).setScale(2, RoundingMode.HALF_EVEN)
    }
}
