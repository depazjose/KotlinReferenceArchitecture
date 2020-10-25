package com.mdt.hexagonal.domain.model

import java.sql.Timestamp

data class BookDto (
val id: Long,
val isbn: Long,
val name: String,
val quantity: Int,
val available: Boolean,
val author: String,
val properties: Map<String, Object>,
val startSaleDate: Long,
val status: String)