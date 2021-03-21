package com.poc.with.spring.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(unique = true)
    val name: String = "",

    val description: String = "",

    var active: Boolean = true,

    @CreationTimestamp
    @Column(name = "dat_create")
    val dateCreate: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "dat_update")
    val dateUpdate: LocalDateTime = LocalDateTime.now(),
)