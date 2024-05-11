package org.server.security.user;

import org.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий, предоставляющий метод для поиска пользователя по его имени пользователя.
 */
@Repository
public class UserAuthRepository {

    /**
     * Репозиторий пациентов, используемый для поиска пациентов по имени пользователя.
     */
    private final UserRepository userRepository;


    /**
     * Конструктор, инициализирующий репозитории пациентов и администраторов.
     *
     * @param userRepository Репозиторий пациентов.
     */
    public UserAuthRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод для поиска пользователя по его имени пользователя.
     * Если пользователь с таким именем найден в репозитории пациентов, возвращается соответствующий пациент,
     * если в репозитории администраторов - возвращается соответствующий администратор.
     * Если пользователь не найден в обоих репозиториях, выбрасывается исключение UsernameNotFoundException.
     *
     * @param username Имя пользователя.
     * @return Найденный пользователь.
     * @throws UsernameNotFoundException Исключение, выбрасываемое, если пользователь не найден.
     */
    public Optional<UserAbstract> findByUsername(String username) {
//        Optional<UserAbstract> admin = adminRepository.findByUsername(username);
//        if (admin.isPresent()) {
//            return admin;
//        }
        Optional<UserAbstract> user = userRepository.findByName(username);
        if (user.isPresent()) {
            return user;
        }
        throw new UsernameNotFoundException("User not found");
    }
}