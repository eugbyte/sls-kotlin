package com.serverless.errors

class Forbidden(message: String = "Not authenticated"): HttpError(message) {
    override val statusCode: Int = 403
}