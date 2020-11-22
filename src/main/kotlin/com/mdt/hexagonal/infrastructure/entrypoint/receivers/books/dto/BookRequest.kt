package com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto

import com.mdt.hexagonal.domain.model.BookDto
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

interface BookRequest {
    class CreationBookRequest (
            @NotNull(message = "ISBN is required")
            val isbn: Long,
            @NotBlank(message = "Name is required")
            val name: String,
            @NotNull(message = "Quantity is required")
            val quantity: Int,
            @NotEmpty(message = "Author is required")
            val author: String,
            val properties: Map<String, Any>?,
            val startSaleDate: Long?,
            @NotBlank(message = "Status is required")
            val status: String) {

        data class Builder(
                var isbn: Long,
                var name: String,
                var quantity: Int,
                var author: String,
                var properties: Map<String, Any>? = null,
                var startSaleDate: Long? = null,
                var status: String) {

            fun isbn(isbn: Long) = apply { this.isbn = isbn }
            fun name(name: String) = apply { this.name = name }
            fun quantity(quantity: Int) = apply { this.quantity = quantity }
            fun author(author: String) = apply { this.author = author }
            fun properties(properties: Map<String, Any>?) = apply { this.properties = properties }
            fun startSaleDate(startSaleDate: Long?) = apply { this.startSaleDate = startSaleDate }
            fun status(status: String) = apply { this.status = status }
            fun build() = CreationBookRequest(isbn, name, quantity, author, properties, startSaleDate, status)
        }
    }

    companion object {
        fun toModel(request: CreationBookRequest): BookDto {
            return BookDto.Builder()
                    .id(0L)
                    .isbn(request.isbn)
                    .name(request.name)
                    .quantity(request.quantity)
                    .author(request.author)
                    .properties(request.properties)
                    .startSaleDate(request.startSaleDate)
                    .status(request.status)
                    .build();
        }
    }
}