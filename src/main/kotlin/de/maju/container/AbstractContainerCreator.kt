package de.maju.container

import org.testcontainers.containers.GenericContainer

abstract class AbstractContainerCreator<T : GenericContainer<T>> : IContainerCreator<T> {

    abstract val container: T

    open val onContainersCreatedHandlers: List<IOnContainerCreatedHandler<T>> = emptyList()

    open val onConfigCreatedHandlers: List<IOnConfigCreatedHandler<T>> = emptyList()

    override fun createContainer(): GenericContainer<T> {
        onContainersCreatedHandlers.forEach {
            it.onContainerCreated(container)
        }
        return container
    }

    override fun getConfig(): MutableMap<String, String> {
        val result = mutableMapOf<String, String>()
        for (handler in onConfigCreatedHandlers) {
            result.putAll(handler.withConfig())
        }
        return result
    }

}

interface IOnContainerCreatedHandler<T> {
    fun onContainerCreated(container: T)
}

interface IOnConfigCreatedHandler<T> {
    fun withConfig(): MutableMap<String, String>
}
