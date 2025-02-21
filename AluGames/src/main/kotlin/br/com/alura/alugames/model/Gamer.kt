package br.com.alura.alugames.model

import org.example.br.com.alura.alugames.modelo.Jogo
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNasc: String? = null
    var usuario: String? = null
        set (value) {
            field = value

            if (idInterno.isNullOrBlank()) criarIdInterno()
        }
    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo>()

    constructor(nome: String, email: String, dataNasc: String, usuario: String):
            this(nome, email) {
        this.dataNasc = dataNasc
        this.usuario = usuario
        criarIdInterno()
    }

    init {
        if (this.nome.isNullOrBlank()) throw IllegalArgumentException("Nome invalido!")
        this.email = validarEmail()
    }


    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String{
        val regex = Regex(pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }
        throw IllegalArgumentException("Email inválido")
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNasc=$dataNasc, usuario=$usuario, idInterno=$idInterno)"
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            }

            return Gamer (nome, email)
        }
    }
}
