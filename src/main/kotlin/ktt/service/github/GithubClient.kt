package ktt.service.github

import ktt.service.github.models.GithubSearch
import ktt.service.github.models.GithubUser
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import org.springframework.web.client.RestTemplate

const val GITHUB_API = "https://api.github.com"

@Component
class GithubClient(val apiHost: String = GITHUB_API) {
    var restTemplate = RestTemplate()

    @Async
    fun searchUsers(language: String, page: Int, per_page: Int): CompletableFuture<GithubSearch> {
        val query = "q=language:$language&page=$page&per_page=$per_page"
        val url = "$apiHost/search/users?$query"
        val response = restTemplate.getForObject<GithubSearch>(url, GithubSearch::class.java)

        return CompletableFuture.completedFuture(response)
    }

    @Async
    fun getUser(user: String): CompletableFuture<GithubUser> {
        val response = restTemplate.getForObject<GithubUser>("$apiHost/users/$user", GithubUser::class.java)

        return CompletableFuture.completedFuture(response)
    }
}
