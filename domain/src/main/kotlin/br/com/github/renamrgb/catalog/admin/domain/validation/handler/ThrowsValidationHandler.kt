package br.com.github.renamrgb.catalog.admin.domain.validation.handler

import br.com.github.renamrgb.catalog.admin.domain.exceptions.DomainException
import br.com.github.renamrgb.catalog.admin.domain.validation.Error
import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler

class ThrowsValidationHandler : ValidationHandler {

    override fun append(error: Error): ValidationHandler {
        throw DomainException.with(error)
    }


    override fun append(handler: ValidationHandler): ValidationHandler {
        throw DomainException.with(handler.getErrors()!!)
    }


    override fun append(validation: ValidationHandler.Validation): ValidationHandler {
        try {
            validation.validate()
        } catch (ex: Exception) {
            throw DomainException.with(Error(ex.message!!))
        }
        return this
    }

    override fun getErrors(): List<Error> {
        return emptyList()
    }
}
