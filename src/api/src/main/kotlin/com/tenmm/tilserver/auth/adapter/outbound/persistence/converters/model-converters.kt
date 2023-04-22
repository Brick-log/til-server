package com.tenmm.tilserver.auth.adapter.outbound.persistence.converters

import com.tenmm.tilserver.auth.domain.Account
import com.tenmm.tilserver.auth.domain.AccountStatus
import com.tenmm.tilserver.auth.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.AccountEntity

fun AccountEntity.toModel(): Account {
    return Account(
        email = Email(this.email),
        oAuthType = OAuthType.valueOf(this.oAuthType.name),
        status = AccountStatus.valueOf(this.status.name),
        userIdentifier = Identifier(this.userIdentifier),
        isSpamNotificationAgreed = this.isSpamNotificationAgreed

    )
}
