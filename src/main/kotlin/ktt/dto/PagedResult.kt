package ktt.dto

data class PagedResult(
    val data: Iterable<Any>,
    val total: Int,
    val limit: Int,
    val page: Int
)