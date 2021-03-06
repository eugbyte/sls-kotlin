package com.serverless.models

import com.amazonaws.services.dynamodbv2.datamodeling.*
import java.util.*

@DynamoDBTable(tableName = "TextContent")
data class TextContent(
    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey // not working with kotlin, must manually generate
    var id: String = UUID.randomUUID().toString(),

    @DynamoDBAttribute(attributeName = "Question")
    var question: String = "",

    @DynamoDBAttribute(attributeName = "Answer")
    var answer: String = ""
    )