package com.example.blog.storage.db.core.member

import com.example.blog.core.domain.member.Member
import com.example.blog.core.domain.member.MemberSignUp
import com.example.blog.core.domain.member.PhoneInfo
import com.example.blog.core.domain.member.PlatformSettings
import com.example.blog.core.domain.post.Writer

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

fun MemberEntity.toDomain() =
    Member(
        id = id,
        memberId = memberId,
        memberPw = memberPw,
        name = name,
        birth = birth,
        phoneInfo = phoneInfo.toDomain(),
        email = email,
        platformSettings = platformSettings?.toDomain(),
        fcm = fcm,
        gender = gender,
        permission = permission,
    )

fun PhoneInfoEmbed.toDomain() =
    PhoneInfo(
        phone = phone,
        countryCode = countryCode,
    )

fun PlatformSettingsEmbed.toDomain() =
    PlatformSettings(
        language = language,
        theme = theme,
    )
fun MemberEntity.toWriter() = Writer(
    id = id,
    name = name,
)
