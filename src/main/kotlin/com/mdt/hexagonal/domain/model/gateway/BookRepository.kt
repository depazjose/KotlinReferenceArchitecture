package com.mdt.hexagonal.domain.model.gateway

import com.mdt.hexagonal.domain.model.BookDto

interface BookRepository {

  fun saveBook(book: BookDto): BookDto
  fun findByIsbn(isbn: Long): BookDto?
}