package com.tenmm.tilserver.alarm.adapter.inbound.rest

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.ModifyAlarmResponse
import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.randomModifyAlarmRequest
import com.tenmm.tilserver.alarm.application.inbound.ModifyAlarmUseCase
import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ModifyAlarmControllerTest {

    @InjectMockKs
    private lateinit var sut: ModifyAlarmController

    @MockK
    private lateinit var modifyAlarmUseCase: ModifyAlarmUseCase

    @Test
    fun `modifyAlarmByUserIdentifier - success`() {

        // given
        val request = randomModifyAlarmRequest()

        // when
        every { modifyAlarmUseCase.modifyAlarm(any()) } returns OperationResult.success()

        // then
        val result = sut.modifyAlarm(UserAuthInfo(Identifier.generate()), request)
        val expected = ModifyAlarmResponse(OperationResult.success().isSuccess)

        assertThat(result).isEqualTo(expected)

        /**
         * TODO
         * modifyAlarmUseCase.modifyAlarm(command)에 대한 verify() 필요.
         * 현재는 userIdentifier를 Controller단에서 임의로 넣어주고 있기 떄문에, 랜덤한 값이라서 정확한 검증 불가
         * 추후, JWT를 통한 userIdentifier 파싱이 가능해지면 해당 테스트 코드 추가 요망
         */
    }

    @Test
    fun `modifyAlarmByUserIdentifier - fail`() {

        // given
        val request = randomModifyAlarmRequest()

        // when
        every { modifyAlarmUseCase.modifyAlarm(any()) } returns OperationResult.fail()

        // then
        val result = sut.modifyAlarm(UserAuthInfo(Identifier.generate()), request)
        val expected = ModifyAlarmResponse(OperationResult.fail().isSuccess)

        assertThat(result).isEqualTo(expected)

        /**
         * TODO
         * modifyAlarmUseCase.modifyAlarm(command)에 대한 verify() 필요.
         * 현재는 userIdentifier를 Controller단에서 임의로 넣어주고 있기 떄문에, 랜덤한 값이라서 정확한 검증 불가
         * 추후, JWT를 통한 userIdentifier 파싱이 가능해지면 해당 테스트 코드 추가 요망
         */
    }
}
