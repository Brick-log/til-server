package com.tenmm.tilserver.common.domain

class NotFoundException(description: String) : RuntimeException(description)
class UnAuthorizedException : RuntimeException()
