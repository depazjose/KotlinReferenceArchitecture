package com.mdt.hexagonal.infrastructure.entrypoint.receivers.books

import com.mdt.hexagonal.domain.usecase.BookUseCase
import com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto.BookRequest
import com.mdt.hexagonal.infrastructure.entrypoint.receivers.books.dto.BookResponse
import io.swagger.annotations.ApiParam
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.awt.PageAttributes
import javax.validation.Valid

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
  fun getBookByIsbn(@PathVariable isbn : Long): BookResponse.BookDetailResponse {
    logger.info("getBookByIsbn")
    return BookResponse.fromModel(bookUseCase.findByIsbn(isbn)?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exists!"))
  }

  @RequestMapping(method = [RequestMethod.POST])
  fun createBook(
          @Valid @RequestPart(name = "book") creationBookRequest: BookRequest.CreationBookRequest,
          @ApiParam(value = "Book cover")
          @RequestPart(name = "image", required = false) file : MultipartFile?):
          ResponseEntity<BookResponse.BookDetailResponse> {

      logger.info("createBook")

      require(bookUseCase.findByIsbn(creationBookRequest.isbn) == null) {
          throw ResponseStatusException(HttpStatus.BAD_REQUEST,
                  String.format("Book with ISBN %s already exists", creationBookRequest.isbn))
      }

      return ResponseEntity.ok(BookResponse.fromModel(bookUseCase.createBook(BookRequest.toModel(creationBookRequest))))

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
