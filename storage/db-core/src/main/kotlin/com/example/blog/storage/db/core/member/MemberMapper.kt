package com.example.blog.storage.db.core.member

import com.example.blog.core.domain.member.MemberSignUp
import com.example.blog.core.domain.member.PhoneInfo
import com.example.blog.core.domain.member.PlatformSettings

fun MemberSignUp.toEntity() =
    MemberEntity(
        memberId = memberId,
        memberPw = memberPw,
        name = name,
        birth = birth,
        phoneInfo = phoneInfo.toEntity(),
        email = email,
        platformSettings = platformSettings.toEntity(),
        fcm = fcm,
        gender = gender,
    )

fun PhoneInfo.toEntity() =
    PhoneInfoEmbed(
        _phone = phone,
        _countryCode = countryCode,
    )

fun PlatformSettings.toEntity() =
    PlatformSettingsEmbed(
        _language = language,
        _theme = theme,
    )
