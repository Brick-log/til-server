package com.tenmm.tilserver.account.adapter.outbound.persistence.converters

import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.account.domain.AccountStatus
import com.tenmm.tilserver.account.domain.OAuthType
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
