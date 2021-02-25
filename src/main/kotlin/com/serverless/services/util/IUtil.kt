package com.serverless.services.util

import org.apache.logging.log4j.Logger
import kotlin.reflect.KClass

interface IUtil {
    fun greet(): String
    fun <T: Any> LOG(type: KClass<T>): Logger
    fun <T: Any> convert(input:Map<String, Any>, type: KClass<T>): T
}