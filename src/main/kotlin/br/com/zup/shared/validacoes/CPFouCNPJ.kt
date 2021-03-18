package br.com.zup.shared.validacoes

import io.micronaut.core.annotation.AnnotationValue
import javax.inject.Singleton
import javax.validation.Constraint
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CPFouCNPJValidator::class])
annotation class CPFouCNPJ(
    val message: String = "Documento inválido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class CPFouCNPJValidator : ConstraintValidator<CPFouCNPJ, Any> {
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<CPFouCNPJ>,
        context: ConstraintValidatorContext
    ): Boolean {

        val cpfValido: Boolean =  CPFValidator().run {
            initialize(null)
            isValid(value!!.toString(), null)
        }

        val cnpjValido: Boolean = CNPJValidator().run {
            initialize(null)
            isValid(value!!.toString(), null)
        }

        return cpfValido || cnpjValido
    }

}