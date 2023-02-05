package com.tenmm.tilserver.common.domain

data class OperationResult(
    val isSuccess: Boolean,
) {
    companion object {
        fun success() = OperationResult(true)
        fun fail() = OperationResult(false)
    }
}
