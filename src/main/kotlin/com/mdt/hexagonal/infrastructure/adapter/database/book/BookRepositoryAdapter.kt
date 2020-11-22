package com.mdt.hexagonal.infrastructure.adapter.database.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mdt.hexagonal.domain.model.BookDto
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant

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

  fun saveBook(book: BookDto): BookDto {
    val mapper = jacksonObjectMapper()
    val bookData = BookData(
            book.id!!,
            book.isbn,
            book.name,
            book.quantity,
            book.available,
            book.author,
            book.properties.let { mapper.writeValueAsString(it)},
            book.startSaleDate?.let { Timestamp.from(Instant.ofEpochMilli(it))},
            book.status)
    val bookResult = jpaBookRepository.save(bookData)
    return BookDto(
            bookResult.id,
            bookResult.isbn,
            bookResult.name,
            bookResult.quantity,
            bookResult.available,
            bookResult.author,
            bookResult.properties.let {
              mapper.readValue<Map<String, Any>>(it!!)
            },
            bookResult.startSaleDate?.time,
            bookResult.status!!
    )
  }
}