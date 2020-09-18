package de.maju.container

import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import dasniko.testcontainers.keycloak.KeycloakContainer
import org.testcontainers.containers.GenericContainer

class KeycloakContainerCreator(private val keycloakContainerConfig: KeycloakContainerConfig) :
    IContainerCreator<KeycloakContainer> {

    companion object {
        const val IMAGE_NAME = "quay.io/keycloak/keycloak:latest"
        const val AUTH_SERVER_HOST = "localhost"


        private const val REALM_NAME = "quarkus"
        private const val ADMIN_USERNAME = "admin"
        private const val ADMIN_PASSWORD = "admin"

        //   "/imports/realm-export.json"
        private const val KEYCLOAK_PORT = 8181
    }

    open class KeycloakContainerConfig(
        open var realmName: String = REALM_NAME,
        open var adminUsername: String = ADMIN_USERNAME,
        open var adminPassword: String = ADMIN_PASSWORD,
        open var realmImportFile: String? = null,
        open var port: Int = KEYCLOAK_PORT,
        open var imageName: String = IMAGE_NAME,
        private val config: MutableMap<String, String> = mutableMapOf()
    ) {
        fun getQuarkusOIDCAuthServerUrlConfig() =
            "quarkus.oidc.auth-server-url" to "http://$AUTH_SERVER_HOST:$port/auth/realms/$realmName"

        fun getQuarkusRestClientUrl(clazz: Class<*>) =
            "${clazz.`package`.name}.${clazz.simpleName}/mp-rest/url=" to
                    "http://$AUTH_SERVER_HOST:$port/auth/realms/$realmName/protocol/openid-connect"


        open fun withConfig() = config
    }


    private var realmName = keycloakContainerConfig.realmName

    private var adminUsername = keycloakContainerConfig.adminUsername
    private var adminPassword = keycloakContainerConfig.adminPassword
    private var realmImportFile: String? = keycloakContainerConfig.realmImportFile
    private var port = keycloakContainerConfig.port
    private var imageName = keycloakContainerConfig.imageName


    override fun getConfig(): MutableMap<String, String> {
        return keycloakContainerConfig.withConfig()
    }


    override fun getContainer(): GenericContainer<KeycloakContainer> {
        return KeycloakContainer(imageName)
            .withAdminUsername(adminUsername)
            .withAdminPassword(adminPassword)
            .withRealmImportFile(realmImportFile)
            .withExposedPorts(port)
            .withCreateContainerCmdModifier { cmd ->
                cmd.withHostConfig(
                    HostConfig.newHostConfig().withPortBindings(PortBinding.parse("$port:8080"))
                )
            }

    }


}