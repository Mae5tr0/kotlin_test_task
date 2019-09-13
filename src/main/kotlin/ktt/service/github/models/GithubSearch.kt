package ktt.service.github.models

data class GithubSearch(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<HashMap<String, String>>
)
