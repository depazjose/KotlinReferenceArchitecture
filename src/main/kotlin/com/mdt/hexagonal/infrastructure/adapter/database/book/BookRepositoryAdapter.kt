package com.mdt.hexagonal.infrastructure.adapter.database.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mdt.hexagonal.domain.model.BookDto
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryAdapter(private val jpaBookRepository: BookDataRepository) {

  var logger: Logger = LogManager.getLogger(BookRepositoryAdapter::class)

  fun findByIsbn(isbn: Long): BookDto? {
    val bookData = jpaBookRepository.findByIsbn(isbn)
    val mapper = jacksonObjectMapper()
    bookData.let { bd ->
      if (bd != null) {
        return BookDto(
                bd.id!!,
                bd.isbn,
                bd.name,
                bd.quantity,
                bd.available,
                bd.author,
                bd.properties.let {
                  mapper.readValue<Map<String, Any>>(it!!)
                },
                bd.startSaleDate?.time?.toLong(),
                bd.status!!
        )
      }
      return null
    }
  }
}