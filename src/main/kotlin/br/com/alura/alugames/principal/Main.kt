package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
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
        print("\nDeseja inserir uma descrição personalizada? S/N: ")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            print("Insira a descrição personalizada para o jogo: ")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo!!.descricao = descricaoPersonalizada
        } else {
            meuJogo!!.descricao = meuJogo!!.titulo
        }
        println(meuJogo)
    }

    resultado.onSuccess {
        println("\n\nBusca finalizada com sucesso!")
    }

}