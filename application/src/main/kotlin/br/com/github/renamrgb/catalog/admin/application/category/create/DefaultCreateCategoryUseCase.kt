package br.com.github.renamrgb.catalog.admin.application.category.create

import br.com.github.renamrgb.catalog.admin.domain.category.Category
import br.com.github.renamrgb.catalog.admin.domain.category.CategoryGateway
import br.com.github.renamrgb.catalog.admin.domain.validation.handler.ThrowsValidationHandler

class DefaultCreateCategoryUseCase(val categoryGateway: CategoryGateway) : CreateCategoryUseCase() {

    override fun execute(input: CreateCategoryCommand): CreateCategoryOutput {
        return Category.newCategory(input.name, input.description, input.isActive).apply {
            validate(ThrowsValidationHandler())
            categoryGateway.create(this)
        }.let { CreateCategoryOutput.from(it) }
    }
}
