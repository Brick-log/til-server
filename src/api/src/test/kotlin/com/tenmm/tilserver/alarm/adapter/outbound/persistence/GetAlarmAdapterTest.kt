package com.tenmm.tilserver.alarm.adapter.outbound.persistence

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity
import com.tenmm.tilserver.outbound.persistence.repository.AlarmRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import toModel

@ExtendWith(MockKExtension::class)
class GetAlarmAdapterTest {

    @InjectMockKs
    private lateinit var sut: GetAlarmAdapter

    @MockK
    private lateinit var alarmRepository: AlarmRepository

    @Test
    fun `getAlarm - success`() {
        // given
        val userIdentifier = Identifier.generate()
        val entity = AlarmEntity(
            userIdentifier = userIdentifier.value,
            enable = true,
            iteration = "DAY"
            // iteration = "* * * * * *"
        )

        // when
        every { alarmRepository.findByUserIdentifier(userIdentifier.value) } returns entity

        // then
        val result = sut.findByUserIdentifier(userIdentifier)
        val expected = entity.toModel()

        assertThat(result)
            .isNotNull()
            .isEqualTo(expected)

        verify { alarmRepository.findByUserIdentifier(userIdentifier.value) }
    }

    @Test
    fun `getAlarm - fail (Not Exist)`() {
        // given
        val userIdentifier = Identifier.generate()

        // when
        every { alarmRepository.findByUserIdentifier(any()) } returns null

        // then
        val result = sut.findByUserIdentifier(userIdentifier)

        assertThat(result)
            .isNull()

        verify { alarmRepository.findByUserIdentifier(userIdentifier.value) }
    }
}
