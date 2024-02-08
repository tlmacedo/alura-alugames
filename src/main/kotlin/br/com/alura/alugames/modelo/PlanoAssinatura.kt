package br.com.alura.alugames.modelo

class PlanoAssinatura(
    tipo: String,
    val mensalidade: Double,
    val jogosIncluidos: Int
) : Plano(tipo) {
    override fun obterValor(aluguel: Aluguel): Double {

        return super.obterValor(aluguel)
    }
}