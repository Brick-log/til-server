package com.tenmm.tilserver.common.domain

data class ResultWithToken<T>(
    val data: T,
    val pageToken: String?,
)
