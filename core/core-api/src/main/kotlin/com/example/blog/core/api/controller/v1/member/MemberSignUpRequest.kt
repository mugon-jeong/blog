package com.example.blog.core.api.controller.v1.member

import com.example.blog.core.domain.member.MemberSignUp
import com.example.blog.core.domain.member.PhoneInfo
import com.example.blog.core.domain.member.PlatformSettings
import com.example.blog.core.enums.Gender
import java.time.LocalDate

data class MemberSignUpRequest(
    val memberId: String,
    val memberPw: String,
    val name: String,
    val birth: LocalDate,
    val phone: String,
    val countryCode: String,
    val email: String? = null,
    val language: String? = null,
    val theme: String? = null,
    val fcm: String? = null,
    val gender: Gender,
)

fun MemberSignUpRequest.toDomain() =
    MemberSignUp(
        memberId = memberId,
        memberPw = memberPw,
        name = name,
        birth = birth,
        phoneInfo =
            PhoneInfo(
                phone = phone,
                countryCode = countryCode,
            ),
        email = email,
        platformSettings =
            PlatformSettings(
                language = language,
                theme = theme,
            ),
        fcm = fcm,
        gender = gender,
    )
