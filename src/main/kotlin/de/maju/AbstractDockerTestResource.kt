package de.maju


import de.maju.container.IContainerCreator
import de.maju.logging.DockerTestResourceLoggingHandler
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.GenericContainer

//class KGenericContainer: GenericContainer<KGenericContainer>()

abstract class AbstractDockerTestResource : QuarkusTestResourceLifecycleManager {

    open val listOfContainerCreator = emptyList<IContainerCreator<*>>()

    open val listOfOnStartHandler = listOf<IOnStartHandler>(
        DockerTestResourceLoggingHandler()
    )

    open val listOfOnStopHandler = listOf<IOnStopHandler>(
        DockerTestResourceLoggingHandler()
    )

    open val listOfContainer = mutableListOf<GenericContainer<*>>()


    override fun start(): MutableMap<String, String> {
        val resultConfig = mutableMapOf<String, String>()

        for (creator in listOfContainerCreator) {
            val config = creator.getConfig()
            val container = creator.getContainer()

            if (!container.isRunning()) container.start()

            with(listOfContainer) {
                if (!contains(container)) add(container)
            }

            listOfOnStartHandler.forEach {
                it.onStart(container)
            }

            resultConfig.putAll(config)
        }


        println("Starting with config: $resultConfig")
        return resultConfig
    }


    override fun stop() {
        listOfContainer.forEach { container ->
            if (container.isRunning()) container.stop()

            listOfOnStopHandler.forEach {
                it.onStop(container)
            }

        }
    }


}