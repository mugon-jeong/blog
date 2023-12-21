package com.example.blog.storage.db.core.site

import com.example.blog.domain.site.Owner
import com.example.blog.domain.site.Site
import com.example.blog.domain.site.SiteAppendContent
import com.example.blog.storage.db.core.member.MemberEntity

fun SiteAppendContent.toEntity(member: MemberEntity) =
    SiteEntity(
        name = name,
        address = address,
        owner = member,
    )

fun SiteEntity.toDomain() =
    Site(
        id = id,
        name = name,
        address = address,
        owner = owner.toOwner(),
    )

fun MemberEntity.toOwner() =
    Owner(
        id = id,
        name = name,
    )
