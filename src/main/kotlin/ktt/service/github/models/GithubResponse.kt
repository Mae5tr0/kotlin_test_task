package ktt.service.github.models

data class GithubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<HashMap<String, String>>
)
