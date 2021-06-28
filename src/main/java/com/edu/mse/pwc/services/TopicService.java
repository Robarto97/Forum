package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.Counter;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.TopicNotFoundException;
import com.edu.mse.pwc.mappers.TopicMapper;
import com.edu.mse.pwc.persistence.entities.ActionEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.repository.ActionRepository;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final ActionRepository actionRepository;

    public TopicDto createTopic(TopicDto topic) {
        TopicEntity topicEntity = topicMapper.topicDtoToEntity(topic);
        TopicEntity savedTopic = topicRepository.save(topicEntity);
        return topicMapper.topicEntityToDto(savedTopic);
    }

    public TopicDto getTopic(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            TopicEntity topicEntity = byId.get();
            return topicMapper.topicEntityToDto(topicEntity);
        }
        throw new TopicNotFoundException("No topic with id " + id + " was found");
    }

    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream().map(topicMapper::topicEntityToDto).collect(Collectors.toList());
    }

    public TopicEntity getTopicEntity(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new TopicNotFoundException("No topic with id " + id + " was found");
    }

    public void markTopicAsSeen(long userId, long topicId) {
        ActionEntity act = actionRepository.findByUserIdAndTopicId(userId, topicId);
        if (act == null) {
            ActionEntity newAct = new ActionEntity();
            newAct.setTopicId(topicId);
            newAct.setUserId(userId);
            newAct.setSeen(true);
            actionRepository.save(newAct);
        }
    }

}
