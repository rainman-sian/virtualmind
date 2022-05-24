package com.virtualmind.codingchallenge.dto;



import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {

    private Long id;

    @NotNull(message = "Name can't be null")
    private String name;
}
