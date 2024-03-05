package br.com.alura.alugames.dados

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "jogos")
class JogoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val titulo: String = "Titulo do jogo",
    val capa: String = "Capa do jogo",
    val preco: BigDecimal = BigDecimal.ZERO,
    val descricao: String? = null
) {
}