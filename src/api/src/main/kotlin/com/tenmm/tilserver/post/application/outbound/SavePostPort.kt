package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.post.domain.Post

interface SavePostPort {
    fun save(post: Post): Boolean
}
