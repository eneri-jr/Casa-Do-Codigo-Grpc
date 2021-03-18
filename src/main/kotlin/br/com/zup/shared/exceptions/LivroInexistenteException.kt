package br.com.zup.shared.exceptions

import java.lang.RuntimeException

class LivroInexistenteException (mensagem: String?) : RuntimeException(mensagem) {
}