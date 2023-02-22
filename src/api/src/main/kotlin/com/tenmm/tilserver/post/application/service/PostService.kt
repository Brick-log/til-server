package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.DeletePostUseCase
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import java.sql.Timestamp
import org.springframework.stereotype.Service

@Service
class PostService : GetPostUseCase, SavePostUseCase, DeletePostUseCase {
    override fun deleteByIdentifier(postIdentifier: Identifier): OperationResult {
        TODO("Not yet implemented")
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): GetPostResult {
        TODO("Not yet implemented")
    }

    override fun getPostListRandom(): GetPostListResult {
        TODO("Not yet implemented")
    }

    override fun getPostListByCategory(categoryIdentifier: Identifier): GetPostListResult {
        TODO("Not yet implemented")
    }

    override fun getPostListByNameAndDate(name: String, to: Timestamp, from: Timestamp, size: Long): GetPostListResult {
        TODO("Not yet implemented")
    }

    override fun getPostListByNameAndDateWithPageToken(
        name: String,
        to: Timestamp,
        from: Timestamp,
        size: Long,
        pageToken: String,
    ): GetPostListResult {
        TODO("Not yet implemented")
    }

    override fun getPostMetaListByNameAndDate(name: String, to: Timestamp, from: Timestamp): GetPostMetaResult {
        TODO("Not yet implemented")
    }

    override fun getPostCountByMonth(name: String, month: Int): Int {
        TODO("Not yet implemented")
    }

    override fun requestSave(command: PostSaveRequestCommand): PostSaveRequestResult {
        TODO("Not yet implemented")
    }

    override fun confirmSave(command: PostSaveConfirmCommand): PostSaveConfirmResult {
        TODO("Not yet implemented")
    }
}
