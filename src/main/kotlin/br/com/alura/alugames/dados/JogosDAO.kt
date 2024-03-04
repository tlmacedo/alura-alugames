package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO(val manager: EntityManager) {

    fun getJogos(): List<Jogo> {
        val query = manager.createQuery("from JogoEntity ", JogoEntity::class.java)
        return query.resultList.map { entity ->
            Jogo(
                titulo = entity.titulo,
                capa = entity.capa,
                preco = entity.preco,
                descricao = entity.descricao.toString(),
                id = entity.id
            )
        }
    }

    fun adicionarJogo(jogo: Jogo) {
        val entity = JogoEntity(
            titulo = jogo.titulo,
            capa = jogo.capa,
            preco = jogo.preco,
            descricao = jogo.descricao.toString()
        )
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

}