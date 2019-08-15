package com.subhadeep.rest.webservices.restfulwebservices.service;

import com.subhadeep.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.subhadeep.rest.webservices.restfulwebservices.model.Post;
import com.subhadeep.rest.webservices.restfulwebservices.model.User;
import com.subhadeep.rest.webservices.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDaoService {

    private PostRepository postRepository;

    @Autowired
    public PostDaoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(User user, Post post) {
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post with id " + id + " doesn't exist!"));
    }
}
