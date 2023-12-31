package com.example.blog.storage.db.core.member

import com.example.blog.core.enums.Gender
import com.example.blog.core.enums.Permission
import com.example.blog.core.enums.TempPwStatus
import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.license.LicenseEntity
import com.example.blog.storage.db.core.post.PostEntity
import com.example.blog.storage.db.core.site.SiteEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Entity
@Table(name = "member")
class MemberEntity(
    memberId: String,
    memberPw: String,
    name: String,
    birth: LocalDate,
    phoneInfo: PhoneInfoEmbed,
    email: String? = null,
    platformSettings: PlatformSettingsEmbed? = null,
    fcm: String? = null,
    gender: Gender,
) : PrimaryKey() {
    @Comment("로그인 ID")
    @Column(name = "member_id", length = 50, nullable = false)
    var memberId: String = memberId
        protected set

    @Comment("로그인 PW")
    @Column(name = "member_pw", length = 100, nullable = false)
    var memberPw: String = memberPw
        protected set

    @Comment("사용자 이름")
    @Column(name = "name", length = 50, nullable = false)
    var name: String = name
        protected set

    @Comment("생년월일")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    var birth: LocalDate = birth
        protected set

    @Embedded
    var phoneInfo: PhoneInfoEmbed = phoneInfo

    @Comment("이메일")
    @Column(length = 50)
    var email: String? = email

    @Comment("성별")
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: Gender = gender
        protected set

    @Comment("접근권한")
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    var permission: Permission = Permission.USER
        protected set

    @Comment("임시 비밀번호 발급 여부 ( Y, N )")
    @Column(length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    var tempPwStatus: TempPwStatus = TempPwStatus.N
        protected set

    @Comment("라이센스")
    @OneToOne(optional = true)
    @JoinColumn(name = "license_id")
    var license: LicenseEntity? = null

    @Embedded
    var platformSettings: PlatformSettingsEmbed? = platformSettings

    @Comment("FCM")
    @Column(name = "fcm")
    var fcm: String? = fcm

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "writer")
    protected val mutableBoards: MutableList<PostEntity> = mutableListOf()
    val blogs: List<PostEntity> get() = mutableBoards.toList()

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "owner")
    protected val mutableSites: MutableList<SiteEntity> = mutableListOf()
    val sites: List<SiteEntity> get() = mutableSites.toList()

    fun writeBoard(blog: PostEntity) {
        mutableBoards.add(blog)
    }

    fun ownSite(site: SiteEntity) {
        mutableSites.add(site)
    }

    fun grant(license: LicenseEntity) {
        this.license = license
    }
}

@Embeddable
data class PlatformSettingsEmbed(
    @Comment("언어")
    @Column(name = "language", length = 50)
    private var _language: String?,
    @Comment("테마")
    @Column(name = "theme", length = 50)
    private var _theme: String?,
) {
    val language: String? get() = _language
    val theme: String? get() = _theme
}

@Embeddable
data class PhoneInfoEmbed(
    @Comment("핸드폰번호")
    @Column(name = "phone", length = 50, nullable = false)
    private var _phone: String,
    @Comment("국가번호")
    @Column(name = "country_code", length = 10)
    private var _countryCode: String,
) {
    val phone: String get() = _phone
    val countryCode: String get() = _countryCode
}
