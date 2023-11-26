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
        print("\nSe desejar inserir uma descrição personalizada: ")
        val mDescricaoPersonalizada = leitura.nextLine()
        if (mDescricaoPersonalizada.equals("", true)) {
            meuJogo!!.descricao = meuJogo!!.titulo
        } else {
            meuJogo!!.descricao = mDescricaoPersonalizada
        }
        println(meuJogo)
    }

    resultado.onSuccess {
        println("\n\nBusca finalizada com sucesso!")
    }

}