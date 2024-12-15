package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val address: String,
    val roadAddress: String,
    val detailAddress: String,
    val postCode: String,
    val doorCode: String,
)