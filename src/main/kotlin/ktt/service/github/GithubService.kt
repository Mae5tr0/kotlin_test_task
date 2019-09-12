package ktt.service.github

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ktt.dto.PagedResult
import ktt.service.github.models.GithubSearch
import ktt.service.github.models.GithubUser
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

const val GITHUB_API = "https://api.github.com"

@Service
class GithubService {
    fun searchUser(language : String, page : Int, per_page : Int) : PagedResult {
        val searchResult = findUsers("q=language:$language&page=$page&per_page=$per_page")

        val userLogins : List<String> = searchResult.items.mapNotNull { user -> user["login"] }
        val users = fetchUserDetailInfo(userLogins)

        return PagedResult(users, searchResult.total_count, per_page, page)
    }

    private fun fetchUserDetailInfo(users : List<String>) : Iterable<GithubUser> {
        var result = ArrayList<GithubUser>()

        val client = HttpClient.newHttpClient()
        val futures = users
            .stream()
            .map { user ->
                client
                    .sendAsync(
                        HttpRequest.newBuilder(URI("$GITHUB_API/users/$user"))
                            .GET()
                            .build(),
                        HttpResponse.BodyHandlers.ofString()
                    )
                    .thenApply { response -> response.body() }
                    .thenApply { rawUser -> parseUser(rawUser) }
                    .thenApply { githubUser -> result.add(githubUser) }
            }
            .collect(Collectors.toList())

        CompletableFuture.allOf(*futures.toTypedArray()).join()

        return result
    }

    private fun parseUser(json : String) : GithubUser {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(json)
    }

    private fun findUsers(query : String) : GithubSearch {
        val client = HttpClient.newHttpClient()
        val response = client.send(
            HttpRequest
                .newBuilder(URI("$GITHUB_API/search/users?$query"))
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )

        val mapper = jacksonObjectMapper()
        return mapper.readValue(response.body())
    }
}

