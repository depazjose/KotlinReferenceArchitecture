package com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto

import com.mdt.hexagonal.domain.model.BookDto
import java.util.stream.Collectors

interface BookResponse {
  class BookDetailResponse private constructor (
    val id: Long?,
    val isbn: Long?,
    val name: String?,
    val quantity: Int?,
    val available: Boolean?,
    val author: String?,
    val properties: Map<String, Any>?,
    val startSaleDate: Long?,
    val status: String?) {

    data class Builder(
        var id: Long? = null,
        var isbn: Long? = null,
        var name: String? = null,
        var quantity: Int? = null,
        var available: Boolean? = null,
        var author: String? = null,
        var properties: Map<String, Any>? = null,
        var startSaleDate: Long? = null,
        var status: String? = null) {

      fun id(id: Long?) = apply { this.id = id }
      fun isbn(isbn: Long) = apply { this.isbn = isbn }
      fun name(name: String) = apply { this.name = name }
      fun quantity(quantity: Int) = apply { this.quantity = quantity }
      fun available(available: Boolean?) = apply { this.available = available }
      fun author(author: String?) = apply { this.author = author }
      fun properties(properties: Map<String, Any>?) = apply { this.properties = properties }
      fun startSaleDate(startSaleDate: Long?) = apply { this.startSaleDate = startSaleDate }
      fun status(status: String?) = apply { this.status = status}
      fun build() = BookDetailResponse(id, isbn, name, quantity, available, author, properties, startSaleDate, status)
    }
  }

    companion object {
      fun fromModel(book: BookDto): BookDetailResponse {

        return BookDetailResponse.Builder()
            .id(book.id)
            .isbn(book.isbn)
            .name(book.name)
            .quantity(book.quantity)
            .available(book.available)
            .author(book.author)
            .properties(book.properties)
            .startSaleDate(book.startSaleDate)
            .status(book.status)
            .build();
      }

      fun fromModel(books: List<BookDto>): List<BookDetailResponse> {
        return books.stream().map { book: BookDto -> fromModel(book) }.collect(Collectors.toList())
      }
    }
}
