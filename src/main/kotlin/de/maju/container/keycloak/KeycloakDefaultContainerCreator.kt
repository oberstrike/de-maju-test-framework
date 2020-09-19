package de.maju.container.keycloak

import dasniko.testcontainers.keycloak.KeycloakContainer
import de.maju.container.AbstractContainerCreator
import de.maju.container.IOnConfigCreatedHandler
import de.maju.container.IOnContainerCreatedHandler

val defaultKeycloakHandler = KeycloakDefaultHandler(
    KeycloakDefaultHandler.default()
)

class KeycloakDefaultContainerCreator() :
    AbstractContainerCreator<KeycloakContainer>() {

    override val container: KeycloakContainer = KeycloakContainer("quay.io/keycloak/keycloak:latest")

    override val onContainersCreatedHandlers: List<IOnContainerCreatedHandler<KeycloakContainer>> = listOf(defaultKeycloakHandler)

    override val onConfigCreatedHandlers: List<IOnConfigCreatedHandler<KeycloakContainer>> = listOf(defaultKeycloakHandler)

}
