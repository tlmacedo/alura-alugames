data class Jogo(
    val titulo: String,
    var capa: String,
    var descricao: String? = null
) {
    override fun toString(): String {
        return "Meu Jogo:\nTitulo: $titulo\ncapa: $capa${getMdescricao()}"
    }

    fun getMdescricao(): String? {
        if (descricao.isNullOrEmpty()) return null
        return "\nDescrição: $descricao"
    }
}