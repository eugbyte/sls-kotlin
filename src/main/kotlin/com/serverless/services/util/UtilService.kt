package com.serverless.services.util

import com.google.gson.Gson
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.reflect.KClass

class UtilService : IUtil {

    override fun greet(): String {
        return "Hello!"
    }

    override fun <T: Any> LOG(type: KClass<T>): Logger {
        return LogManager.getLogger(type)
    }

    override fun <T: Any> convert(input:Map<String, Any>, type: KClass<T>): T {
        val gson = Gson()
        val jsonString = gson.toJson(input)
        return gson.fromJson(jsonString, type.javaObjectType)
    }




}