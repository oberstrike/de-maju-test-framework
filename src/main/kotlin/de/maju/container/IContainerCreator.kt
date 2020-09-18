package de.maju.container

import org.testcontainers.containers.GenericContainer


interface IContainerCreator<T : GenericContainer<T>?> {

    fun getContainer(): GenericContainer<T>

    fun getConfig(): MutableMap<String, String>
}
