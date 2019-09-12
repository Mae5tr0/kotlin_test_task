package ktt.service

import org.junit.Test
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import ktt.service.github.GithubService
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0)
public class GithubServiceTests(private var service: GithubService) {

	@Before
	fun setup() : Unit {
        service = GithubService(("http://localhost:${this.environment.getProperty("wiremock.server.port")}")
	}

    @Test
    fun testSearchUser() : Unit {
        val github : GithubService = GithubService("http://localhost:8089")

        stubFor(
            get(urlEqualTo("/my/resource"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")
                )
        )

//        val result = myHttpServiceCallingObject.doSomething()
//        assertTrue(result.wasSuccessful())

//        verify(
//            postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
//                .withRequestBody(matching(".*<message>1234</message>.*"))
//                .withHeader("Content-Type", notMatching("application/json"))
//        )

        println("super puper")
    }
}