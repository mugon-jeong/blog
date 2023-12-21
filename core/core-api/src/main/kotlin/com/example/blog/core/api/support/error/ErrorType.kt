package com.example.blog.core.api.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String, val logLevel: LogLevel) {
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),
    PASSWORD_NOT_MATCH_ERROR(HttpStatus.FORBIDDEN, ErrorCode.A200, "비밀번호가 틀렸습니다.", LogLevel.ERROR),
    MEMBER_ID_DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.A300, "중복된 아이디입니다.", LogLevel.ERROR),
    POST_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.P100, "존재하지 않는 POST 입니다.", LogLevel.ERROR),
    POST_EDIT_PERMISSION_DENIED(HttpStatus.FORBIDDEN, ErrorCode.P200, "게시글 수정 권한이 없습니다.", LogLevel.ERROR),
    ACCESS_DENY(HttpStatus.FORBIDDEN, ErrorCode.A100, "접근 권한이 없습니다.", LogLevel.ERROR),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, ErrorCode.T100, "토큰이 없습니다.", LogLevel.ERROR),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, ErrorCode.T200, "토큰이 유효하지 않습니다..", LogLevel.ERROR),
    POST_DELETE_PERMISSION_DENIED(HttpStatus.FORBIDDEN, ErrorCode.P200, "게시글 삭제 권한이 없습니다.", LogLevel.ERROR),
    SITE_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.P100, "존재하지 않는 SITE 입니다.", LogLevel.ERROR),
}
