package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult
import com.tenmm.tilserver.blog.application.outbound.GetBlogPort
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetUserBlogService(
    private val getUserUseCase: GetUserUseCase,
    private val getBlogPort: GetBlogPort,
) : GetUserBlogUseCase {
    @Transactional(readOnly = true)
    override fun getAllByUserName(name: String): List<GetBlogResult> {
        val user = getUserUseCase.getByName(name)
        val blogs = getBlogPort.getByUserIdentifier(user.identifier)

        return blogs.map {
            GetBlogResult(
                blogIdentifier = it.identifier,
                url = it.url
            )
        }
    }
}
