package com.mdt.hexagonal.infrastructure.adapter.database.book

import java.sql.Timestamp
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "book_data")
data class BookData (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(unique = true)
  val isbn: Long,

  @NotBlank(message = "Name is mandatory")
  val name: String,
  val quantity: Int,
  val available: Boolean?,

  @NotBlank(message = "Author is mandatory")
  val author: String,
  val properties: String? = null,

  @Column(name = "start_sale_date")
  val startSaleDate: Timestamp? = null,
  val status: String? = null
)