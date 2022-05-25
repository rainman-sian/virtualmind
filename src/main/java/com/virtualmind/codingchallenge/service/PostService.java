package com.virtualmind.codingchallenge.service;

import com.virtualmind.codingchallenge.dto.PostDTO;
import com.virtualmind.codingchallenge.model.Post;
import com.virtualmind.codingchallenge.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    @PersistenceContext
    private EntityManager entityManager;

    private int pageSize = 25; // In real life would be configurable

    private final PostRepository postRepository;

    private final ModelMapper mapper;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    public List<PostDTO> listPostTitlesAndTopics(int pageNumber) {

        // The following will query for all the records of Post (and related Topics) in database
        // then will extract a set of records based on pagination parameters
        // this is not correct since this will return a lot of unnecessary data everytime this method is called.
        List<Post> posts = entityManager
                .createQuery("SELECT p FROM Post p")
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        List<PostDTO> result = new ArrayList<>(posts.size());

        for (Post post: posts) {
             PostDTO postDto = new PostDTO();
             postDto.setId(post.getId());
             postDto.setTitle(post.getTitle());
             postDto.setTopicName(post.getTopic().getName());
             result.add(postDto);
        }

        return result;
    }

    /**
     * Proposed solution to listPostTitlesAndTopics function
     */
    public List<PostDTO> listPostTitlesAndTopics2(int pageNumber) {
        // I create a pageable object
        Pageable currentPage = PageRequest.of(pageNumber, pageSize);
        // I request a query with properly pagination
        List<Post> results = postRepository.findAll(currentPage).toList();
        // and finally, I map the list of entities to a list of DTOs
        return results.stream().map(post -> mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }
}
