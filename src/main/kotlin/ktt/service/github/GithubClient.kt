package ktt.service.github

import ktt.service.github.models.GithubResponse
import ktt.service.github.models.GithubUser
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import org.springframework.web.client.RestTemplate

@Component
class GithubClient(val githubApi: String = "https://api.github.com") {
    val restTemplate = RestTemplate()

    @Async
    fun searchUsers(language: String, page: Int, perPage: Int): CompletableFuture<GithubResponse> {
        val query = "q=language:$language&page=$page&per_page=$perPage"
        val url = "$githubApi/search/users?$query"
        val response = restTemplate.getForObject<GithubResponse>(url, GithubResponse::class.java)

        return CompletableFuture.completedFuture(response)
    }

    @Async
    fun getUser(user: String): CompletableFuture<GithubUser> {
        val response = restTemplate.getForObject<GithubUser>("$githubApi/users/$user", GithubUser::class.java)

        return CompletableFuture.completedFuture(response)
    }
}
