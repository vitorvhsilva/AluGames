package org.example.br.com.alura.alugames.modelo

data class Jogo(val titulo: String?, val capa: String?) {
    var descricao: String? = ""

    override fun toString(): String {
        return "Jogo(titulo='$titulo', capa='$capa', descricao='$descricao')"
    }


}