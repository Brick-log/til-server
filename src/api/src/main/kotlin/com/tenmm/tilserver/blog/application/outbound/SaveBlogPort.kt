package com.tenmm.tilserver.blog.application.outbound

import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand

interface SaveBlogPort {
    fun save(blogCommand: SaveBlogCommand): Boolean
}
