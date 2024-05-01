package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.validation.Error
import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler
import br.com.github.renamrgb.catalog.admin.domain.validation.Validator

class CategoryValidator(
    private val category: Category,
    handler: ValidationHandler?
) : Validator(handler!!) {
    override fun validate() {
        if (category.name == null) {
            this.validationHandler().append(Error("'name' should not be null."))
        }
    }
}
