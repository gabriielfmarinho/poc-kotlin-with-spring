package com.poc.with.spring.arch.annotation

import com.poc.with.spring.arch.extension.ArchUnitExtension
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ArchUnitExtension::class)
annotation class ArchTest
