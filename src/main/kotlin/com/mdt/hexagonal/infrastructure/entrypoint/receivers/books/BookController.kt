package com.mdt.hexagonal.infrastructure.entrypoint.receivers.books

import com.mdt.hexagonal.domain.usecase.BookUseCase
import com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto.BookResponse
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.awt.PageAttributes

@RestController
@RequestMapping(value = ["/api/v1/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BookController(private val bookUseCase: BookUseCase) {

  private val logger = LogManager.getLogger(BookController::class)

  @RequestMapping(method = [RequestMethod.GET])
  fun getAllBooks(): String {
    logger.info("getAllBooks")
    return "Get all Books";
  }

  @RequestMapping(value = ["/{isbn}"], method = [RequestMethod.GET])
  fun getBookByIsbn(@PathVariable isbn:Long): BookResponse.BookDetailResponse {
    logger.info("getBookByIsbn")
    return BookResponse.fromModel(bookUseCase.findByIsbn(isbn)?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exists!"))
  }

  private fun buildBook() : BookResponse.BookDetailResponse {

    val propertiesMap = mapOf("reference" to "new era", "maps" to true)

    return BookResponse.BookDetailResponse.Builder()
        .id(123L)
        .isbn(909090L)
        .name("foo")
        .quantity(101)
        .available(true)
        .author("kotlin")
        .properties(propertiesMap)
        .startSaleDate(System.currentTimeMillis())
        .status("AVAILABLE")
        .build();
  }
}
