package com.poc.with.spring.arch

import com.poc.with.spring.arch.annotation.ArchTest
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

@ArchTest
class ArchExceptionTest {

    @Test
    fun shouldHaveSimpleNameEndingWithException(classes: JavaClasses?) {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..exception..")
                .should().haveSimpleNameEndingWith("Exception")
                .check(classes)
    }
}