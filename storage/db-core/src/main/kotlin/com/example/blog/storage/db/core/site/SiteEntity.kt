package com.example.blog.storage.db.core.site

import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.member.MemberEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(name = "site")
class SiteEntity(
    name: String,
    address: String,
    owner: MemberEntity,
) : PrimaryKey() {
    @Comment("이름")
    @Column(name = "name", length = 50, nullable = false)
    var name: String = name

    @Comment("주소")
    @Column(name = "address", columnDefinition = "TEXT")
    var address: String = address

    @Comment("소유자")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var owner: MemberEntity = owner
        protected set

    /**
     * this' 누수 경고는 객체 초기화가 완료되기 전에 다른 메서드에게 현재 객체의 참조를 제공할 때 발생하는 것을 의미
     * 여기서는 객체 초기화가 완료된 후에 writer.writeBoard(this)가 호출되므로 이 부분에서 발생하는 누수는 아님
     */
    init {
        owner.ownSite(this)
    }
}
