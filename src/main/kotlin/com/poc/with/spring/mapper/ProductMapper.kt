package com.poc.with.spring.mapper

import com.poc.with.spring.domain.dto.ProductCreateDTO
import com.poc.with.spring.controller.product.request.CreateProductRequest
import com.poc.with.spring.controller.product.request.UpdateProductRequest
import com.poc.with.spring.controller.product.response.ProductResponse
import com.poc.with.spring.domain.dto.ProductDTO
import com.poc.with.spring.domain.dto.ProductUpdateDTO
import com.poc.with.spring.domain.entity.Product

fun ProductCreateDTO.toProduct () = Product(
    name = name,
    description = description
)

fun ProductDTO.toProductResponse () = ProductResponse(
    name = name,
    description = description
);

fun ProductCreateDTO.toCreateProductRequest () = CreateProductRequest(
    name = name,
    description = description
)

fun CreateProductRequest.toProductCreateDTO () = ProductCreateDTO(
    name = name,
    description = description
)

fun ProductUpdateDTO.toProduct () = Product(
    name = name,
    description = description
)

fun UpdateProductRequest.toProductUpdateDTO () = ProductUpdateDTO(
    name = name,
    description = description
)

fun Product.toProductDTO () = ProductDTO(
    name = name,
    description = description
)