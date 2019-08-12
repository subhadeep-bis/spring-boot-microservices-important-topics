package com.subhadeep.rest.webservices.restfulwebservices.model;

import java.util.Date;

public class ExceptionResponse {
   private Date timeStampDate;
   private String message;
   private String details;

    public ExceptionResponse(Date timeStampDate, String message, String details) {
        this.timeStampDate = timeStampDate;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStampDate() {
        return timeStampDate;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
