package com.tenmm.tilserver.draft.adapter.outbound.model

// import javax.persistence.Column
// import javax.persistence.Entity
// import javax.persistence.GeneratedValue
// import javax.persistence.GenerationType
// import javax.persistence.Id
import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp

// @Entity(name = "drafts")
class DraftEntity(
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    val Id: Long = 0,

    // @Column(unique = true)
    val UserIdentifier: Identifier,

    // @Column(length = 2000)
    val Data: String,

    // @Column
    val UpdatedAt: Timestamp,
)
