package ktt.service.github

import ktt.dto.PagedResult
import ktt.service.github.models.GithubUser
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.concurrent.CompletableFuture.allOf

@Service
class GithubService(private val client: GithubClient) {

    @Throws(Throwable::class)
    fun searchUsers(language: String, page: Int, per_page: Int): PagedResult<GithubUser> {
        val searchResult = client.searchUsers(language, page, per_page).get()

        val userLogins: List<String> = searchResult.items.mapNotNull { user -> user["login"] }
        val usersFutures: MutableList<CompletableFuture<GithubUser>> =
            userLogins
                .stream()
                .map { userLogin -> client.getUser(userLogin) }
                .collect(Collectors.toList())

        val allFutures = allOf(*usersFutures.toTypedArray())
        val users: List<GithubUser> = allFutures.thenApply {
            usersFutures
                .stream()
                .map { future -> future.join() }
                .collect(Collectors.toList())
        }.get()

        return PagedResult(users, searchResult.total_count, per_page, page)
    }
}

