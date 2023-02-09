package com.tenmm.tilserver.blog.adapter.outbound

import com.tenmm.tilserver.blog.application.outbound.SaveBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import org.springframework.stereotype.Component

@Component
class SaveBlogAdapter : SaveBlogPort {
    override fun save(): Blog {
        TODO("Not yet implemented")
    }
}
