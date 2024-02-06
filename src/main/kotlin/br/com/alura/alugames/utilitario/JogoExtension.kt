import br.com.alura.alugames.modelo.Jogo

fun Jogo.criarJogo(): Jogo {
    return Jogo(this.titulo, this.capa, this.preco, this.descricao)
}