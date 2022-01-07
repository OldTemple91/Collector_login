package swing.mobility.rider.collector.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;
}
