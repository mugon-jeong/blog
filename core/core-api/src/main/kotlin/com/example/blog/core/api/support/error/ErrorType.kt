package com.example.blog.core.api.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String, val logLevel: LogLevel) {
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),
    PASSWORD_NOT_MATCH_ERROR(HttpStatus.FORBIDDEN, ErrorCode.A100, "비밀번호가 틀렸습니다.", LogLevel.ERROR),
    MEMBER_ID_DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.A200, "중복된 아이디입니다.", LogLevel.ERROR),
    POST_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.P100, "존재하지 않는 아이디입니다.", LogLevel.ERROR),
    POST_EDIT_PERMISSION_DENIED(HttpStatus.FORBIDDEN, ErrorCode.P200, "게시글 수정 권한이 없습니다.", LogLevel.ERROR),
}
