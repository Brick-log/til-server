package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.common.constants.EMAIL_REGEX

data class Email(val value: String) {
    init {
        if (!EMAIL_REGEX.matches(value)) {
            throw IllegalArgumentException("Invalid Email : $value")
        }
    }
}
