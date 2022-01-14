package swing.mobility.rider.collector.web.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swing.mobility.rider.collector.security.jwt.JwtFilter;
import swing.mobility.rider.collector.service.AuthService;
import swing.mobility.rider.collector.service.dto.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<AuthDto.getMyInfo> getMyMemberInfo() {

        return ResponseEntity.ok(authService.getMyInfo());
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {

        TokenDto tokenDto = authService.login(authDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccessToken());

        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenDto> reissue(@RequestBody AuthDto.updateRefresh updateRefresh) {
        return ResponseEntity.ok(authService.reissue(updateRefresh));
    }

}