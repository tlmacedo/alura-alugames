package br.com.alura.alugames.modelo

data class Jogo(
    val titulo: String,
    var capa: String,
    val preco: Double,
    var descricao: String? = null
) {
    override fun toString(): String {
        return "MeuJogo:" +
                "\nTitulo: $titulo\tValor: R$${preco}" +
                "\ncapa: $capa${getMdescricao()}"
    }

    fun getMdescricao(): String? {
        if (descricao.isNullOrEmpty()) return null
        return "\nDescrição: $descricao"
    }
}