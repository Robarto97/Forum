package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    Page<ReplyEntity> findByTopicId(Long topicId, Pageable paging);

}
