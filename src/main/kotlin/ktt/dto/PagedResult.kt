package ktt.dto

data class PagedResult<T>(
    val data: List<T>,
    val total: Int,
    val limit: Int,
    val page: Int
)