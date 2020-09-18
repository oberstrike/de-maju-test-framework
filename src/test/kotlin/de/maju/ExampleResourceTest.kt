package de.maju

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(DockerTestResource::class)
class ExampleResourceTest {

    @Test
    fun testHelloEndpoint() {
        assert(true)

    }

}