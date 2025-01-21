package com.file.reader.Excel_File_Reader.service.impl;

import com.file.reader.Excel_File_Reader.auth.AuthenticationRequest;
import com.file.reader.Excel_File_Reader.auth.AuthenticationResponse;
import com.file.reader.Excel_File_Reader.auth.RegisterRequest;
import com.file.reader.Excel_File_Reader.entity.User;
import com.file.reader.Excel_File_Reader.repository.UserRepository;
import com.file.reader.Excel_File_Reader.service.AuthenticationService;
import com.file.reader.Excel_File_Reader.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getUserPassword())
        );

        User user = userRepository.findByEmail(request.getEmail());

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
