package de.maju.keycloak

import dasniko.testcontainers.keycloak.KeycloakContainer
import de.maju.container.keycloak.KeycloakDefaultContainerCreator
import de.maju.container.keycloak.KeycloakDefaultHandler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KeycloakContainerCreatorTest {


    private val keycloakContainerCreator = KeycloakDefaultContainerCreator()


    @Test
    fun testDefaultConfig() {
        var container = keycloakContainerCreator.container
        Assertions.assertNotNull(container)


        val keycloakContainer = container
        assert(!keycloakContainer.exposedPorts.contains(8181))

        container = keycloakContainerCreator.createContainer() as KeycloakContainer
        assert(container.exposedPorts.contains(8181))

        println(keycloakContainer)

    }


}