package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult

interface SavePostUseCase {
    suspend fun requestSave(command: PostSaveRequestCommand): PostSaveRequestResult
    fun confirmSave(command: PostSaveConfirmCommand): PostSaveConfirmResult
}
