package swing.mobility.rider.collector.web.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // common error code
    INVALID_APP_TOKEN(401, "C0001", "Invalid app token"),
    INVALID_ACCESS_TOKEN(401, "C0002", "Invalid access token"),
    BAD_INPUT_ERROR(400, "C0003", "Invalid Bad Input"),
    RESOURCE_NOT_FOUND(404, "C0004", "Resource Not Found"),
    INTERNAL_SERVER_ERROR(500, "C9999", "Internal Server Error"),

    // account error code
    ACCOUNT_NOT_FOUND(404, "A0001", "없는 관리자 계정입니다");



    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
