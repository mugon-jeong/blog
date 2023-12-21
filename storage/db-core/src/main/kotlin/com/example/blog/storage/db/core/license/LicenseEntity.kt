package com.example.blog.storage.db.core.license

import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.member.MemberEntity
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "license")
class LicenseEntity : PrimaryKey() {
    @OneToOne(optional = true, mappedBy = "license")
    private val member: MemberEntity? = null
}
