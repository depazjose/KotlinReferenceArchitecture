package com.mdt.hexagonal.infrastructure.adapter.database

import com.mdt.hexagonal.domain.model.BookDto
import com.mdt.hexagonal.domain.model.gateway.BookRepository
import com.mdt.hexagonal.infrastructure.adapter.database.book.BookRepositoryAdapter
import org.springframework.stereotype.Component

@Component
class BookRepositoryImpl(private val bookRepositoryAdapter: BookRepositoryAdapter) : BookRepository {

  override fun saveBook(book: BookDto): BookDto {
    return bookRepositoryAdapter.saveBook(book)
  }

  override fun findByIsbn(isbn: Long): BookDto? {
    return bookRepositoryAdapter.findByIsbn(isbn)
  }
}