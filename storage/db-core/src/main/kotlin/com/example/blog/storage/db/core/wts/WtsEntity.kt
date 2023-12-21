package com.example.blog.storage.db.core.wts

import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.site.SiteEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(name = "wts")
class WtsEntity(
    name: String,
    site: SiteEntity,
) : PrimaryKey() {
    @Comment("이름")
    @Column(name = "name", length = 50, nullable = false)
    var name: String = name

    @Comment("소유자")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var site: SiteEntity = site
        protected set

    init {
        site.addWts(this)
    }
}
