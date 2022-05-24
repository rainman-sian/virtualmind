package com.virtualmind.codingchallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity representing post table
 */
@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    public static final int MAX_SIZE = 1024;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = MAX_SIZE)
    private String title;

    @Column(name = "text", nullable = false, length = MAX_SIZE)
    private String text;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
}
