package swing.mobility.rider.collector.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import swing.mobility.rider.collector.domain.model.Authority;
import swing.mobility.rider.collector.domain.model.Collector;

import static swing.mobility.rider.collector.domain.model.Authority.ROLE_ADMIN;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MemberRequestDto {

    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private Long bandId;
    private Authority authority;


    public Collector toMember(PasswordEncoder passwordEncoder) {
        return Collector.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .bandId(bandId)
                .authority(ROLE_ADMIN)
                .build();
    }
}
