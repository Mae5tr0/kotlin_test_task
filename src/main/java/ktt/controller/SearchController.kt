package ktt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/search")
    fun search() =
        "Greetings from Spring Boot!"

}