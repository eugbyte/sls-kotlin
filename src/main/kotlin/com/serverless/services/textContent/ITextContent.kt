package com.serverless.services.textContent

import com.serverless.data.DynamoDBAdapter
import com.serverless.models.TextContent

interface ITextContent {
    val dynamoDBAdapter: DynamoDBAdapter<TextContent>
    fun get(id: String): TextContent
    fun save(textContent: TextContent)
    fun list(): List<TextContent>
    fun delete(id: String): TextContent
}