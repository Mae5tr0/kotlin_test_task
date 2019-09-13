package ktt.service.github.models

data class GithubUser(
    val id: Long,
    val login: String = "",
    val name: String = "",
    val avatar_url: String = "",
    val followers: Int = 0
)
