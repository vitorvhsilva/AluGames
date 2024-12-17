package br.com.alura.alugames.main

import br.com.alura.alugames.model.Gamer

fun main() {
    val gamer = Gamer("Vitor", "vitor@email.com")

    gamer.let {
        it.dataNasc = "28/03/2006"
        it.usuario = "vitorvhsilva"
    }
}