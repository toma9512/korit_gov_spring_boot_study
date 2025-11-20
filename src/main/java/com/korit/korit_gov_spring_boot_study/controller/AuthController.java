package com.korit.korit_gov_spring_boot_study.controller;

import com.korit.korit_gov_spring_boot_study.dto.request.SigninReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.SignupReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SigninRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SignupRespDto;
import com.korit.korit_gov_spring_boot_study.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private UserService userService;

    public AuthController() {
        userService = UserService.getInstance();
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(userService.signup(signupReqDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninRespDto> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(userService.signin(signinReqDto));
    }

    /*
    파라미터가 없으면 400 에러
    타입이 안맞을 때
    이름이 불일치

    민감한 정보는 쓰지 않도록
     */

    // @GetMapping("/get")
    // public String getUser(@RequestParam String username) {
    //     System.out.println("쿼리 파라미터로 들어온 값 : " + username); // 인텔리제이 실행 창에
    //     return "쿼리 파라미터로 들어온 값 : " + username; // 인터넷 창에
    // }
    //
    // @GetMapping("/get/name")
    // public String getUsername(@RequestParam(value = "name", defaultValue = "홍길동") String username, @RequestParam(required = false) Integer age) {
    //     return username + " " + age;
    // }
    // value = "name" : 변수 명이 username or name이 되도록 설정
    // defaultValue : 기본값 설정
    // required = false : 빈 값이어도 되도록 설정
    // -> Wrapper Class로 설정해야 한다(ex : int는 null이 없으므로 int로 설정해도 오류가 발생)

    // @PostMapping("/signup")
    // public String signup(@RequestBody SignupReqDto signupReqDto) {
    //     System.out.println(signupReqDto);
    //     return signupReqDto.getUsername() + "님 회원가입이 완료되었습니다.";
    // }
    /*
    @RequestBody
    HTTP 요청의 바디에 들어있는 JSON데이터를 자바 객체(DTO)로 변환해서 주입해주는 어노테이션
    클라이언트가 JSON형식으로 데이터를 보내면 서버에서 JSON을 파싱해 알아서 DTO에 대해 매핑 후 주입
    단, JSON데이터의 키와 객체의 멤버변수의 이름과 일치시켜야 한다.
     */
    // @PostMapping("/signin")
    // public String signin(@RequestBody SignupReqDto signupReqDto) {
    //     return signupReqDto.getUsername() + "님, 반갑습니다.";
    // }

    /*
    ResponseEntity
    HTTP 응답 전체를 커스터마이징 해서 응답할 수 있는 Spring 클래스
    상태 코드, 응답 Body, 응답 Header 등등..
    200
    400
    401 : 인증 실패
    403 : 접근권한 없음
    404
    500
     */

    // @PostMapping("/signup")
    // public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
    //     if (signupReqDto.getUsername() == null || signupReqDto.getUsername().isBlank()) {
    //         SignupRespDto signupRespDto = new SignupRespDto("failed", "username을 입력하세요.");
    //         return ResponseEntity.badRequest().body(signupRespDto);
    //     } else if (signupReqDto.getPassword() == null || signupReqDto.getPassword().isBlank()) {
    //         SignupRespDto signupRespDto = new SignupRespDto("failed", "password를 입력하세요.");
    //         return ResponseEntity.badRequest().body(signupRespDto);
    //     }
    //     SignupRespDto signupRespDto = new SignupRespDto("success", "회원가입 성공");
    //     return ResponseEntity.ok(signupRespDto);
    // }
}
