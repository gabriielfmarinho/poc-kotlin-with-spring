package com.poc.with.spring.integration.config

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class TestContainerConfig :  BeforeAllCallback{
    override fun beforeAll(context: ExtensionContext?) {
        val container = MySqlContainerConfig.getInstance()
        container.start()
    }
}
