package swing.mobility.rider.collector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swing.mobility.rider.collector.domain.model.Collector;
import swing.mobility.rider.collector.domain.model.RefreshToken;
import swing.mobility.rider.collector.domain.repository.MemberRepository;
import swing.mobility.rider.collector.security.jwt.TokenProvider;
import swing.mobility.rider.collector.service.dto.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Collector member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public TokenDto login(AuthDto authDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authDto.getUserName(), authDto.getPassword());

        Authentication authentication = null;
        if (authDto.getGrantType() == TokenGrantType.PASSWORD) {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } else {
            throw new RuntimeException("GrantType 정보가 일치하지 않습니다.");
        }

        return tokenProvider.generateTokenDto(authentication);
    }

    @Transactional
    public TokenDto reissue(AuthDto.updateRefresh updateRefresh) {

        if (!tokenProvider.validateToken(updateRefresh.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }else if(updateRefresh.getGrantType() != TokenGrantType.REFRESH_TOKEN){
            throw new RuntimeException("GrantType 정보가 일치하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(updateRefresh.getRefreshToken());

        return tokenProvider.generateTokenDto(authentication);
    }
}
