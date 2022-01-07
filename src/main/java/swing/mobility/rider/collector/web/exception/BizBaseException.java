package swing.mobility.rider.collector.web.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizBaseException extends RuntimeException {
    private ErrorCode errorCode;
}
