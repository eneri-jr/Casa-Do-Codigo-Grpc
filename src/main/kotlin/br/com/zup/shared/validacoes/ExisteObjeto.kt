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
@Constraint(validatedBy = [ExisteObjetoValidator::class])
annotation class ExisteObjeto(
    val message: String = "Id informado não esta cadastrado no banco",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val campo: String,
    val tabela: String
)

@Singleton
open class ExisteObjetoValidator(val em: EntityManager) : ConstraintValidator<ExisteObjeto, Any> {

    @Transactional
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<ExisteObjeto>,
        context: ConstraintValidatorContext
    ): Boolean {

        val campo = annotationMetadata.stringValue("campo").get()
        val tabela = annotationMetadata.stringValue("tabela").get()

        val query = em.createQuery("SELECT 1 FROM $tabela WHERE $campo = :value")
        query.setParameter("value", value)
        val result = query.resultList

        return !result!!.isEmpty()
    }

}