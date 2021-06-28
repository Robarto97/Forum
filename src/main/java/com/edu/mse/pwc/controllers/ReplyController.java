package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto reply) {
        return replyService.createReply(reply);
    }

    @PutMapping("/{replyId}/")
    public ReplyDto updateReply(@PathVariable Long replyId, @RequestBody ReplyDto reply) {
        return replyService.updateReply(replyId, reply);
    }
}
