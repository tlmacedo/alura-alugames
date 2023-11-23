import com.google.gson.annotations.SerializedName

class Jogo(
    @SerializedName("title") val titulo: String,
    @SerializedName("thumb") var capa: String,
    var descricao: String? = null
) {
    override fun toString(): String {
        return "Meu Jogo:\nTitulo: $titulo\ncapa: $capa${getMdescricao()}"
    }

    fun getMdescricao(): String {
        if (descricao.isNullOrEmpty()) return ""
        return "\nDescrição: $descricao"
    }
}