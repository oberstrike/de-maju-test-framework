package de.maju.container.postgres

import de.maju.container.AbstractContainerCreator
import de.maju.container.IOnConfigCreatedHandler
import de.maju.container.IOnContainerCreatedHandler
import org.testcontainers.containers.PostgreSQLContainer

const val postgresContainerName = "postgres"

class KPostgreSQLContainer : PostgreSQLContainer<KPostgreSQLContainer>(postgresContainerName)

private val defaultPostgresHandler = PostgresInitHandler.default()

class PostgresContainerCreatorImpl(
    postgresInitHandler: PostgresInitHandler = defaultPostgresHandler
) :
    AbstractContainerCreator<KPostgreSQLContainer>() {

    override val container: KPostgreSQLContainer = KPostgreSQLContainer()

    override val onContainersCreatedHandlers: List<IOnContainerCreatedHandler<KPostgreSQLContainer>> = listOf(
        postgresInitHandler
    )

    override val onConfigCreatedHandlers: List<IOnConfigCreatedHandler<KPostgreSQLContainer>> = listOf(

    )


}