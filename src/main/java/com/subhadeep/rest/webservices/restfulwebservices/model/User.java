package com.subhadeep.rest.webservices.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//@ApiModel and @ApiModelProperty are used for Swagger Documentation
// These two annotations help us to add more sense to the model description
// given in api documentation under "definition" section of Api Documentation

@ApiModel(description = "Details about User model")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(notes = "Minimum name length is 2 characters!")
    @Size(min = 2, message = "Minimum name length is 2 characters!")
    @NotNull(message="Please provide a Name")
    private String name;

    @ApiModelProperty(notes = "Date of Birth can only be in Past")
    @Past
    @NotNull(message="Please provide a date.")
    private Date dob;

    // we have to tell the mapped attribute
    // this tells which field is owning the relationship
    @OneToMany(mappedBy = "user")
    private List<Post> posts; // whenever we will fetch a user
//    the posts will come by default

    public User(Integer id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
