package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.domain.post.PostAppender
import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.post.PostFinder
import com.example.blog.core.domain.post.PostRemover
import com.example.blog.core.domain.post.PostSummary
import com.example.blog.core.domain.post.PostUpdater
import com.example.blog.core.domain.support.DomainPage
import com.example.blog.core.domain.support.DomainSort
import com.example.blog.core.enums.PostSort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Transactional(readOnly = true)
@Service
class PostService(
    private val postAppender: PostAppender,
    private val postFinder: PostFinder,
    private val postUpdater: PostUpdater,
    private val postRemover: PostRemover,
) {
    @Transactional
    fun appender(post: PostContent): UUID {
        return postAppender.append(post)
    }

    fun findById(id: UUID) = postFinder.findById(id) ?: throw CoreApiException(ErrorType.POST_NOT_FOUND_ERROR)

    @Transactional
    fun updater(
        postId: UUID,
        updaterId: UUID,
        title: String,
        content: String,
    ): UUID {
        val post = postFinder.findById(postId) ?: throw CoreApiException(ErrorType.POST_NOT_FOUND_ERROR)
        if (post.writer.id != updaterId) throw CoreApiException(ErrorType.POST_EDIT_PERMISSION_DENIED)
        return postUpdater.update(post.id, title, content)
    }

    fun findPage(pageable: Pageable): DomainPage<PostSummary> {
        logger.info {
            """
            pageable: $pageable
            pageable.sort: ${pageable.sort}
            pageable.sort.get: ${pageable.sort.get()}
            """.trimIndent()
        }
        val postSort =
            when (pageable.sort.getOrderFor("createdAt")) {
                Sort.Order.asc("createdAt") -> DomainSort(PostSort.CREATED_AT, true)
                Sort.Order.desc("createdAt") -> DomainSort(PostSort.CREATED_AT, false)
                else -> DomainSort(PostSort.CREATED_AT, true) // 기본값 설정
            }

        return postFinder.findPage(
            pageNumber = pageable.pageNumber,
            pageSize = pageable.pageSize,
            domainSort = postSort,
        )
    }

    @Transactional
    fun deleteById(
        postId: UUID,
        loginMemberId: UUID,
    ): UUID {
        val (_, _, _, writer, _, _) = findById(postId)
        if (writer.id != loginMemberId) throw CoreApiException(ErrorType.POST_DELETE_PERMISSION_DENIED)
        return postRemover.deleter(postId)
    }
}
