package com.subhadeep.rest.webservices.restfulwebservices.controller;

import com.subhadeep.rest.webservices.restfulwebservices.model.Post;
import com.subhadeep.rest.webservices.restfulwebservices.model.User;
import com.subhadeep.rest.webservices.restfulwebservices.service.PostDaoService;
import com.subhadeep.rest.webservices.restfulwebservices.service.UserDaoServiceSqlDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jpa/users")
public class UserControllerSqlDB {

    private UserDaoServiceSqlDB userDaoServiceSqlDB;
    private PostDaoService postDaoService;

    @Autowired
    public UserControllerSqlDB(UserDaoServiceSqlDB userDaoServiceSqlDB, PostDaoService postDaoService) {
        this.userDaoServiceSqlDB = userDaoServiceSqlDB;
        this.postDaoService = postDaoService;
    }

    @GetMapping("/")
    public List<Resource> findAll() {
        List<User> retrievedUsers = userDaoServiceSqlDB.findAll();
        List<Resource> hateOasUsers = new ArrayList<>();
        for(User user : retrievedUsers) {
            Resource<User> resource = new Resource<>(user);
            ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
                    .methodOn(this.getClass())
                    .retrieveFirstUser(user.getId()));
            resource.add(linkBuilder.withRel("user-endpoint"));
            hateOasUsers.add(resource);
        }
        return hateOasUsers;
    }

    @GetMapping("/{id}")
    public Resource retrieveFirstUser(@PathVariable("id") Integer id) {
        User user = userDaoServiceSqlDB.findOne(id);
        Resource<User> resource= new Resource<>(user);
        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
                .methodOn(this.getClass()).findAll());
        resource.add(linkBuilder.withRel("All Users"));
        return resource;
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userDaoServiceSqlDB.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.setLocation(location);
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User user) {
        User updatedUser = userDaoServiceSqlDB.updateUser(id, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().buildAndExpand(updatedUser.getId()).toUri();
        HttpHeaders  httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);
        return new ResponseEntity<>(updatedUser, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Integer id) {
        return userDaoServiceSqlDB.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable("id") Integer id) {
        User user = userDaoServiceSqlDB.findOne(id);
        return user.getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> addUserPost(@PathVariable("id") Integer custId, @RequestBody Post post) {
        User user = userDaoServiceSqlDB.findOne(custId);
        Post savedPost = postDaoService.save(user, post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{postId}")
                .buildAndExpand(savedPost.getId()).toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);

        return new ResponseEntity<>(savedPost, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/posts/{postId}")
    public Post retrieveUserPost(@PathVariable("id") Integer userId,
                                 @PathVariable("postId")Integer postId) {
//        This call is made to check if an user exists and then and only then post will be looked for
        userDaoServiceSqlDB.findOne(userId);
        Post post = postDaoService.findById(postId);
        return post;
    }
}
