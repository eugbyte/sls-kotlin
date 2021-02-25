package com.serverless.errors

class BadRequest(message: String): HttpError(message) {
    override val statusCode: Int = 400
}