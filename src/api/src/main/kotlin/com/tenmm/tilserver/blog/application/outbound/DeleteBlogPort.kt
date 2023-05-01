package com.tenmm.tilserver.blog.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteBlogPort {
    fun deleteAllByUserIdentifier(userIdentifier: Identifier): Boolean
}
