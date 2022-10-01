package br.com.dandrade.mercadolivro.validation

import br.com.dandrade.mercadolivro.service.CustumerEmailAvailable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(
    private val customerEmailAvailable: CustumerEmailAvailable
) : ConstraintValidator<EmailAvailable, String> {


    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return customerEmailAvailable.emailAvailable(value)
    }

}
