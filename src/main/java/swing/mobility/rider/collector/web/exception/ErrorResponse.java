package swing.mobility.rider.collector.web.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    Integer status;
    String code;
    String message;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime timestamp;

    @Builder
    public ErrorResponse(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse badRequest() {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.BAD_INPUT_ERROR.getCode())
                .message(ErrorCode.BAD_INPUT_ERROR.getMessage())
                .build();
    }
}
