package br.com.alura.alugames.modelo

import com.google.gson.annotations.Expose

data class Jogo(
    @Expose val titulo: String,
    @Expose var capa: String,
) : Recomendavel {
    var descricao: String? = null
    var preco = 0.0
    private val listaNotas = mutableListOf<Int>()

    override val media: Double
        get() = listaNotas.average()

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