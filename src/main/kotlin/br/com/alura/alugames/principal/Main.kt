package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import br.com.alura.alugames.utilitario.transformarEmIdade
import java.util.*


fun main() {

	val leitura = Scanner(System.`in`)
	val gamer = Gamer.criarGamer(leitura)
	println("Cadastro concluido com sucesso.\nDados do gamer: $gamer idade=${gamer.dataNascimento?.transformarEmIdade()}")

	do {

		print("Digite um código de jogo para buscar: ")

		val busca = leitura.nextLine()

		val buscaApi = ConsumoApi()
		val informacaoJogo = buscaApi.buscarJogo(busca)
//		val informacaoJogo = buscaApi.buscarGamers()

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

	println("\nJogos ordenados por título:")
	gamer.jogosBuscados.sortBy {
		it.titulo
	}

	var contador = 1
	gamer.jogosBuscados.forEach {
		val myId = String.format("%02d", contador++)
		println("[$myId]Título: ${it.titulo}")
	}

//    val jogosFiltrados = gamer.jogosBuscados.filter {
//        it.titulo.contains("batman", true) ?: false
//    }
//    println("\nJogos filtrados:\n$jogosFiltrados")

	println("\nDeseja excluir algum jogo da lista original?\nSe sim informe a posição do jogo: ")
	val opcao = leitura.nextLine()
	if (opcao.lowercase() != "n" && !opcao.isNullOrEmpty()) {
		val posicao = opcao.toIntOrNull()
		gamer.jogosBuscados.removeAt(posicao!! - 1)
	}

	println("Lista atualizada:\n${gamer.jogosBuscados}")

	println("\n\nBusca finalizada com sucesso!")

}