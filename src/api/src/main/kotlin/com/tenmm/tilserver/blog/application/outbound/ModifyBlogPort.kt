package com.tenmm.tilserver.blog.application.outbound

import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand

interface ModifyBlogPort {
    fun modifyIdentifier(command: ModifyBlogCommand): Boolean
}
