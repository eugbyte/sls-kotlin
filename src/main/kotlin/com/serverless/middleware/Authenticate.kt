package com.serverless.middleware

import com.serverless.errors.Forbidden

fun authenticate(input: Map<String, Any>) {
    // ... some authentication logic ...
    val isAuthenticated = true;
    if (!isAuthenticated) throw Forbidden()
}