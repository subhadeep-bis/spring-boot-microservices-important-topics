package com.subhadeep.rest.webservices.restfulwebservices.controller;

import com.subhadeep.rest.webservices.restfulwebservices.model.Name;
import com.subhadeep.rest.webservices.restfulwebservices.model.PersonV1;
import com.subhadeep.rest.webservices.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

// *** URI Versioning. You're mentioning v1 and v2 in the URI itself
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2( new Name("Subhadeep", "Biswas"));
    }


//  *** Versioning using Request Parameter. You mention the parameters using "params" keyword
/*      @RequestParam is used to get the Request Parameters from URL, also known as
        Query parameters (i.e. the string aget the "?"), whereas @PathVariable extract
        values from URI.
        So, if the Incoming request is in the form
        http://localhost:8080/shop/order/{1001}/receipts?date=12-05-2017
        URI is the part before question mark(?) and URL is the part after
        you can get the order_id using @PathVariable and date using @RequestParam
 */
    @GetMapping(value="person/param", params = "version=1")
    public PersonV1 paramv1(@RequestParam(value = "version") Integer version) {
        System.out.println(version);
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/param", params = "version=2")
    public PersonV2 paramV2(@RequestParam(value="version") Integer version) {
        System.out.println(version);
        return new PersonV2( new Name("Subhadeep", "Biswas"));
    }


//  *** Header Versioning
//    Mention X-API_VERSION in headers of your request
    @GetMapping(value="person/header", headers = "X-API-VERSION=1") //X-API-VERSION IS JUST A RANDOM NAME
    public PersonV1 headerv1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2( new Name("Subhadeep", "Biswas"));
    }

//  *** Producer's Versioning/ Content-Negotiation Versioning/ Accept Versioning
/*      This is again a header parameter, however, the way this is ent in request is a little
        different
        ************************
        In order to send this as a request, you have to use the "accept" parameter
        to mention the type of data you are expecting from the service. If you
        remember we did the same thing during content negotiation and hence the
        name content-negotiation-versioning.
        So, this time instead of sending "accept" : "application/json" as key-value pairs
        we will send "accept": "application/vnd.company.app-{v1/v2}+json" in header
        ************************
        The "produces" attribute is saying what is the output of this specific service.
        Until now we were generating application/json, but now we are not generating
        application/json, but also appending v1/v2 to it accordingly
*/
    @GetMapping(value="person/produces", produces = "application/vnd.company.app-v1+xml")
        public PersonV1 producesv1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesv2() {
        return new PersonV2( new Name("Subhadeep", "Biswas"));
    }
}
