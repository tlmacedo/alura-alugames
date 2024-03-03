package br.com.alura.alugames.modelo

import com.google.gson.annotations.Expose
import java.math.BigDecimal
import java.math.RoundingMode

data class Jogo(
    @Expose val titulo: String,
    @Expose var capa: String,
) : Recomendavel {
    var descricao: String? = null
    var preco = BigDecimal("0.0")
    private val listaNotas = mutableListOf<Int>()

    override val media: BigDecimal
        get() = listaNotas.average().toBigDecimal().setScale(2,RoundingMode.HALF_EVEN)

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    override fun toString(): String {
        return "\nMeuJogo:" +
                "\nTitulo: $titulo\tValor: R$${preco}" +
                "\ncapa: $capa${getMdescricao()}" +
                "\nReputação: ${media}"
    }

    fun getMdescricao(): String? {
        if (descricao.isNullOrEmpty()) return null
        return "\nDescrição: $descricao"
    }
}