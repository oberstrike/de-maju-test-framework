package de.maju.container.postgres

import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import de.maju.container.IOnContainerCreatedHandler


class PostgresInitHandler(
    private val defaultConfig: IPostgresDefaultConfig
) : IOnContainerCreatedHandler<KPostgreSQLContainer> {

    companion object {
        private const val USERNAME = "postgres"
        private const val PASSWORD = "postgres"
        private const val PORT = 5434
        private const val DB_NAME = "queue"

        fun default(
            dbName: String = DB_NAME,
            username: String = USERNAME,
            password: String = PASSWORD,
            port: Int = PORT,
            initScriptPath: String? = null
        ) = PostgresInitHandler(
            object : IPostgresDefaultConfig {
                override var dbName: String = dbName
                override var password: String = password
                override var username: String = username
                override var port: Int = port
                override var initScriptPath: String? = initScriptPath
            }
        )
    }

    override fun onContainerCreated(container: KPostgreSQLContainer) {
        container.apply {
            withDatabaseName(defaultConfig.dbName)
            withUsername(defaultConfig.username)
            withPassword(defaultConfig.password)
            withExposedPorts(defaultConfig.port)
            if (defaultConfig.initScriptPath != null)
                withInitScript(defaultConfig.initScriptPath)
            withCreateContainerCmdModifier { cmd ->
                cmd.withHostConfig(
                    HostConfig.newHostConfig().withPortBindings(
                        PortBinding.parse("${defaultConfig.port}:${5432}")
                    )
                )
            }
        }
    }

    interface IPostgresDefaultConfig {
        var username: String
        var password: String
        var port: Int
        var dbName: String
        var initScriptPath: String?
    }
}
