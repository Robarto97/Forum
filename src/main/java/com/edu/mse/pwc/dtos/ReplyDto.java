package com.edu.mse.pwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long id;
    private Date modifiedAt;
    private String text;
    private Long topicId;

}
