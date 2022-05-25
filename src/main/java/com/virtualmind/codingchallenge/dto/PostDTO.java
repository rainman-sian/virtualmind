package com.virtualmind.codingchallenge.dto;


import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    Long id;

    @NotNull(message = "Title can't be null")
    @Max(value = 16536, message = "Text can't be longer that 16536")
    String title;

    @NotNull(message = "Text can't be null")
    @Max(value = 16536, message = "Text can't be longer that 16536")
    String text;

    @NotNull(message = "Topic can't be null")
    TopicDTO topicDTO;

    public void setTopicName(String topicName) {
        if (topicDTO == null) {
            topicDTO = TopicDTO.builder().name(topicName).build();
        } else {
            topicDTO.setName(topicName);
        }
    }
}
