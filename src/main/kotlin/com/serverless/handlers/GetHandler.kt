package com.serverless.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.di.Container
import com.serverless.di.Injected
import com.serverless.middleware.Middleware
import com.serverless.middleware.middleWare
import com.serverless.models.ApiGatewayResponse
import com.serverless.models.TextContent
import com.serverless.services.textContent.ITextContent
import com.serverless.services.util.IUtil

open class GetHandlerBase (
    private val textContentService: ITextContent,
    private val utilService: IUtil
    ): RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    private val logger = utilService.LOG(this::class)

    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        logger.info("input $input")

        @Suppress("UNCHECKED_CAST")
        val pathParameters:Map<String, Any> = input["pathParameters"] as Map<String, Any>? ?: throw Error("JSON data is wrong")

        val id: String = pathParameters["id"] as String

        val textContent: TextContent = textContentService.get(id)

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = mapOf("textContent" to textContent)
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }
    }
}

@Injected
class GetHandler : GetHandlerBase(Container.textContentService, Container.utilService) {
    @Middleware
    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        return middleWare (input, context) { i, c -> super.handleRequest(i, c) }
    }
}