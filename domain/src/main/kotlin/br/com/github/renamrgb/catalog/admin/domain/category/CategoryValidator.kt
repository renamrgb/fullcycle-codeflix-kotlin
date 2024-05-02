package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.validation.Error
import br.com.github.renamrgb.catalog.admin.domain.validation.ValidationHandler
import br.com.github.renamrgb.catalog.admin.domain.validation.Validator


class CategoryValidator(
    private val category: Category,
    handler: ValidationHandler?
) : Validator(handler!!) {

    companion object {
        private const val NAME_MIN_LENGTH = 3
        private const val NAME_MAX_LENGTH = 255
    }

    override fun validate() {
        checkNameConstraints()
    }

    private fun checkNameConstraints() {
        val name = category.name
        if (name == null) {
            this.validationHandler().append(Error("'name' should not be null."))
            return
        }

        if (name.isBlank()) {
            this.validationHandler().append(Error("'name' should not be empty."))
            return
        }

        val nameLength = name.trim().length
        if (nameLength < NAME_MIN_LENGTH || nameLength > NAME_MAX_LENGTH) {
            this.validationHandler().append(Error("'name' must be between 3 and 255 characters."))
        }
    }
}
