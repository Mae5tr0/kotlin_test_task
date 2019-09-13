package ktt.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import ktt.service.github.GithubService
import org.springframework.web.bind.annotation.*

// TODO move to central configuration
const val DEFAULT_PAGE = "0"
const val DEFAULT_LIMIT = "10"

// TODO error processing
// TODO documentation
@RestController
@Api(value = "user", description = "Rest API for user operations", tags = arrayOf("User API"))
//@Api(
//    value = "user",
//    description = "Search for GitHub users by the programming language they use",
//    tags = arrayOf("Search API")
//)
class SearchController(
  val githubService: GithubService
) {

//    @ApiOperation(
//        httpMethod = "GET",
//        value = "Search for GitHub users by the programming language",
//        response = String.class,
//        responseContainer = "List"
//    )
//    @ApiResponses(value = {
//        @ApiResponse(code = 404, message = "Countries not found"),
//        @ApiResponse(code = 500, message = "The countries could not be fetched")
//    })
    @GetMapping("/search")
    fun search(
        @RequestParam(name = "language") language: String,
        @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) page: Int,
        @RequestParam(name = "limit", defaultValue = DEFAULT_LIMIT) limit: Int
    ) = githubService.searchUsers(language, page, limit)
}