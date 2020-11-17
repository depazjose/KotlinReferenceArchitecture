package com.mdt.hexagonal.infrastructure.adapter.database.book

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(domainClass = BookData::class, idClass = Long::class)
interface BookDataRepository: JpaSpecificationExecutor<BookData> {

  fun save(book: BookData): BookData
  fun findByIsbn(isbn: Long): BookData?
  fun findAll(): List<BookData>
}