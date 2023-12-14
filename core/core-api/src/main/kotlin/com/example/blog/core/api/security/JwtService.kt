package com.example.blog.core.api.security

import com.example.blog.core.enums.Permission
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class JwtService(
    private val encoder: JwtEncoder,
    @Value("\${jwt.issuer}")
    var issue: String,
    @Value("\${jwt.access.expiry}")
    var accessExpiry: Long,
    @Value("\${jwt.refresh.expiry}")
    var refreshExpiry: Long,
    @Value("\${jwt.invite.expiry}")
    var inviteExpiry: Long,
) {
    fun generateRefreshToken(
        permissionType: Permission,
        id: UUID,
    ): String {
        return generateToken("refresh", refreshExpiry, permissionType, id)
    }

    fun generateAccessToken(
        permissionType: Permission,
        id: UUID,
    ): String {
        return generateToken("access", accessExpiry, permissionType, id)
    }

    fun generateInviteToken(
        siteId: UUID?,
        memberId: UUID?,
    ): String {
        val jwsHeader =
            JwsHeader.with(MacAlgorithm.HS256)
                .type("JWT")
                .build()
        val now = Instant.now()
        val claims =
            JwtClaimsSet.builder()
                .issuer(issue)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(inviteExpiry))
                .subject("invite")
                .claim("site", siteId)
                .claim("member", memberId)
                .build()
        return encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun generateToken(
        subject: String?,
        expiry: Long,
        permissionType: Permission,
        id: UUID,
    ): String {
        val jwsHeader =
            JwsHeader.with(MacAlgorithm.HS256)
                .type("JWT")
                .build()
        val now = Instant.now()
        val claims =
            JwtClaimsSet.builder()
                .issuer(issue)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(subject)
                .claim("permission", permissionType)
                .claim("id", id.toString())
                .build()
        return encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }
}
