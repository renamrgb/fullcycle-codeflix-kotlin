package br.com.github.renamrgb.catalog.admin.domain.category

import br.com.github.renamrgb.catalog.admin.domain.pagination.Pagination

interface CategoryGateway {

    fun create(category: Category): Category

    fun deleteById(id: CategoryID)

    fun findById(id: CategoryID): Category?

    fun update(category: Category): Category

    fun findAll(query: CategorySearchQuery): Pagination<Category>
}