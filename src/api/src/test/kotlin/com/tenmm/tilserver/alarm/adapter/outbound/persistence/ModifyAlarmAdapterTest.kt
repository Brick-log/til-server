package com.tenmm.tilserver.alarm.adapter.outbound.persistence

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tenmm.tilserver.alarm.adapter.outbound.persistence.model.randomModifyAlarmCommand
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity
import com.tenmm.tilserver.outbound.persistence.repository.AlarmRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ModifyAlarmAdapterTest {

    @InjectMockKs
    private lateinit var sut: ModifyAlarmAdapter

    @MockK
    private lateinit var alarmRepository: AlarmRepository
    @Test
    fun `modifyAlarm - success`() {
        // given
        val command = randomModifyAlarmCommand()
        val entity = AlarmEntity(
            userIdentifier = command.userIdentifier.value,
            enable = command.enable,
            iteration = command.iteration
        )

        // when
        every { alarmRepository.findByUserIdentifier(any()) } returns entity
        every { alarmRepository.save(any()) } returns entity

        // then
        val result = sut.modifyByUserIdentifier(command)
        val expected = OperationResult.success().isSuccess

        assertThat(result).isEqualTo(expected)
        verify { alarmRepository.findByUserIdentifier(command.userIdentifier.value) }
        verify { alarmRepository.save(entity.copy(id = 0)) }
    }

    @Test
    fun `modifyAlarm - fail (Exception Occurred)`() {

        // given
        val command = randomModifyAlarmCommand()
        val entity = AlarmEntity(
            userIdentifier = command.userIdentifier.value,
            enable = command.enable,
            iteration = command.iteration
        )

        // when
        every { alarmRepository.findByUserIdentifier(any()) } returns entity
        every { alarmRepository.save(any()) } throws RuntimeException()

        // then
        val result = sut.modifyByUserIdentifier(command)
        val expected = OperationResult.fail().isSuccess

        assertThat(result).isEqualTo(expected)
        verify { alarmRepository.findByUserIdentifier(command.userIdentifier.value) }
    }
}
