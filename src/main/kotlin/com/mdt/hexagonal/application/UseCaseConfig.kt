package com.mdt.hexagonal.application

import com.mdt.hexagonal.domain.model.gateway.BookRepository
import com.mdt.hexagonal.domain.usecase.BookUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class UseCaseConfig {
    @Bean
    fun bookUseCaseImpl(
            bookRepository: BookRepository?): BookUseCaseImpl? {
        return BookUseCaseImpl(bookRepository!!)
    }
}