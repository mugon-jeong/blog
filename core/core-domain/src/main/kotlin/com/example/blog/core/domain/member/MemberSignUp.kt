package com.example.blog.core.domain.member

import com.example.blog.core.enums.Gender
import java.time.LocalDate

data class MemberSignUp(
    val memberId: String,
    val memberPw: String,
    val name: String,
    val birth: LocalDate,
    val phoneInfo: PhoneInfo,
    val email: String? = null,
    val platformSettings: PlatformSettings,
    val fcm: String? = null,
    val gender: Gender,
)
