import com.tenmm.tilserver.alarm.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity

import com.tenmm.tilserver.alarm.domain.AlarmIteration

fun Alarm.toEntity(): AlarmEntity {
    return AlarmEntity(
        userIdentifier = this.userIdentifier.value,
        enable = this.enable,
        iteration = this.iteration.name
    )
}

fun AlarmEntity.toModel(): Alarm {
    return Alarm(
        userIdentifier = Identifier(this.userIdentifier),
        enable = this.enable,
        iteration = AlarmIteration.valueOf(this.iteration)
    )
}
