package com.example.petstore;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "hello", description = "hello")
public class HelloController {

    @GetMapping("/")
    @Operation(description = "index")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @Operation(description = "hello world")
    public String hello() {
        return "hello world";
    }
}
