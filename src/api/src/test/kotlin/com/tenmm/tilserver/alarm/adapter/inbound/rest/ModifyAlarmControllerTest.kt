package com.tenmm.tilserver.alarm.adapter.inbound.rest

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.ModifyAlarmResponse
import com.tenmm.tilserver.alarm.application.inbound.ModifyAlarmUseCase
import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.randomModifyAlarmRequest
import com.tenmm.tilserver.common.domain.OperationResult
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ModifyAlarmControllerTest {

    @InjectMockKs
    private lateinit var sut: ModifyAlarmController

    @MockK
    private lateinit var modifyAlarmUseCase: ModifyAlarmUseCase

    @AfterEach
    fun tearDown() {
        confirmVerified(modifyAlarmUseCase)
    }
    @Test
    fun `modifyAlarmByUserIdentifier - success`() {

        // given
        val request = randomModifyAlarmRequest()

        // when
        every { modifyAlarmUseCase.modifyAlarm(any()) } returns OperationResult.success()

        // then
        val result = sut.modifyAlarm(randomModifyAlarmRequest())
        val expected = ModifyAlarmResponse(OperationResult.success().isSuccess)

        assertThat(result).isEqualTo(expected)

        verify { modifyAlarmUseCase.modifyAlarm(any()) }
    }

    @Test
    fun `modifyAlarmByUserIdentifier - fail`() {

        // given
        val request = randomModifyAlarmRequest()

        // when
        every { modifyAlarmUseCase.modifyAlarm(any()) } returns OperationResult.fail()

        // then
        val result = sut.modifyAlarm(randomModifyAlarmRequest())
        val expected = ModifyAlarmResponse(OperationResult.fail().isSuccess)

        assertThat(result).isEqualTo(expected)

        verify { modifyAlarmUseCase.modifyAlarm(any()) }
    }
}
