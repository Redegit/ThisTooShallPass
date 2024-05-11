package org.server.security.auth;

import lombok.RequiredArgsConstructor;
import org.server.entity.User;
import org.server.repository.UserRepository;
import org.server.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервис аутентификации для пациентов и администраторов.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    /**
     * Регистрация нового пользователя в системе, и сохранение его в базу данных (пока что он с правами админа).
     *
     * @param user новый пользователь
     * @return объект {@link AuthenticationResponse} с JWT-токеном и ID пациента
     */
    public AuthenticationResponse registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(Math.toIntExact(user.getId()))
                .build();
    }

//    /**
//     * Аутентифицирует пациента, проверяя его имя пользователя и пароль, и возвращает JWT-токен и ID пациента.
//     *
//     * @param request объект {@link AuthenticationRequest} с данными аутентификации пациента
//     * @return объект {@link AuthenticationResponse} с JWT-токеном и ID пациента
//     */
//    public AuthenticationResponse authenticatePatient(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//        PatientEntity patient = (PatientEntity) patientRepository.findByPhoneNumber(request.getUsername())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(patient);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .id(patient.getId())
//                .build();
//    }


    /**
     * Метод для аутентификации администратора и генерации JWT-токена.
     *
     * @param request Запрос аутентификации администратора
     * @return ResponseEntity со статусом ОК и объектом AuthenticationResponse, содержащим JWT-токен
     */
    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User admin = (User) userRepository.findByName(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}