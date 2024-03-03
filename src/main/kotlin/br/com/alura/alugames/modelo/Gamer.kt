package br.com.alura.alugames.modelo

import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

data class Gamer(
    var nome: String,
    var email: String
) : Recomendavel {
    var dataNascimento: String? = null
    var idInterno: String? = null
        private set

    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank())
                criarIdInterno()
        }
    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo>()
    val jogosAlugados = mutableListOf<Aluguel>()
    private val listaNotas = mutableListOf<Int>()
    override val media: BigDecimal
        get() {
            try {
                return listaNotas.average().toBigDecimal()
            } catch (ex: NumberFormatException) {
                return BigDecimal("0.0")
            }
        }

    val jogosRecomendados = mutableListOf<Jogo>()

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10) {
            println("Nota inválida. Insira uma nota entre 1 e 10")
        } else {
            listaNotas.add(nota)
        }
    }

    fun recomendarJogo(jogo: Jogo, nota: Int) {
        jogo.recomendar(nota)
        jogosRecomendados.add(jogo)
    }

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
        return "Gamer:" +
                "\nnome=${nome}" +
                "\nemail=${email}" +
                "\ndataNascimento=${dataNascimento}" +
                "\nusuario=${usuario}" +
                "\nidInterno=${idInterno}" +
                "\nreputação: ${media}"
    }

    private fun criarIdInterno() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        this.idInterno = "${usuario}#$tag"

    }

    fun alugaJogo(jogo: Jogo, periodo: Periodo): Aluguel {
        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)
        return aluguel
    }

    fun jogosDoMes(mes: Int): List<Jogo> {
        return jogosAlugados.filter { aluguel -> aluguel.periodo.dataInicial.monthValue == mes }
            .map { aluguel -> aluguel.jogo }
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

        private fun imprimirCabecalho(titulo: String = "AluGames") {
            print(
                "*******************************************************\n" +
                        "*             Boas Vindas ao $titulo                 *\n" +
                        "*******************************************************\n" +
                        "Vamos fazer seu cadastro?\n"
            )
        }

        private fun getNameNewUser() {
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

