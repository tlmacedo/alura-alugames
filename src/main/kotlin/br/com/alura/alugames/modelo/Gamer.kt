package br.com.alura.alugames.modelo

import java.util.*
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

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    private fun criarIdInterno() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        this.idInterno = "${usuario}#$tag"

    }


    companion object {
        var name: String? = null
        var email: String? = null
        var leituraGamer = Scanner(System.`in`)
        fun criarGamer(leitura: Scanner): Gamer {
            this.leituraGamer = leitura

            imprimirCabecalho()
            getNameNewUser()
            getEmailNewUser()

            print("Deseja completar seu cadastro com usuário e data de nascimento? (S/N): ")
            val opcao = leituraGamer.nextLine()

            return if (opcao.equals("s", true)) {
                print("Digite sua data de nascimento(DD/MM/AAAA): ")
                val nascimento = leituraGamer.nextLine()
                print("Digite seu nome de usuário: ")
                val usuario = leituraGamer.nextLine()

                Gamer(name!!, email!!, nascimento, usuario)
            } else
                Gamer(name!!, email!!)
        }

        private fun validEmail(): Boolean {
            // Retornar true se o email for válido.
            val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            return regex.matches(email!!)
        }

        internal fun validName(): Boolean {

            if (name.isNullOrBlank() || name!!.replace(
                    "[0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]".toRegex(),
                    ""
                ).length < 3
            )

                return false
            name = name!!.split(" ").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
            return true
        }

        fun imprimirCabecalho(titulo: String = "AluGames") {
            print(
                "*******************************************************\n" +
                        "*             Boas Vindas ao $titulo                 *\n" +
                        "*******************************************************\n" +
                        "Vamos fazer seu cadastro?\n"
            )
        }

        fun getNameNewUser() {
            print("Primeiro informe seu nome: ")
            var isName: Boolean? = null
            do {
                if (isName == false)
                    print("nome está em branco, por favor informe seu nome: ")
                name = leituraGamer.nextLine()
                isName = validName()
            } while (!isName!!)
        }

        private fun getEmailNewUser() {
            var isEmail: Boolean? = null
            print("$name informe seu e-mail: ")
            do {
                if (isEmail == false) {
                    print("$name o e-mail: \"$email\" é inválido!\ninforme um e-mail válido: ")
                }
                email = leituraGamer.nextLine()
                isEmail = validEmail()
            } while (!isEmail!!)
        }
    }


}

