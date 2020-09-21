package de.maju

import de.maju.container.postgres.PostgresContainerCreatorImpl
import de.maju.container.postgres.PostgresInitHandler
import de.maju.logging.DockerTestResourceLoggingHandler


class DockerTestResource : AbstractDockerTestResource() {

    private val loggingHandler = DockerTestResourceLoggingHandler()

    private val postgresInitHandler = PostgresInitHandler.default()

    override val listOfContainerCreator = listOf(
        PostgresContainerCreatorImpl(postgresInitHandler)
    )

    override val listOfOnStartHandler: List<IOnStartHandler> = listOf(loggingHandler)

    override val listOfOnStopHandler: List<IOnStopHandler> = listOf(loggingHandler)

}
