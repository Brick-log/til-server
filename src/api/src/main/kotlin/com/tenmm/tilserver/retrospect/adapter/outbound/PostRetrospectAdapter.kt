package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.PostRetrospectPort
import org.springframework.stereotype.Component

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.PostRetrospectRequestModel

import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import java.time.LocalDateTime
import java.sql.Timestamp

@Component
class PostRetrospectAdapter(
    private val retrospectQnaRepository: RetrospectQnaRepository,
    private val retrospectRepository: RetrospectRepository
) : PostRetrospectPort {
    override fun save(userIdentifier: Identifier, postRetrospectRequestModel: PostRetrospectRequestModel) {
        val retrospectIdentifier = Identifier.generate().value

        val retrospectEntity: RetrospectEntity = RetrospectEntity(
            retrospectIdentifier = retrospectIdentifier,
            userIdentifier = userIdentifier.value,
            questionType = postRetrospectRequestModel.questionType,
            isSecret = postRetrospectRequestModel.isSecret,
            createdAt = Timestamp.valueOf(LocalDateTime.now())
        )
        retrospectRepository.save(retrospectEntity)
        postRetrospectRequestModel.retrospect.forEach {
            val retrospectQnaEntity: RetrospectQnaEntity = RetrospectQnaEntity(
                retrospectIdentifier = retrospectIdentifier,
                questionName = it.questionName,
                answer = it.answer,
            )
            retrospectQnaRepository.save(retrospectQnaEntity)
        }
    }
}
