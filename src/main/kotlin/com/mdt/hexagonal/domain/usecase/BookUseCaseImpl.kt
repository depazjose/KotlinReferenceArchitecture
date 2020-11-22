package com.mdt.hexagonal.domain.usecase

import com.mdt.hexagonal.domain.model.BookDto
import com.mdt.hexagonal.domain.model.gateway.BookRepository

class BookUseCaseImpl(private val bookRepository: BookRepository): BookUseCase {
  override fun findByIsbn(isbn: Long?): BookDto? {
    return bookRepository.findByIsbn(isbn!!)
  }

  override fun createBook(book: BookDto): BookDto {
    return bookRepository.saveBook(book)
  }
}