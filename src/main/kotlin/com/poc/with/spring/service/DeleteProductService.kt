package com.poc.with.spring.service

import com.poc.with.spring.log.loggerFor
import com.poc.with.spring.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class DeleteProductService(
    private val productRepository: ProductRepository
) {

    companion object {
        private val log = loggerFor(DeleteProductService.javaClass)
    }

    @Transactional
    fun delete(name: String) {
        log.info("delete product by name=${name}")
        productRepository.deleteByName(name)
    }
}