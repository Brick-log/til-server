package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.RetrospectQna

fun RetrospectQnaEntity.toQnaModel(): RetrospectQna {
    return RetrospectQna(
        questionName = this.questionName,
        answer = this.answer,
    )
}
