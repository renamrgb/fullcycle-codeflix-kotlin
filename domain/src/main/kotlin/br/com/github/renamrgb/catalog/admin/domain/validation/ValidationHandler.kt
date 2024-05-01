package br.com.github.renamrgb.catalog.admin.domain.validation

/**
 * Interface for handling validation errors.
 */
interface ValidationHandler {

    fun append(error: Error): ValidationHandler

    fun append(handler: ValidationHandler): ValidationHandler

    fun append(validation: Validation): ValidationHandler

    fun getErrors(): List<Error>?

    fun hasError(): Boolean = getErrors()?.isNotEmpty() ?: false

    interface Validation {
        fun validate()
    }
}