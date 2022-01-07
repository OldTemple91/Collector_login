package swing.mobility.rider.collector.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swing.mobility.rider.collector.service.MemberService;
import swing.mobility.rider.collector.service.dto.MemberResponseDto;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {

        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.getMemberInfo(userId));
    }
}
