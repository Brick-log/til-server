package com.tenmm.tilserver.blog.application.outbound

import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.common.domain.Identifier

interface GetBlogPort {
    fun getByBlogIdentifier(blogIdentifier: Identifier): Blog
    fun getByUserIdentifier(userIdentifier: Identifier): List<Blog>
}
