package br.com.github.renamrgb.catalog.admin.domain.exceptions

import br.com.github.renamrgb.catalog.admin.domain.validation.Error

class DomainException private constructor(override val message: String, val errors: List<Error>) : NoStacktraceException(message) {
    companion object {

        fun with(errors: List<Error>): DomainException {
            return DomainException("", errors)
        }

        fun with(error: Error): DomainException {
            return DomainException("", listOf(error))
        }
    }
}
