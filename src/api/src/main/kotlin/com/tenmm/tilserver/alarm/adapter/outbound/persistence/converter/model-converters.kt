import com.tenmm.tilserver.alarm.domain.Alarm
import com.tenmm.tilserver.alarm.domain.AlarmIteration as AlarmIterationModel
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity
import com.tenmm.tilserver.outbound.persistence.entity.AlarmIteration as AlarmIterationEntity

fun Alarm.toEntity(): AlarmEntity {
    return AlarmEntity(
        userIdentifier = this.userIdentifier.value,
        enable = this.enable,
        iteration = this.iteration.toEntity()
    )
}

fun AlarmIterationModel.toEntity(): AlarmIterationEntity {
    return when (this) {
        AlarmIterationModel.NONE -> AlarmIterationEntity.NONE
        AlarmIterationModel.DAY -> AlarmIterationEntity.DAY
        AlarmIterationModel.WEEK -> AlarmIterationEntity.WEEK
        AlarmIterationModel.MONTH -> AlarmIterationEntity.MONTH
    }
}

fun AlarmEntity.toModel(): Alarm {
    return Alarm(
        userIdentifier = Identifier(this.userIdentifier),
        enable = this.enable,
        iteration = this.iteration.toModel()
    )
}

fun AlarmIterationEntity.toModel(): AlarmIterationModel {
    return when (this) {
        AlarmIterationEntity.NONE -> AlarmIterationModel.NONE
        AlarmIterationEntity.DAY -> AlarmIterationModel.DAY
        AlarmIterationEntity.WEEK -> AlarmIterationModel.WEEK
        AlarmIterationEntity.MONTH -> AlarmIterationModel.MONTH
    }
}
