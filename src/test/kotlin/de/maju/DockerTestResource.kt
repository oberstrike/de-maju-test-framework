package de.maju

import de.maju.container.KeycloakContainerCreator


interface TestClient

class DockerTestResource : AbstractDockerTestResource() {

    class CustomConfig : KeycloakContainerCreator.KeycloakContainerConfig() {
        override var port = 5555

        override fun withConfig(): MutableMap<String, String> {
            return mutableMapOf(
                getQuarkusRestClientUrl(TestClient::class.java),
                getQuarkusOIDCAuthServerUrlConfig()
            )
        }

    }


    override val listOfContainerCreator = listOf(
        KeycloakContainerCreator(
            CustomConfig()
        )
    )
}