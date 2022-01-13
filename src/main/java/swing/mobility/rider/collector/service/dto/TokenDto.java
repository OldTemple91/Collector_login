package swing.mobility.rider.collector.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenDto {

    //private String grantType;
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;


}
