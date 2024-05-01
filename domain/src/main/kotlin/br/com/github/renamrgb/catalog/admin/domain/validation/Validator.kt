package br.com.github.renamrgb.catalog.admin.domain.validation

abstract class Validator protected constructor(private val handler: ValidationHandler) {

    abstract fun validate()

    protected fun validationHandler(): ValidationHandler {
        return handler
    }
}
