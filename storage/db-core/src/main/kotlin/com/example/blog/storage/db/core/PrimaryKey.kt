package com.example.blog.storage.db.core

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.Objects
import java.util.UUID

/**
 * Persistable인터페이스는 getId와 isNew 함수를 제공하는데
 * isNew함수는 언급한 entityInformation.isNew(entity)에서 활용
 * Persistable인터페이스를 구현한 Entity를 영속화하면
 * JpaPersistableEntityInformation.isNew함수가 호출되며 여기서 Persistable.isNew함수를 호출
 */
@MappedSuperclass
abstract class PrimaryKey : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid()

    /**
     * entityInformation.isNew가 true이면
     * 즉, Entity가 새롭게 생성되었다면 EntityManager.persist 함수를,
     * 이미 존재하는 Entity라면 EntityManager.merge함수를 실행
     * Entity의 신규 생성 여부
     * 영속화는 하지 않도록 @Transient를 선언
     */
    @Transient
    private var isNew = true

    override fun getId(): UUID = id

    override fun isNew(): Boolean = isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        /**
         * lazy loading일 경우 동등성 비교 실패
         * 지연 로딩으로 인해 아직 쿼리가 실행되지 않았으므로 HibernateProxy객체로 존재
         */
        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return when (obj) {
            is HibernateProxy -> obj.hibernateLazyInitializer.identifier as Serializable
            else -> (obj as PrimaryKey).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)

    /**
     * 영속화를 한 이후와 Entity를 조회하였을 때는 isNew가 false가 되어야 함
     */
    @PostPersist
    @PostLoad
    protected fun load() {
        isNew = false
    }
}
