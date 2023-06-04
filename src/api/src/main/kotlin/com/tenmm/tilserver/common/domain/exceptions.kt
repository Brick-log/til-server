package com.tenmm.tilserver.common.domain

class NotFoundException(description: String) : RuntimeException(description)
class UnAuthorizedException(description: String?) : RuntimeException(description)
class ModifyFailException(type: ModifyFailType) : RuntimeException(type.toString())

enum class ModifyFailType {
    USER, BLOG, MAIL_AGREEMENT
}
