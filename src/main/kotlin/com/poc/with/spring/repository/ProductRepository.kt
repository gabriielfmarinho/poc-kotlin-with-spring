package com.poc.with.spring.repository

import com.poc.with.spring.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    @Query(
        value = """
        select * from product p
        where p.description = :description
    """,
        nativeQuery = true
    )
    fun findByDescription(@Param("description") description: String): Optional<List<Product>>

    @Query(
        value = """
        select * from product p
        where p.name = :name
    """,
        nativeQuery = true
    )
    fun findByName(@Param("name") name: String): Optional<Product>

    @Modifying
    @Query(
        value = """
            delete from product p
            where p.name = :name
        """,
        nativeQuery = true
    )
    fun deleteByName(@Param("name") name: String)
}