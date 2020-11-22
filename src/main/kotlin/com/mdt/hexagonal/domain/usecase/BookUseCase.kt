package com.mdt.hexagonal.domain.usecase

import com.mdt.hexagonal.domain.model.BookDto

interface BookUseCase {
  fun findByIsbn(isbn: Long?): BookDto?
  fun createBook(book: BookDto): BookDto
}