package com.example.blog.core.domain.member

import com.example.blog.core.enums.Gender
import com.example.blog.core.enums.Permission
import java.time.LocalDate
import java.util.UUID

data class Member(
    val id: UUID,
    val memberId: String,
    val memberPw: String,
    val name: String,
    val birth: LocalDate,
    val phoneInfo: PhoneInfo,
    val email: String? = null,
    val platformSettings: PlatformSettings?,
    val fcm: String? = null,
    val gender: Gender,
    val permission: Permission,
)
