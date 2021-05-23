package com.fastcampus.java.controller;

import com.fastcampus.java.model.SearchParam;
import com.fastcampus.java.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    // localhost:8080/api/getMethod
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest(){

        return "Hi getMethod";
    }

    // localhost:8080/api/getParameter?id=1234&password=abcd
    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbbb";
        System.out.println("id : "+id);
        System.out.println("pwd : "+pwd);

        return id+pwd;
    }

    // localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
    // parameter가 늘어날 수록 대응력이 좋지 않으므로 객체로 parameter를 request
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" : "", "page" : 0}
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {
        // {"resultCode : "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}