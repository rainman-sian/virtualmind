package com.virtualmind.codingchallenge.repository;

import com.virtualmind.codingchallenge.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    // This interface will inherit List<Post> findAll(Pageable p) function
}
