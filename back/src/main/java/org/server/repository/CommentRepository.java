package org.server.repository;

import org.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий {@code CommentRepository} для управления сущностями комментариев {@link Comment}.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
