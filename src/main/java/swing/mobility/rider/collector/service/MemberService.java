package swing.mobility.rider.collector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swing.mobility.rider.collector.domain.repository.MemberRepository;
import swing.mobility.rider.collector.security.SecurityUtil;
import swing.mobility.rider.collector.service.dto.MemberResponseDto;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String userId) {
        return memberRepository.findByUserId(userId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
}
