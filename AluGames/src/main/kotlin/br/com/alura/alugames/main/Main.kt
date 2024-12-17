package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.service.ConsumoAPI
import org.example.br.com.alura.alugames.modelo.Jogo
import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(scanner)
    println("Cadastro feito com sucesso")

    do {
        println("Digite o id do jogo a ser buscado")
        val id = scanner.nextLine()

        val consumoAPI = ConsumoAPI()

        val informacaoJogo = consumoAPI.buscarJogo(id)
        var meuJogo = Jogo(informacaoJogo?.info?.title, informacaoJogo?.info?.thumb)

        println("Deseja inserir uma descricao personalizada? S/N")
        val opcao = scanner.nextLine()

        if (!opcao.equals("s", true)) {
            meuJogo.descricao = meuJogo.titulo
        } else {
            println("Insira a descricao personalizada:")
            val descricao = scanner.nextLine()
            meuJogo.descricao = descricao
        }


        gamer.jogosBuscados.add(meuJogo)

        println("Deseja continuar? S/N")
        val continuar = scanner.nextLine()
    } while (continuar.equals("s", false))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por titulo:")
    gamer.jogosBuscados.sortBy {
        it.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo: ${it.titulo}")
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it.titulo?.contains("batman", true) ?: false
    }

    println("Finalizado com sucesso")
}