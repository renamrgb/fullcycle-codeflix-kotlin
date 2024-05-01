package br.com.github.renamrgb.catalog.admin.application

import br.com.github.renamrgb.catalog.admin.domain.category.Category

class UseCase {

    fun execute(): Category {
        return Category()
    }
}