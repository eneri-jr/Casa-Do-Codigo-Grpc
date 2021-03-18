package br.com.zup.shared.validacoes

import br.com.zup.venda.cadastra.NovaVenda
import io.micronaut.core.annotation.AnnotationValue
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.validation.Constraint
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.transaction.Transactional
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidaPaisEstadoValidator::class])
annotation class ValidaPaisEstado(
    val message: String = "O estado não pertence ao Pais informado",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
open class ValidaPaisEstadoValidator(val em: EntityManager) : ConstraintValidator<ValidaPaisEstado, NovaVenda> {

    @Transactional
    override fun isValid(
        value: NovaVenda,
        annotationMetadata: AnnotationValue<ValidaPaisEstado>,
        context: ConstraintValidatorContext
    ): Boolean {

        if (value.estadoId.toString().equals("0")) {
            val query = em.createQuery("SELECT 1 FROM Estado WHERE pais_id = :value")
            query.setParameter("value", value.paisId)
            val result = query.resultList
            return result.isEmpty()
        }

        val query = em.createQuery("SELECT 1 FROM Estado WHERE pais_id = :value AND id = :value2")
        query.setParameter("value2", value.estadoId)
        query.setParameter("value", value.paisId)
        val result = query.resultList
        return !result.isEmpty()
    }
}

