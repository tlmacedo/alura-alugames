import br.com.alura.alugames.servicos.ConsumoApi

fun main(){
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscarGamers()

    println(listaGamers)
}