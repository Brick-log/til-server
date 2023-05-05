package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "alarm")
@Entity
data class AlarmEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userIdentifier: String,

    @Column
    val enable: Boolean,

    @Column
    @Enumerated(EnumType.STRING)
    val iteration: AlarmIteration = AlarmIteration.NONE
)

enum class AlarmIteration {
    MONHT, WEEK, DAY, NONE
}