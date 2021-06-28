package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.ReplyNotFoundException;
import com.edu.mse.pwc.exceptions.TopicNotFoundException;
import com.edu.mse.pwc.mappers.ReplyMapper;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.persistence.repository.ReplyRepository;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final TopicService topicService;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public ReplyDto createReply(ReplyDto reply) {
        Long topicId = reply.getTopicId();
        TopicEntity topicEntity = topicService.getTopicEntity(topicId);
        ReplyEntity replyEntity = replyMapper.replyDtoToEntity(reply);
        replyEntity.setTopic(topicEntity);
        ReplyEntity newReplyEntity = replyRepository.save(replyEntity);
        return replyMapper.replyEntityToDto(newReplyEntity);
    }


    public ReplyDto updateReply(Long replyId, ReplyDto reply) {
        Optional<ReplyEntity> byId = replyRepository.findById(replyId);
        if (!byId.isPresent()) {
            throw new ReplyNotFoundException("There is no reply with id " + replyId);
        }
        ReplyEntity replyEntity = byId.get();
        replyEntity.setText(reply.getText());

        ReplyEntity updated = replyRepository.save(replyEntity);
        return replyMapper.replyEntityToDto(updated);
    }
}
