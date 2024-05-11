package org.server.security.user;


import org.springframework.security.core.userdetails.UserDetails;

/**
 * Абстрактный класс, представляющий собой пользователя системы, реализующий интерфейс UserDetails.
 * Содержит поля логина и пароля.
 */
public abstract class UserAbstract implements UserDetails {

    /**
     * Логин пользователя.
     */
    private String username;
    /**
     * Пароль пользователя.
     */
    private String password;
}
