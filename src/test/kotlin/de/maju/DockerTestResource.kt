package de.maju

import de.maju.container.keycloak.KeycloakDefaultContainerCreator
import de.maju.container.postgres.PostgresContainerCreator
import de.maju.container.postgres.PostgresInitHandler
import de.maju.logging.DockerTestResourceLoggingHandler


class DockerTestResource : AbstractDockerTestResource() {

    private val loggingHandler = DockerTestResourceLoggingHandler()

    override val listOfContainerCreator = listOf(
        PostgresContainerCreator(),
        KeycloakDefaultContainerCreator()
    )

    override val listOfOnStartHandler: List<IOnStartHandler> = listOf(loggingHandler)

    override val listOfOnStopHandler: List<IOnStopHandler> = listOf(loggingHandler)

}