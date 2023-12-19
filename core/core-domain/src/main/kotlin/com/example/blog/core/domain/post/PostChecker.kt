package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostChecker(
    private val postFinder: PostFinder,
) {
    fun checkOwner(
        postId: UUID,
        checkRequestMemberId: UUID,
    ): Boolean? {
        val post = postFinder.findById(postId)
        return post?.writer?.id == checkRequestMemberId
    }

    fun checkExist(postId: UUID): UUID? = postFinder.findById(postId)?.id

    fun checkCommentOwner(
        postId: UUID,
        commentId: UUID,
        writer: UUID,
    ): Boolean? {
        val post = postFinder.findById(postId)
        return post?.comments?.filter { it.writer.id == writer }.isNullOrEmpty()
    }
}
