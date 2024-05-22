package org.server.repository;

import org.server.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий {@code TopicRepository} для управления сущностями комментариев {@link Topic}.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
