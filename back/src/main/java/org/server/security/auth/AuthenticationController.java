package org.server.security.auth;


import lombok.RequiredArgsConstructor;
import org.server.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для обработки запросов авторизации
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    /**
     * Сервис, который осуществляет регистрацию и аутентификацию пользователей
     */
    private final AuthenticationService service;

    /**
     * Обрабатывает POST-запрос на регистрацию пользователя
     *
     * @param user - сущность user, полученная из тела запроса
     * @return объект ResponseEntity с телом AuthenticationResponse в случае успешной регистрации
     */
    @PostMapping("/user/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(service.registerUser(user));
    }

    /**
     * Обрабатывает POST-запрос на аутентификацию пользователя
     *
     * @param request - объект AuthenticationRequest, содержащий имя пользователя и пароль
     * @return объект ResponseEntity с телом AuthenticationResponse в случае успешной аутентификации
     */
    @PostMapping("/user/login")
    public ResponseEntity<AuthenticationResponse> authenticatePatient(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticateUser(request));
    }

//    /**
//     * Обрабатывает POST-запрос на аутентификацию администратора
//     *
//     * @param request - объект AuthenticationRequest, содержащий имя пользователя и пароль
//     * @return объект ResponseEntity с телом AuthenticationResponse в случае успешной аутентификации
//     */
//    @PostMapping("/admin/login")
//    public ResponseEntity<AuthenticationResponse> authenticateAdmin(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return ResponseEntity.ok(service.authenticateAdmin(request));
//    }
}