package br.com.alura.alugames.modelo

import com.google.gson.annotations.Expose
import java.math.BigDecimal
import java.math.RoundingMode
import javax.persistence.*

@Entity
@Table(name = "jogos")
data class Jogo(
    @Expose val titulo: String,
    @Expose var capa: String
) : Recomendavel {
    var preco = BigDecimal("0.0")
    var descricao: String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    private val listaNotas = mutableListOf<Int>()

    constructor(
        titulo: String,
        capa: String,
        preco: BigDecimal,
        descricao: String,
//        id:Int
    ) : this(titulo, capa) {
        this.preco = preco
        this.descricao = descricao
    }

    val listaNotasValidas = listaNotas.filter {
        it.toBigDecimal() != null
    }

    override val media: BigDecimal
        get() = if (listaNotasValidas.isNotEmpty()) {
        listaNotasValidas.average().toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
    } else {
        BigDecimal.ZERO // ou qualquer valor padrão desejado se a lista estiver vazia ou não contiver números válidos
    }


//    override val media: BigDecimal
//        get() = listaNotas.average().toBigDecimal().setScale(2,RoundingMode.HALF_EVEN)

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    override fun toString(): String {
        return "\nMeuJogo[${id}]:" +
                "\nTitulo: $titulo\tValor: R$${preco}" +
                "\ncapa: $capa${getMdescricao()}" +
                "\nReputação: ${media}"
    }

    fun getMdescricao(): String? {
        if (descricao.isNullOrEmpty()) return null
        return "\nDescrição: $descricao"
    }
}