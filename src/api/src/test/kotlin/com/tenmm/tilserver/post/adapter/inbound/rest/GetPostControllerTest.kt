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
import java.util.UUID
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

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
        val postIdValue = UUID.randomUUID().toString()
        val getPostResult = randomGetPostResult()

        every { getPostUseCase.getPostByIdentifier(any()) } returns getPostResult

        // when
        val result = sut.getPostByIdentifier(postIdValue)

        // then
        val expected = GetPostResponse.fromResult(getPostResult)

        assertThat(result).isEqualTo(expected)

        val postIdentifier = Identifier(postIdValue)
        verify { getPostUseCase.getPostByIdentifier(postIdentifier) }
    }

    @Test
    fun `getPostByIdentifier - fail - invalid identifier format`() {
        // given
        val postIdValue = RandomStringUtils.randomAlphabetic(10)

        // when
        val result = assertThrows<IllegalArgumentException> {
            sut.getPostByIdentifier(postIdValue)
        }


        // then
        val expectedMessage = "Invalid identifier: $postIdValue"

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
