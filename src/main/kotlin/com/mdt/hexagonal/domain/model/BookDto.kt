package com.mdt.hexagonal.domain.model

data class BookDto (
val id: Long,
val isbn: Long,
val name: String,
val quantity: Int,
val available: Boolean?,
val author: String,
val properties: Map<String, Any>?,
val startSaleDate: Long?,
val status: String)