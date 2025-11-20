package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.request.SigninReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.SignupReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SigninRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SignupRespDto;
import com.korit.korit_gov_spring_boot_study.entity.User;
import com.korit.korit_gov_spring_boot_study.repository.UserRepository;

import java.util.List;

public class UserService {
    private static UserService instance;
    private UserRepository userRepository;

    private UserService() {
        userRepository = UserRepository.getInstance();
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public SignupRespDto signup(SignupReqDto signupReqDto) {
        if (signupReqDto.getUsername() == null || signupReqDto.getPassword() == null ||
            signupReqDto.getUsername().isBlank() || signupReqDto.getPassword().isBlank()) {
            return new SignupRespDto("failed", "username과 password를 입력해주세요");
        }

        if (userRepository.findUserByUsername(signupReqDto.getUsername()).isPresent()) {
            return new SignupRespDto("failed", "중복된 username입니다.");
        }

        userRepository.addUser(signupReqDto.toEntity());
        return new SignupRespDto("success", "회원 가입 성공");
    }

    public SigninRespDto signin(SigninReqDto signinReqDto) {
        User foundUser = userRepository.findUserByUsername(signinReqDto.getUsername()).orElse(null);

        if (foundUser == null || !foundUser.getPassword().equals(signinReqDto.getPassword())) {
            return new SigninRespDto("failed", "username과 password를 다시 확인해 주세요");
        }

        return new SigninRespDto("success", "로그인 성공");
    }

    public List<User> getUserAll() {
        return userRepository.getUserAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }
}
