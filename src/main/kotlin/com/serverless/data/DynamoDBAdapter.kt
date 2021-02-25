package com.serverless.data
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.*
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.google.gson.Gson
import java.lang.Exception
import kotlin.reflect.KClass
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.model.ScanRequest


class DynamoDBAdapter <T: Any> (tableName: String, private val type: KClass<T>) {
    private val credentials = AWSStaticCredentialsProvider(BasicAWSCredentials("DEFAULT_ACCESS_KEY", "DEFAULT_ACCESS_KEY"));

    private val client: AmazonDynamoDB? = AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(EndpointConfiguration("http://localhost:8000", "us-east-1"))
        .withCredentials(credentials)
        .build()

    private val dynamoDB: DynamoDB = DynamoDB(client)
    val table: Table = dynamoDB.getTable(tableName)

    private val mapperConfig: DynamoDBMapperConfig = DynamoDBMapperConfig.builder()
        .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride(tableName))
        .build()

    private val mapper: DynamoDBMapper = DynamoDBMapper(client, mapperConfig)

    fun get(id: Any): T {
        return mapper.load(type.javaObjectType, id) ?: throw Error("object with $id not found")
    }

    fun list(scanExp: DynamoDBScanExpression = DynamoDBScanExpression()): List<T> {
        return mapper.scan(type.javaObjectType, scanExp)
    }

    fun save(obj: T) {
       mapper.save(obj)
    }

    fun delete(id: Any): T {
        val obj: T = get(id)
        mapper.delete(obj)
        return obj
    }


}