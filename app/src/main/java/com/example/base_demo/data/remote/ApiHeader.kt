package com.example.base_demo.data.remote

import okhttp3.Headers
import javax.inject.Inject


class ApiHeader @Inject constructor() {
    private val headers = Headers.Builder()

    fun add() {

    }

    fun build(): Headers = headers.build()
}