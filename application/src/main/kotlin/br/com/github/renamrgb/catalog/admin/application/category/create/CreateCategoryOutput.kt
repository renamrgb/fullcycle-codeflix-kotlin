package br.com.github.renamrgb.catalog.admin.application.category.create

import br.com.github.renamrgb.catalog.admin.domain.category.Category
import br.com.github.renamrgb.catalog.admin.domain.category.CategoryID

data class CreateCategoryOutput(
    val id: CategoryID
) {
    companion object {
        fun from(category: Category): CreateCategoryOutput {
            return CreateCategoryOutput(category.id)
        }
    }
}
