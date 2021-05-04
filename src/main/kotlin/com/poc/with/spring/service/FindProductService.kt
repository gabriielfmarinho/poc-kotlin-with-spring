package com.poc.with.spring.service

import com.poc.with.spring.domain.dto.ProductDTO
import com.poc.with.spring.domain.entity.Product
import com.poc.with.spring.exception.ProductNotFoundException
import com.poc.with.spring.log.loggerFor
import com.poc.with.spring.mapper.toProductDTO
import com.poc.with.spring.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FindProductService(
    private val productRepository: ProductRepository
) {

    companion object {
        private val log = loggerFor(FindProductService::class.java)
    }

    fun findById(id: Long): ProductDTO {
        log.info("find product in data base by id=${id}")
        val product = productRepository.findById(id)
            .orElseThrow { ProductNotFoundException("product id=${id}, is not found") }
        return product.toProductDTO()
    }

    fun findByName(name: String): Product {
        log.info("find product in data base by name=${name}")
        return productRepository.findByName(name)
            .orElseThrow { ProductNotFoundException("product name=${name}, is not found") }
    }

    fun findByDescription(description: String): List<ProductDTO> {
        log.info("find product in data base by description=${description}")
        val products = productRepository.findByDescription(description)
            .orElseThrow { ProductNotFoundException("product description=${description}, is not found") }
        return products.map { product -> product.toProductDTO() }
    }

    fun findByAll(page: Int, quantityPerPage: Int): Page<ProductDTO> {
        log.info("find all product pageable in data base")
        val pageRequest = buildAPageRequest(page, quantityPerPage)
        return productRepository.findAll(pageRequest)
            .map { product -> product.toProductDTO() }
    }

    private fun buildAPageRequest(page: Int, quantityPerPage: Int): PageRequest{
        return PageRequest.of(page, quantityPerPage)
    }
}
