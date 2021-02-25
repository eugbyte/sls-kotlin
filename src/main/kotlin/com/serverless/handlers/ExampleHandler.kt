package com.serverless.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.models.ApiGatewayResponse
import com.serverless.services.util.IUtil
import com.serverless.di.Container
import com.serverless.di.Injected

open class ExampleHandlerBase (private val utilService: IUtil) : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

  val LOG = utilService.LOG(ExampleHandlerBase::class)

  override fun handleRequest(input:Map<String, Any>, context:Context): ApiGatewayResponse {

    LOG.info("received: $input")

    return ApiGatewayResponse.build {
      statusCode = 200
      objectBody = mapOf("message" to utilService.greet())
      headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
    }
  }

}

@Injected
class ExampleHandler: ExampleHandlerBase(utilService = Container.utilService)
