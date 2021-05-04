package com.poc.with.spring.service

import com.poc.with.spring.domain.dto.ProductCreateDTO
import com.poc.with.spring.domain.entity.Product
import com.poc.with.spring.exception.ProductAlreadyExistsException
import com.poc.with.spring.log.loggerFor
import com.poc.with.spring.mapper.toProduct
import com.poc.with.spring.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class SaveProductService(
    private val productRepository: ProductRepository
) {

    companion object {
        private val log = loggerFor(SaveProductService::class.java)
    }

    fun create(productCreateDTO: ProductCreateDTO) {
        val product = productCreateDTO.toProduct()
        if (productAlreadyExists(product)) {
            throw ProductAlreadyExistsException("product name=${product.name}, already exists")
        }
        persist(product)
    }

    fun update(product: Product) {
        persist(product)
    }

    private fun persist(product: Product) {
        log.info("init persistence in database for, product=${product}")
        productRepository.save(product)
    }

    private fun productAlreadyExists(product: Product): Boolean {
        return productRepository.findByName(product.name).isPresent
    }
}
