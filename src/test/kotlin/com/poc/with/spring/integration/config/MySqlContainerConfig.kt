package com.poc.with.spring.integration.config

import org.testcontainers.containers.MySQLContainer

class MySqlContainerConfig : MySQLContainer<MySqlContainerConfig>(IMAGE) {

    companion object {

        private const val IMAGE: String = "mysql"

        private lateinit var container: MySqlContainerConfig

        fun getInstance(): MySqlContainerConfig {
            if (!this::container.isInitialized) {
                container = MySqlContainerConfig()
            }
            return container
        }
    }

    override fun start() {
        super.start()
        System.setProperty("MYSQL_URL", container.jdbcUrl)
        System.setProperty("MYSQL_USERNAME", container.username)
        System.setProperty("MYSQL_PASSWORD", container.password)
    }
}
