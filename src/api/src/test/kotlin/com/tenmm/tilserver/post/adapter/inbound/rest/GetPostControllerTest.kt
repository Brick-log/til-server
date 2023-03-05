package com.tenmm.tilserver.post.adapter.inbound.rest

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.randomGetPostResult
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
class GetPostControllerTest {

    @InjectMockKs
    private lateinit var sut: GetPostController

    @MockK
    private lateinit var getPostUseCase: GetPostUseCase

    @MockK
    private lateinit var getRecommendedPostUseCase: GetRecommendedPostUseCase

    @AfterEach
    fun tearDown() {
        confirmVerified(getPostUseCase, getRecommendedPostUseCase)
    }

    @Test
    fun `getPostByIdentifier - success`() {
        // given
        val postIdentifier = Identifier.generate()
        val getPostResult = randomGetPostResult()

        every { getPostUseCase.getPostByIdentifier(any()) } returns getPostResult

        // when
        val result = sut.getPostByIdentifier(postIdentifier)

        // then
        val expected = GetPostResponse.fromResult(getPostResult)

        assertThat(result).isEqualTo(expected)

        verify { getPostUseCase.getPostByIdentifier(postIdentifier) }
    }
}
