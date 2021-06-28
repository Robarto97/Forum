package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.Counter;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.persistence.entities.Role;
import com.edu.mse.pwc.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
@RolesAllowed(value = {"MODERATOR", "ADMIN", "USER"})
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/{id}/")
    public TopicDto getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @GetMapping
    public List<TopicDto> getAllTopic() {
        return topicService.getAllTopics();
    }

    @PostMapping
    public TopicDto createTopic(@RequestBody TopicDto topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping("/saw/{userId}/{topicId}/")
    public Counter<TopicDto> userSawTopic(@PathVariable long userId, @PathVariable long topicId) {
        this.topicService.markTopicAsSeen(userId, topicId);
        return new Counter<TopicDto>(HttpStatus.OK.value(), "Counted", null);
    }
}
