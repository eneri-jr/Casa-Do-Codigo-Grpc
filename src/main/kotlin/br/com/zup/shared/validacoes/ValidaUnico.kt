package br.com.zup.shared.validacoes

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR, FIELD, VALUE_PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidaUnicoValidator::class])
annotation class ValidaUnico(
    val message: String = "O valor deste campo deve ser único, já existe este valor cadastrado",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val campo: String,
    val tabela: String
)

@Singleton
open class ValidaUnicoValidator(val em: EntityManager) : ConstraintValidator<ValidaUnico, Any> {

    @Transactional
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<ValidaUnico>,
        context: ConstraintValidatorContext
    ): Boolean {

        if(value.toString().isNullOrBlank()) {
            return true
        }

        val campo = annotationMetadata.stringValue("campo").get()
        val tabela = annotationMetadata.stringValue("tabela").get()

        val query = em.createQuery("SELECT 1 FROM $tabela WHERE $campo = :value")
        query.setParameter("value", value.toString())
        val result = query.resultList

        return result!!.isEmpty()
    }

}