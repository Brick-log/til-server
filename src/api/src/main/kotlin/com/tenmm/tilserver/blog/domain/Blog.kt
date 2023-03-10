package com.tenmm.tilserver.blog.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class Blog(
    val identifier: Identifier,
    val userIdentifier: Identifier,
    val url: Url,
)
