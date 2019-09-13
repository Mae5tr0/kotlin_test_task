package ktt.dto

data class PagedResult(
    val data: List<Any>,
    val total: Int,
    val limit: Int,
    val page: Int
)