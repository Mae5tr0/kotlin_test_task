package ktt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

@RestController
@Api(value = "user", description = "Rest API for user operations", tags = arrayOf("User API"))
//@Api(
//    value = "user",
//    description = "Search for GitHub users by the programming language they use",
//    tags = arrayOf("Search API")
//)
class GreetingController {

    @GetMapping("/search")
    fun search() =
        "Greetings from Spring Boot!"

}