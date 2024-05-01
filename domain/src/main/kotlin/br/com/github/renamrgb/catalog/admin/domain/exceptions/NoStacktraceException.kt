package br.com.github.renamrgb.catalog.admin.domain.exceptions

open class NoStacktraceException(message: String, cause: Throwable? = null) : RuntimeException(message, cause, true, false)
