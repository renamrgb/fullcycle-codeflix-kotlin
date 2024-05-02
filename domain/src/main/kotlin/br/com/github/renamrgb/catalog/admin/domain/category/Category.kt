package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.AggregateRoot
import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler
import java.time.Instant

class Category private constructor(
    id: CategoryID,
    var name: String?,
    var description: String?,
    var active: Boolean?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
    var deletedAt: Instant?
) : AggregateRoot<CategoryID>(id) {
    companion object {
        fun newCategory(name: String?, description: String?, active: Boolean?): Category {
            val now = Instant.now()
            val deletedAt = if (active!!) null else now
            return Category(
                id = CategoryID.unique(),
                name = name,
                description = description,
                active = active,
                createdAt = now,
                updatedAt = now,
                deletedAt = deletedAt
            )
        }
    }

    fun deactivate(): Category = this.also {
        val now = Instant.now()
        if (it.deletedAt == null) {
            it.deletedAt = now
        }
        it.updatedAt = now
        it.active = false
    }

    fun activate(): Category = this.also {
        it.deletedAt = null
        it.active = true
        it.updatedAt = Instant.now()
    }


    fun update(name: String?, description: String?, active: Boolean): Category = this.also {
        if (active) {
            it.activate()
        } else {
            it.deactivate()
        }
        it.name = name
        it.description = description
        it.updatedAt = Instant.now()
    }

    override fun validate(handler: ValidationHandler) {
        CategoryValidator(this, handler).validate()
    }
}