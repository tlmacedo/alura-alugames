package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo

class JogosDAO {
    fun getJogos(): List<Jogo> {
        val listaJogos = mutableListOf<Jogo>()
        val conexao = Banco.factoryConnection()

        if (conexao != null) {
            try {
                val statement = conexao.createStatement()
                val resultado = statement.executeQuery("SELECT * from jogos")

                while (resultado.next()) {
                    val id = resultado.getInt("id")
                    val capa = resultado.getString("capa")
                    val descricao = resultado.getString("descricao")
                    val preco = resultado.getBigDecimal("preco")
                    val titulo = resultado.getString("titulo")

                    val jogo = Jogo(titulo, capa, preco, descricao)
                    jogo.id = id
                    listaJogos.add(jogo)
                }

                statement.close()
            } finally {
                conexao.close()
            }
        }
        return listaJogos
    }

    fun adicionarJogo(jogo: Jogo) {

        val conexao = Banco.factoryConnection()
        val insert = "INSERT INTO JOGOS (TITULO, CAPA, PRECO, DESCRICAO) VALUES (?, ?, ?, ?)"

        if (conexao != null) {
            try {
                val statement = conexao.prepareStatement(insert)
                statement.setString(1, jogo.titulo)
                statement.setString(2, jogo.capa)
                statement.setDouble(3, jogo.preco.toDouble())
                statement.setString(4, jogo.descricao)
                statement.executeUpdate()

                statement.close()
            } finally {
                conexao.close()
            }
        }

    }

}