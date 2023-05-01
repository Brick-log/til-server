package com.tenmm.tilserver.blog.application.outbound

import com.tenmm.tilserver.blog.domain.Blog

interface SaveBlogPort {
    fun saveAll(blogs: List<Blog>): Boolean
}
