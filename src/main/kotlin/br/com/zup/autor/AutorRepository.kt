package br.com.zup.autor

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun existsByEmail(email: String) : Boolean
}