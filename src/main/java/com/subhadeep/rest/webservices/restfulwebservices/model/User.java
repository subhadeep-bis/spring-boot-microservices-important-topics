package com.subhadeep.rest.webservices.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

//@ApiModel and @ApiModelProperty are used for Swagger Documentation
// These two annotations help us to add more sense to the model description
// given in api documentation under "definition" section of Api Documentation

@ApiModel(description = "Details about User model")
public class User {
    private Integer id;

    @ApiModelProperty(notes = "Minimum name length is 2 characters!")
    @Size(min = 2, message = "Minimum name length is 2 characters!")
    private String name;

    @ApiModelProperty(notes = "Date of Birth can only be in Past")
    @Past
    private Date dob;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
