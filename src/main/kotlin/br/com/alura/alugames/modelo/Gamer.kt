package br.com.alura.alugames.modelo

import java.util.Scanner
import kotlin.random.Random

data class Gamer(
    var nome: String,
    var email: String
) {
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank())
                criarIdInterno()
        }
    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo>()

    constructor(
        nome: String,
        email: String,
        dataNascimento: String,
        usuario: String
    ) : this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    init {
        if (nome.isBlank())
            throw IllegalAccessException("Nome está em branco.")
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        this.idInterno = "${usuario}#$tag"

    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email))
            return email
        throw IllegalAccessException("Email inválido!")
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            print("*******************************************************\n*             Boas Vindas ao AluGames                 *\n*******************************************************\nVamos fazer seu cadastro.\nDigite seu nome: ")
            val nome = leitura.nextLine()
            print("Digite seu e-mail: ")
            val email = leitura.nextLine()
            print("Deseja completar seu cadastro com usuário e data de nascimento? (S/N): ")
            val opcao = leitura.nextLine()

            return if (opcao.equals("s", true)) {
                print("Digite sua data de nascimento(DD/MM/AAAA): ")
                val nascimento = leitura.nextLine()
                print("Digite seu nome de usuário: ")
                val usuario = leitura.nextLine()

                Gamer(nome, email, nascimento, usuario)
            } else
                Gamer(nome, email)
        }
    }


}

