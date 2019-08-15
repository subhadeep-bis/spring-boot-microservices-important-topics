package com.subhadeep.rest.webservices.restfulwebservices.controller;

import com.subhadeep.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.subhadeep.rest.webservices.restfulwebservices.model.User;
import com.subhadeep.rest.webservices.restfulwebservices.service.UserDaoServiceNoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserControllerNoDB {

    private UserDaoServiceNoDB userDaoServiceNoDB;

    @Autowired
    public UserControllerNoDB(UserDaoServiceNoDB userDaoServiceNoDB) {
        this.userDaoServiceNoDB = userDaoServiceNoDB;
    }

    @GetMapping("/users")
    public List<Resource> retrieveAllUsers() {
        List<User> users = userDaoServiceNoDB.findAll();
        List<Resource> hateoasUsers = new ArrayList<>();
        // HATEOAS
        for(User user : users) {
            Resource<User> resource = new Resource<User>(user);
            ControllerLinkBuilder linkTo = ControllerLinkBuilder
                    .linkTo(ControllerLinkBuilder
                            .methodOn(this.getClass())
                            .retrieveFirstUser(user.getId()));
            resource.add(linkTo.withRel("first-user"));
            hateoasUsers.add(resource);
        }
        return hateoasUsers;
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveFirstUser(@PathVariable Integer id) {
        User retrievedUser = userDaoServiceNoDB.findOne(id);
        if(retrievedUser == null) {
            // open the UserNotFoundException class to know more. Important, do open
            throw new UserNotFoundException("User not found with id -> " + id);
        }

        // HATEOAS
        Resource<User> resource = new Resource<User>(retrievedUser);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(this.getClass())
                        .retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

//    In 28 minutes example and the best practice
    @RequestMapping(path="/users", method= RequestMethod.POST)
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = userDaoServiceNoDB.save(user);

        // ** We want two things here
        //      1. Instead of sending the status OK, send CREATED because we are creating a resource
        //      2. Set the URI of created Resource in the response. So, if the resource being created has an Id 4, then it can be
        //          fetched using the uri /users/4 and that is what we want to show in the response.
        // /users/{id} savedUser.getId
        // The way I can get the current request URI is using ServletUriComponentsBuilder.fromCurrentRequest()"
        //  The path method will allow us to append the userId.
        // buildAndExpand helps in replacing the value of the variable {id}. Below the id will be replaced with 4.
        // ending it with toUri() that ultimately compiles the total process to URI.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build(); //Create a new builder with a CREATED status and a location header set to the given URI. i.e. the uri is set in the headers of the response
    }


// My implementation. This was done because I had previously kind of done like this and this would help me
/*    @RequestMapping(path="/users", method= RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);
        return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
    }
*/

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        User user = userDaoServiceNoDB.deleteById(id);

        if(user == null)
            throw new UserNotFoundException("User not found with id -> " + id);
    }
}
