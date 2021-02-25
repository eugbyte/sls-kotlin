package com.serverless.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.di.Container
import com.serverless.di.Injected
import com.serverless.models.ApiGatewayResponse
import com.serverless.models.TextContent
import com.serverless.services.textContent.ITextContent
import com.serverless.services.util.IUtil

open class ListHandlerBase (
    private val textContentService: ITextContent,
    private val utilService: IUtil
) : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    val LOG = utilService.LOG(this::class)

    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: $input")
        val textContents: List<TextContent> = textContentService.list()

        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = mapOf("textContents" to textContents)
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }
    }

}

@Injected
class ListHandler : ListHandlerBase(Container.textContentService, Container.utilService)