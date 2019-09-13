package ktt.service

import junit.framework.Assert.assertEquals
import ktt.service.github.GithubClient
import org.junit.Test
import ktt.service.github.GithubService
import ktt.service.github.models.GithubSearch
import ktt.service.github.models.GithubUser
import org.junit.Before
import java.util.concurrent.CompletableFuture
import org.mockito.Mockito.`when`
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.mockito.Mockito.mock
import java.io.FileInputStream

public class GithubServiceTests {
    private lateinit var client : GithubClient
    private lateinit var githubService: GithubService

    @Before
    fun setup() {
        client = mock(GithubClient::class.java)
        githubService = GithubService(client)
    }

    @Test
    fun testSearchUser() : Unit {
        `when`(client.searchUsers("haskell", 0, 2))
            .thenReturn(CompletableFuture.completedFuture(
                searchResult("search_by_language")
            ))
        `when`(client.getUser("ekmett"))
            .thenReturn(CompletableFuture.completedFuture(userInfo("ekmett")))
        `when`(client.getUser("sdiehl"))
            .thenReturn(CompletableFuture.completedFuture(userInfo("sdiehl")))

        val result = githubService.searchUsers("haskell", 0, 2)

        assertEquals(11749, result.total)
        assertEquals(2, result.limit)
        assertEquals(0, result.page)
        assertEquals(2, result.data.size)
        assertEquals("ekmett", (result.data[0] as GithubUser).login)
        assertEquals("sdiehl", (result.data[1] as GithubUser).login)
    }

    private fun searchResult(filename : String) : GithubSearch {
        val mapper = jacksonObjectMapper()
        val fileStream = FileInputStream("src/test/resources/github_responses/$filename.json")
        return mapper.readValue(fileStream)
    }

    private fun userInfo(login : String) : GithubUser {
        val mapper = jacksonObjectMapper()
        val fileStream = FileInputStream("src/test/resources/github_responses/user_$login.json")
        return mapper.readValue(fileStream)
    }
}