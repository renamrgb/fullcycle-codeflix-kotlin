package br.com.github.renamrgb.catalog.admin.domain

import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler
import java.util.*

abstract class Entity<ID : Identifier> protected constructor(id: ID) {
    val id: ID

    init {
        Objects.requireNonNull(id, "'id' should not be null.")
        this.id = id
    }

    abstract fun validate(handler: ValidationHandler)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val entity = other as Entity<*>
        return id == entity.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}
