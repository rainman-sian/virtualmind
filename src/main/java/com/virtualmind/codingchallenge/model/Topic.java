package com.virtualmind.codingchallenge.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing Topic table
 */
@Entity
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "name", nullable = false, length = 255)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true, mappedBy = "topic")
    @Setter(AccessLevel.PRIVATE)
    private List<Post> posts = new ArrayList<>();

    /**
     * Returns an unmodifiable List containing the posts elements
     */
    public List<Post> getPosts() {
        return List.copyOf(posts);
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
