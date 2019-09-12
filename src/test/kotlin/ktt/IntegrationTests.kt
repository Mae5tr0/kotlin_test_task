package ktt

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {
//
//    @BeforeAll
//    fun setup() {
//        println(">> Setup")
//    }
//
//    @Test
//    fun `Assert blog page title, content and status code`() {
//        println(">> Assert blog page title, content and status code")
//        val entity = restTemplate.getForEntity<String>("/")
//        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(entity.body).contains("<h1>Blog</h1>")
//    }
//
//    @Test
//    fun `Assert article page title, content and status code`() {
//        println(">> TODO")
//    }
//
//    @AfterAll
//    fun teardown() {
//        println(">> Tear down")
//    }
//
//}