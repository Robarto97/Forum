package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {
    ActionEntity findByUserIdAndTopicId(long userId, long topicId);

    @Query(value = "SELECT COUNT(*) FROM actions ac WHERE ac.topic_id = topicId ", nativeQuery = true)
    int countUsersWhoSawTheTopic(long topicId);
}