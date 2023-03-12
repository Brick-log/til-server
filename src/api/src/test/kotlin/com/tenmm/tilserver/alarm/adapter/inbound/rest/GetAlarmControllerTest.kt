package com.tenmm.tilserver.alarm.adapter.inbound.rest

import com.tenmm.tilserver.alarm.application.inbound.GetAlarmUseCase
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * TODO
 * Header에서 UserIdentifier가져온 이후에 테스트코드 작성하기
 */
@Disabled
@ExtendWith(MockKExtension::class)
class GetAlarmControllerTest {

    @InjectMockKs
    private lateinit var sut: GetAlarmController

    @MockK
    private lateinit var getAlarmUseCase: GetAlarmUseCase

    @Test
    fun `getAlarm - success`() {
        // given

        // when

        // then
    }

    @Test
    fun `getAlarm - fail (Not Exist)`() {
        // given

        // when

        // then
    }
}
