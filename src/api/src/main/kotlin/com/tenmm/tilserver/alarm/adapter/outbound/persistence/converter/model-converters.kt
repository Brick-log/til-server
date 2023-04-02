import com.tenmm.tilserver.alarm.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity

fun Alarm.toEntity(): AlarmEntity {
    return AlarmEntity(
        userIdentifier = this.userIdentifier.toString(),
        enable = this.enable,
        iteration = this.iteration
    )
}

fun AlarmEntity.toModel(): Alarm {
    return Alarm(
        userIdentifier = Identifier(this.userIdentifier),
        enable = this.enable,
        iteration = this.iteration
    )
}
