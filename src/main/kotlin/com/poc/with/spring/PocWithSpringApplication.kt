package com.poc.with.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocWithSpringApplication

fun main(args: Array<String>) {
	runApplication<PocWithSpringApplication>(*args)
}
