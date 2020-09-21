package de.maju.container.keycloak

import dasniko.testcontainers.keycloak.KeycloakContainer
import de.maju.container.AbstractContainerCreator
import de.maju.container.IOnConfigCreatedHandler
import de.maju.container.IOnContainerCreatedHandler

private val defaultKeycloakHandler = KeycloakDefaultHandler(
    KeycloakDefaultHandler.defaultConfig()
)

class KeycloakDefaultContainerCreatorImpl() :
    AbstractContainerCreator<KeycloakContainer>() {

    override val container: KeycloakContainer = KeycloakContainer("quay.io/keycloak/keycloak:latest")

    override val onContainersCreatedHandlers: List<IOnContainerCreatedHandler<KeycloakContainer>> = listOf(defaultKeycloakHandler)

    override val onConfigCreatedHandlers: List<IOnConfigCreatedHandler<KeycloakContainer>> = listOf(defaultKeycloakHandler)

}


private val newKeycloakHandler = object : IKeycloakDefaultHandler {
    override fun withConfig(): MutableMap<String, String> {
        TODO("Not yet implemented")
    }

    override fun onContainerCreated(container: KeycloakContainer) {
        TODO("Not yet implemented")
    }
}
