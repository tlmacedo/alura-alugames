package br.com.alura.alugames.dados

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "jogos")
class JogoEntity(
    val titulo: String = "Titulo do jogo",
    val capa: String = "Capa do jogo",
    val preco: BigDecimal = BigDecimal.ZERO,
    val descricao: String? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
) {

    override fun toString(): String {
        return "JogoEntity(titulo='$titulo', capa='$capa', preco=$preco, descricao=$descricao, id=$id)"
    }
}