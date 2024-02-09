import br.com.alura.alugames.modelo.Jogo

fun Jogo.criarJogo(): Jogo {
    val jogo = Jogo(this.titulo, this.capa)
    jogo.preco = this.preco
    jogo.descricao = this.descricao
    return jogo
}