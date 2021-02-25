package com.serverless.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.di.Container
import com.serverless.di.Injected
import com.serverless.middleware.middleWare
import com.serverless.middleware.Middleware
import com.serverless.models.ApiGatewayResponse
import com.serverless.models.TextContent
import com.serverless.services.textContent.ITextContent
import com.serverless.services.util.IUtil
import org.apache.logging.log4j.Logger

open class CreateHandlerBase (
    private val textContentService: ITextContent,
    private val utilService: IUtil
    ) : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    private val LOG: Logger = utilService.LOG(this::class)

    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("input $input")

        @Suppress("UNCHECKED_CAST")
        val body: Map<String, Any> = input["body"] as Map<String, Any>? ?: throw Error("json structure is wrong")

        val textContent: TextContent = utilService.convert(body, TextContent::class)

        textContentService.save(textContent)

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = mapOf("textContent" to textContent)
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }

    }
}


@Injected
class CreateHandler: CreateHandlerBase (
    textContentService = Container.textContentService,
    utilService = Container.utilService
) {
    @Middleware
    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        return middleWare (input, context) { i, c -> super.handleRequest(i, c) }
    }
}
