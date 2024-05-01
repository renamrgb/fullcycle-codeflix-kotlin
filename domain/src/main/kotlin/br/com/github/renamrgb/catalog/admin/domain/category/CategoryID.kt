package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.Identifier
import java.util.*

class CategoryID private constructor(value: String) : Identifier() {
    val value: String

    init {
        Objects.requireNonNull(value)
        this.value = value
    }

    companion object {
        fun unique(): CategoryID {
            return from(UUID.randomUUID())
        }

        fun from(id: String): CategoryID {
            return CategoryID(id)
        }

        fun from(id: UUID): CategoryID {
            return CategoryID(id.toString().lowercase())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as CategoryID
        return value == that.value
    }

    override fun hashCode(): Int {
        return Objects.hashCode(value)
    }
}
