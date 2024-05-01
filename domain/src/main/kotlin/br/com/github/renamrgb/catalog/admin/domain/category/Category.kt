package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.AggregateRoot
import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler
import java.time.Instant

class Category private constructor(
    id: CategoryID,
    val name: String?,
    val description: String?,
    val active: Boolean?,
    val createdAt: Instant?,
    val updatedAt: Instant?,
    val deletedAt: Instant?
) : AggregateRoot<CategoryID>(id) {
    companion object {
        fun newCategory(name: String?, description: String?, active: Boolean?): Category {
            val now = Instant.now()
            return Category(
                id = CategoryID.unique(),
                name = name,
                description = description,
                active = active,
                createdAt = now,
                updatedAt = now,
                deletedAt = null
            )
        }
    }

    override fun validate(handler: ValidationHandler) {
        CategoryValidator(this, handler).validate()
    }
}