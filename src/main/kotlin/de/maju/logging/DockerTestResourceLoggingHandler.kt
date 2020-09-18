package de.maju.logging

import de.maju.IOnStartHandler
import de.maju.IOnStopHandler
import mu.KotlinLogging
import org.testcontainers.containers.GenericContainer

val logger = KotlinLogging.logger("DockerTestResource")

class DockerTestResourceLoggingHandler : IOnStartHandler, IOnStopHandler {

    override fun onStart(container: GenericContainer<*>) {
        logger.info { "Starting ${container.getDockerImageName()}" }
    }

    override fun onStop(container: GenericContainer<*>) {
        logger.info { "Stopping ${container.getDockerImageName()}" }
    }

}