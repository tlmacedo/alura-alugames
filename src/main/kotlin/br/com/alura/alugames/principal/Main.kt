package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso.\nDados do gamer: $gamer")

    do {

        print("Digite um código de jogo para buscar: ")

        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscarJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {

            if (informacaoJogo != null) {
                meuJogo = Jogo(
                    informacaoJogo.info.title,
                    informacaoJogo.info.thumb
                )
            }
            println("Titulo:${meuJogo!!.titulo}")
        }

        resultado.onFailure {
            println("Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {
            print("Se desejar inserir uma descrição personalizada: ")
            val mDescricaoPersonalizada = leitura.nextLine()
            if (mDescricaoPersonalizada.equals("", true)) {
                meuJogo!!.descricao = meuJogo!!.titulo
            } else {
                meuJogo!!.descricao = mDescricaoPersonalizada
            }

            gamer.jogosBuscados.add(meuJogo!!)
        }

        print("Deseja buscar um novo jogo? S/N: ")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:\n${gamer.jogosBuscados}")

    println("\n\nBusca finalizada com sucesso!")

}