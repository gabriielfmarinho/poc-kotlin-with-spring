package com.poc.with.spring.service

import com.poc.with.spring.config.IntegrationTest
import com.poc.with.spring.domain.dto.ProductCreateDTO
import com.poc.with.spring.exception.ProductAlreadyExistsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order

@IntegrationTest
@TestMethodOrder(OrderAnnotation::class)
class CreateProductServiceTest(
    val saveProductService: SaveProductService
) {

    @Test
    @Order(1)
    fun shouldCreateAProductWithSuccess() {
        val productCreateDTO = buildAProductCreateDTO("name product test one",
            "description product test one")
        saveProductService.create(productCreateDTO)
    }

    @Test
    @Order(2)
    fun shouldThrownProductAlreadyExistsExceptionWhenRequestCreateANewProductWithEqualName() {
        val productCreateDTO = buildAProductCreateDTO("name product test one",
            "description product test one")
        saveProductService.create(productCreateDTO)
        assertThrows<ProductAlreadyExistsException> { saveProductService.create(productCreateDTO) }
    }

    private fun buildAProductCreateDTO(name: String, description: String) =
        ProductCreateDTO(name, description)
}