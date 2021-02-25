package com.serverless.errors

// Base class
open class HttpError(message: String) : Exception(message) {
    open val statusCode = 500
}