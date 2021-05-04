package com.poc.with.spring.arch

import com.poc.with.spring.arch.annotation.ArchTest
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

@ArchTest
class ArchServiceTest {

    @Test
    fun shouldHaveSimpleNameEndingWithService(classes: JavaClasses?) {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..service..")
                .and().haveSimpleNameNotContaining("Test")
                .and().areNotInnerClasses()
                .and().areNotMemberClasses()
                .should().haveSimpleNameEndingWith("Service")
                .check(classes)
    }
}