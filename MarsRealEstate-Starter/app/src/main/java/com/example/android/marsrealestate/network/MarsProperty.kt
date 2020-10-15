/*
 * Moshi parses JSON data and converts it into Kotlin objects. To do this, it needs to have a
 * Kotlin data class to store the parsed results. It's a MarsProperty class.
 *
 * @Json аннотация позволяет заменить имя Json переменной на более подходящее для Котлина.
 */

package com.example.android.marsrealestate.network

import com.squareup.moshi.Json

data class MarsProperty(
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double
)
