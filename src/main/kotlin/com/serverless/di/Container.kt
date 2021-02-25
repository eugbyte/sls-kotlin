package com.serverless.di

import com.serverless.services.textContent.ITextContent
import com.serverless.services.textContent.TextContentService
import com.serverless.services.util.IUtil
import com.serverless.services.util.UtilService

@Target(AnnotationTarget.CLASS, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class Injected

// Singleton services
object Container {
    val utilService: IUtil = UtilService()
    val textContentService: ITextContent = TextContentService()
}
