package com.serverless.services.textContent

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.serverless.data.DynamoDBAdapter
import com.serverless.models.TextContent
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride


class TextContentService(
    override val dynamoDBAdapter: DynamoDBAdapter<TextContent> = DynamoDBAdapter(tableName = "TextContent", type = TextContent::class)
) : ITextContent {
        override fun get(id: String): TextContent {
        return dynamoDBAdapter.get(id)
    }

    override fun save(textContent: TextContent) {
        dynamoDBAdapter.save(textContent)
    }

    override fun list(): List<TextContent> {
        return dynamoDBAdapter.list()
    }

    override fun delete(id: String): TextContent {
        return dynamoDBAdapter.delete(id)
    }

}