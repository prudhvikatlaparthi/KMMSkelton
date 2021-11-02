package com.pru.kmmskelton

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient