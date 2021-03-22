package com.poc.with.spring.controller.product

import com.poc.with.spring.controller.product.MediaTypes.Companion.MEDIA_TYPE_V1
import com.poc.with.spring.controller.product.request.CreateProductRequest
import com.poc.with.spring.controller.product.request.UpdateProductRequest
import com.poc.with.spring.controller.product.response.ProductResponse
import com.poc.with.spring.log.loggerFor
import com.poc.with.spring.mapper.toProductCreateDTO
import com.poc.with.spring.mapper.toProductResponse
import com.poc.with.spring.mapper.toProductUpdateDTO
import com.poc.with.spring.service.DeleteProductService
import com.poc.with.spring.service.SaveProductService
import com.poc.with.spring.service.FindProductService
import com.poc.with.spring.service.UpdateProductService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val saveProductService: SaveProductService,
    private val findProductService: FindProductService,
    private val updateProductService: UpdateProductService,
    private val deleteProductService: DeleteProductService
) {

    companion object {
        val log = loggerFor(ProductController.javaClass)
    }

    @PostMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun create(@RequestBody createProductRequest: CreateProductRequest): ResponseEntity<Unit> {
        log.info("receive request for create product, productRequest=${createProductRequest}")
        saveProductService.create(createProductRequest.toProductCreateDTO())
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @GetMapping("/{id}", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        log.info("receive request, find product by id=${id}")
        val productDTO = findProductService.findById(id)
        return ResponseEntity.ok(productDTO.toProductResponse())
    }

    @GetMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getByDescription(@RequestParam description: String ): ResponseEntity<List<ProductResponse>> {
        log.info("receive request, find product by description=${description}")
        val productsDTO = findProductService.findByDescription(description);
        return ResponseEntity.ok( productsDTO.map { productDTO -> productDTO.toProductResponse() })
    }

    @GetMapping("/all", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun getAllPageable(@RequestParam page: Int, @RequestParam quantityPerPage: Int ): ResponseEntity<Page<ProductResponse>> {
        log.info("receive request, find all product pageable")
        return ResponseEntity.ok(findProductService.findByAll(page, quantityPerPage)
            .map { productDTO -> productDTO.toProductResponse() })
    }

    @PatchMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun update(@RequestBody updateProductRequest: UpdateProductRequest): ResponseEntity<Any> {
        log.info("receive request, update  product with name=${updateProductRequest.name}")
        updateProductService.update(updateProductRequest.toProductUpdateDTO())
        return ResponseEntity.ok().build()
    }

    @PutMapping("/inactivate", produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun inactive(@RequestParam name: String): ResponseEntity<Any> {
        log.info("receive request, inactivate product with name=${name}")
        updateProductService.inactivate(name)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping(produces = [MEDIA_TYPE_V1], consumes = [MEDIA_TYPE_V1])
    fun delete(@RequestParam name: String): ResponseEntity<Any> {
        log.info("receive request, delete product with name=${name}")
        deleteProductService.delete(name)
        return ResponseEntity.ok().build()
    }
}