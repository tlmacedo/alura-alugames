package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO(manager: EntityManager) : DAO<Jogo>(manager) {
    override fun toEntity(objeto: Jogo) {
        return JogoEntity(objeto.titulo, objeto.capa, objeto.preco, objeto.descricao, objeto.id)
    }

    override fun getLista(): List<Jogo> {
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


}