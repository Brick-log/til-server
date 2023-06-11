package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.exception.ErrorCode
import org.apache.logging.log4j.util.Strings

open class ServerBaseException(val errorCode: ErrorCode, val description: String = Strings.EMPTY) : RuntimeException()

class InvalidIdentifierException(input: String) : ServerBaseException(ErrorCode.INVALID_ARGUMENT, input)
class InvalidUrlException(input: String) : ServerBaseException(ErrorCode.INVALID_ARGUMENT, input)

class UserNotFoundException : ServerBaseException(ErrorCode.USER_NOT_FOUND)
class UnAuthorizedException : ServerBaseException(ErrorCode.UNAUTHORIZED_USER)
class ModifyUserFailException(val type: ModifyUserFailType) : ServerBaseException(ErrorCode.USER_MODIFY_FAIL, type.name)

enum class ModifyUserFailType {
    USER_NAME, USER_CATEGORY, USER_INTRODUCTION, USER_PROFILE_IMG_SRC, USER_PATH, USER_STATUS, BLOG, MAIL_AGREEMENT,
}

class PostNotFoundException : ServerBaseException(ErrorCode.POST_NOT_FOUND)

class ModifyPostFailException(val type: ModifyPostFailType) : ServerBaseException(ErrorCode.POST_MODIFY_FAIL, type.name)

enum class ModifyPostFailType {
    POST_TITLE, POST_SUMMARY, POST_CREATED_AT
}

class CategoryNotFoundException : ServerBaseException(ErrorCode.CATEGORY_NOT_FOUND)

class SignUpFailException : ServerBaseException(ErrorCode.SIGN_UP_FAIL)
class OAuthFailException(type: OAuthType, description: String) :
    ServerBaseException(ErrorCode.OAUTH_FAIL, "$type - $description")

class PostSaveFailException : ServerBaseException(ErrorCode.POST_SAVE_FAIL)
