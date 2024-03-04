import br.com.alura.alugames.dados.Banco
import br.com.alura.alugames.dados.JogosDAO
import br.com.alura.alugames.modelo.Jogo
import java.math.BigDecimal


fun main() {

    val jogo = Jogo(
        titulo = "The Last of Us Part I",
        capa = "https://cdn.cloudflare.steamstatic.com/steam/apps/1888930/header.jpg?t=1686864554",
        preco = BigDecimal("5.99"),
        descricao = "Uma aventura pós-apocalíptica de sobrevivência em um mundo infestado por zumbis e facções em conflito.",
        0
    )
    val jogo2 = Jogo(
        "Dandara",
        "https://cdn.cloudflare.steamstatic.com/steam/apps/612390/header.jpg?t=1674055293",
        BigDecimal("9.99"),
        "Um jogo de plataforma e ação com elementos de metroidvania, onde você controla a heroína Dandara em sua luta para libertar um mundo repleto de opressão e tirania.",
        0
    )

    val manager = Banco.getEntityManager()
    val jogoDAO = JogosDAO(manager)

    jogoDAO.adicionarJogo(jogo2)

//    jogoDAO.adicionarJogo(jogo)

    val listaJogos: List<Jogo> = jogoDAO.getJogos()
    println(listaJogos)

    manager.close()

}