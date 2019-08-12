package com.subhadeep.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Generally when this exception will occur, the response that you will receive
// will have a status code 500 showing that there was a problem, however, 5xx status
// codes generally means that the problem was an InternalServerError which is
// not the actual case. If we look properly it was a mistake of a user making a request
// for a resource that is not present i.e a wrong id was provided and this will generally
// result in the exception that we are trying to work upon.
// So, what we need the RESTController to return is the status code of 4xx, more
// precisely 404 NOT_FOUND
// In order to do so we will use an Annotation called @ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
