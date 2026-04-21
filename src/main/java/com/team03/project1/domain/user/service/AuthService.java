package com.team03.project1.domain.user.service;

import com.team03.project1.domain.user.entity.UserEntity;
import com.team03.project1.domain.user.repository.UserRepository;
import com.team03.project1.exception.InvalidLoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    //로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("auth service email : " +email);
       UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new InvalidLoginException("로그인 실패"));
        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
