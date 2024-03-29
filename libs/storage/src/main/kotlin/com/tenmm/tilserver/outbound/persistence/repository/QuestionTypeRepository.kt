package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface QuestionTypeRepository : JpaRepository<QuestionTypeEntity, Long> {

    @Query(value = "SELECT * FROM question_type where question_type = :questionType", nativeQuery = true)
    fun findByType(questionType: String): QuestionTypeEntity?

    @Query(value = "SELECT * FROM question_type where is_random = true", nativeQuery = true)
    fun findRandomQuestion(): List<QuestionTypeEntity>

    @Query(value = "SELECT * FROM question_type where is_random = false", nativeQuery = true)
    fun findStaticQuestion(): List<QuestionTypeEntity>
}
