package com.webservices.restfulwebservices.controller;

import com.webservices.restfulwebservices.model.Name;
import com.webservices.restfulwebservices.model.PersonV1;
import com.webservices.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningUserController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersion(){
        return new PersonV1("Shahed Shariati");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersion(){
        return new PersonV2(new Name("Samrand","Shariati"));
    }

    //127.0.0.1:8080/person?version=1
    @GetMapping(value = "/person",params = "version=1")
    public PersonV1 getFirstVersionRequestParameter(){
        return new PersonV1("Shahed Shariati");
    }

    //127.0.0.1:8080/person?version=2
    @GetMapping(value = "/person",params = "version=2")
    public PersonV2 getSecondVersionRequestParameter(){
        return new PersonV2(new Name("Samrand","Shariati"));
    }


    // Set header X-API-VERSION=1
    @GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionRequestheaders(){
        return new PersonV1("Shahed Shariati");
    }

    // Set header X-API-VERSION=2
    @GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionRequestHeader(){
        return new PersonV2(new Name("Samrand","Shariati"));
    }

    @GetMapping(value = "/person/accept",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionAccept(){
        return new PersonV1("Shahed Shariati");
    }

    @GetMapping(value = "/person/accept",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionAccept(){
        return new PersonV2(new Name("Samrand","Shariati"));
    }




}
