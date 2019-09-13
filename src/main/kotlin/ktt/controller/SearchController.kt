package ktt.controller

import io.swagger.annotations.*
import ktt.dto.PagedResult
import ktt.service.github.GithubService
import ktt.service.github.models.GithubUser
import org.springframework.web.bind.annotation.*

const val DEFAULT_PAGE = "0"
const val DEFAULT_LIMIT = "10"

@RestController
@Api(value = "User", description = "Rest API for user operations", tags = ["User API"])
class SearchController(
    val githubService: GithubService
) {

    @ApiOperation(value = "Search for GitHub users by the programming language")
    @GetMapping("/search")
    fun search(
        @RequestParam(name = "language") language: String,
        @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) page: Int,
        @RequestParam(name = "limit", defaultValue = DEFAULT_LIMIT) limit: Int
    ): PagedResult<GithubUser> = githubService.searchUsers(language, page, limit)
}