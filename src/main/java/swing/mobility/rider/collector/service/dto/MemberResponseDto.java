package swing.mobility.rider.collector.service.dto;

import lombok.Builder;
import lombok.Getter;
import swing.mobility.rider.collector.domain.model.Collector;

@Getter
@Builder
public class MemberResponseDto {

    private String userId;
    private String password;

    public static MemberResponseDto of(Collector save) {
        return MemberResponseDto.builder()
                .userId(save.getUserId())
                .password(save.getPassword())
                .build();
    }
}
