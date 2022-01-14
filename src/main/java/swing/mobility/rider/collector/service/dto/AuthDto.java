package swing.mobility.rider.collector.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import swing.mobility.rider.collector.domain.model.Collector;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AuthDto {


    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    @JsonProperty(required = false)
    private TokenGrantType grantType;


    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class getMyInfo {
        private String userName;
        private String password;


        public static getMyInfo from(Collector c) {
            return getMyInfo.builder()
                    .userName(c.getUserId())
                    .password(c.getPassword())
                    .build();
        }
    }


    @Data
    public static class updateRefresh {
        private String refreshToken;
        private TokenGrantType grantType;
    }
}
