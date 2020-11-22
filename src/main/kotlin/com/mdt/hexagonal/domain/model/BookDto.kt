package com.mdt.hexagonal.domain.model

class BookDto (
val id: Long?,
val isbn: Long,
val name: String,
val quantity: Int,
val available: Boolean?,
val author: String,
val properties: Map<String, Any>?,
val startSaleDate: Long?,
val status: String?) {

    data class Builder(
            var id: Long? = null,
            var isbn: Long? = null,
            var name: String? = null,
            var quantity: Int? = null,
            var available: Boolean? = null,
            var author: String? = null,
            var properties: Map<String, Any>? = null,
            var startSaleDate: Long? = null,
            var status: String? = null) {

        fun id(id: Long?) = apply { this.id = id }
        fun isbn(isbn: Long) = apply { this.isbn = isbn }
        fun name(name: String) = apply { this.name = name }
        fun quantity(quantity: Int) = apply { this.quantity = quantity }
        fun available(available: Boolean?) = apply { this.available = available }
        fun author(author: String) = apply { this.author = author }
        fun properties(properties: Map<String, Any>?) = apply { this.properties = properties }
        fun startSaleDate(startSaleDate: Long?) = apply { this.startSaleDate = startSaleDate }
        fun status(status: String?) = apply { this.status = status}
        fun build() = BookDto(id, isbn!!, name!!, quantity!!, available, author!!, properties, startSaleDate, status)
    }
}