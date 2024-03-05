package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Gamer
import javax.persistence.EntityManager

class GamesDAO(private val manager: EntityManager) {

    fun getGamers(): List<Gamer> {
        val query = manager.createQuery("from GamerEntity", GamerEntity::class.java)
        return query.resultList.map { entity ->
            Gamer(entity.nome, entity.email, entity.dataNascimento, entity.usuario.toString(), entity.id)
        }
    }

    fun adicionaGamer(gamer: Gamer) {
        val entity = GamerEntity(gamer.id, gamer.nome, gamer.email, gamer.dataNascimento, gamer.usuario)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

}