package com.mdt.hexagonal.infrastructure.entrypoint.receivers.books

import com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto.BookResponse
import org.apache.logging.log4j.LogManager;
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes

@RestController
@RequestMapping(value = ["/api/v1/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BookController {

  private val logger = LogManager.getLogger(BookController::class.java)

  @RequestMapping(method = [RequestMethod.GET])
  fun getAllBooks(): String {
    logger.info("getAllBooks")
    return "Get all Books";
  }

  @RequestMapping(value = ["/{isbn}"], method = [RequestMethod.GET])
  fun getBookByIsbn(): BookResponse.BookDetailResponse {
    logger.info("getBookByIsbn")
    return buildBook()
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
