package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.SaveBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand
import com.tenmm.tilserver.blog.application.outbound.SaveBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import org.springframework.stereotype.Service

@Service
class SaveBlogService(
    private val saveBlogPort: SaveBlogPort
) : SaveBlogUseCase {
    override fun save(command: SaveBlogCommand): Blog {
        return saveBlogPort.save()
    }
}
