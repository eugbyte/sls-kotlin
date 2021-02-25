package com.serverless.middleware

import com.serverless.models.ApiGatewayResponse
import com.amazonaws.services.lambda.runtime.Context
import com.serverless.errors.Forbidden
import com.serverless.errors.HttpError

annotation class Middleware

inline fun middleWare(input: Map<String, Any>,
               context: Context,
               handleRequest: (i: Map<String, Any>, c: Context) -> ApiGatewayResponse
): ApiGatewayResponse {
    return try {
        authenticate(input)
        handleRequest(input, context)
    } catch(error: HttpError) {
        ApiGatewayResponse.build {
            statusCode = error.statusCode
            objectBody = mapOf("textContent" to error.message.toString())
        }
    } catch (error: Error) {
        ApiGatewayResponse.build {
            statusCode = 500
            objectBody = mapOf("textContent" to "Something wrong occurred")
        }
    }
}
