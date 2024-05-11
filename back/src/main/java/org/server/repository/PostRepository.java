package org.server.repository;

import org.server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий {@code PostRepository} для управления сущностями постов {@link Post}.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
