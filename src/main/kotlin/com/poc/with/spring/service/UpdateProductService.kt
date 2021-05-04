package com.poc.with.spring.service

import com.poc.with.spring.domain.dto.ProductUpdateDTO
import com.poc.with.spring.log.loggerFor
import com.poc.with.spring.mapper.toProduct
import org.springframework.beans.BeanUtils.copyProperties
import org.springframework.stereotype.Service

@Service
class UpdateProductService(
    private val findProductService: FindProductService,
    private val saveProductService: SaveProductService
) {
    companion object {
        private val log = loggerFor(UpdateProductService::class.java)
    }

    fun update(productUpdateDTO: ProductUpdateDTO) {
        val productToUpdate = productUpdateDTO.toProduct();
        log.info("init update in product=${productToUpdate}")
        val productDataBase = findProductService.findByName(productToUpdate.name)
        copyProperties(productToUpdate, productDataBase)
        saveProductService.update(productDataBase)
    }

    fun inactivate(name: String) {
        val product = findProductService.findByName(name)
        product.active = true
        saveProductService.update(product)
    }
}
