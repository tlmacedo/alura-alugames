package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO(manager: EntityManager) : DAO<Jogo, JogoEntity>(manager, JogoEntity::class.java) {
    override fun toEntity(objeto: Jogo): JogoEntity {
        return JogoEntity(
            id = objeto.id,
            titulo = objeto.titulo,
            capa = objeto.capa,
            preco = objeto.preco,
            descricao = objeto.descricao
        )
    }

    override fun toModel(entity: JogoEntity): Jogo {
        return Jogo(
            id = entity.id,
            titulo = entity.titulo,
            capa = entity.capa,
            preco = entity.preco,
            descricao = entity.descricao
        )
    }

}