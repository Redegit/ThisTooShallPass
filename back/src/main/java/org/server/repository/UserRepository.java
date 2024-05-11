package org.server.repository;

import org.server.entity.User;
import org.server.security.user.UserAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий {@code UserRepository} для управления сущностями администратора {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Поиск пользователя по имени.
     *
     * @param name имя пользователя
     * @return найденный пользователь или null, если не найден
     */
    Optional<UserAbstract> findByName(String name);

}
