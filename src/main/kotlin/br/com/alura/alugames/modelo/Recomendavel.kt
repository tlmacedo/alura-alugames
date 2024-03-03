package br.com.alura.alugames.modelo

import java.math.BigDecimal

interface Recomendavel {
    val media: BigDecimal

    fun recomendar(nota: Int)
}