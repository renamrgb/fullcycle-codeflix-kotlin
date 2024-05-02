package br.com.github.renamrgb.catalog.admin.domain.pagination

data class Pagination<T>(
    val currentPage: Int,
    val perPage: Int,
    val total: Long,
    val items: List<T>
)
