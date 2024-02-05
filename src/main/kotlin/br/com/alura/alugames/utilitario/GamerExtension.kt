import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoGamerJson


fun InfoGamerJson.criarGamer(): Gamer {
    return Gamer(this.nome, this.email, this.dataNascimento, this.usuario)
}