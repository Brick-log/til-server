package com.tenmm.tilserver.blog.adapter.inbound.rest

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tenmm.tilserver.blog.adapter.inbound.rest.model.DeleteBlogResponse
import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.randomDeleteBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Disabled
@ExtendWith(MockKExtension::class)
class DeleteBlogControllerTest {

    @InjectMockKs
    private lateinit var sut: DeleteBlogController

    @MockK
    private lateinit var deleteBlogUseCase: DeleteBlogUseCase

    @AfterEach
    fun tearDown() {
        confirmVerified(deleteBlogUseCase)
    }
    @Test
    fun `deleteBlog - success`() {
        // given
        val blogIdentifier = Identifier.generate()
        val deleteBlogResult = OperationResult.success()
        val deleteBlogCommand = randomDeleteBlogCommand(blogIdentifier = blogIdentifier)

        every { deleteBlogUseCase.delete(any()) } returns deleteBlogResult

        // when
        val result = sut.deleteBlog(blogIdentifier.value)

        // then
        val expected = DeleteBlogResponse(deleteBlogResult.isSuccess)

        assertThat(result).isEqualTo(expected)

        verify { deleteBlogUseCase.delete(deleteBlogCommand) }
    }
}
