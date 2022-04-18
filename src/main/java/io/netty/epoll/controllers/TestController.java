package io.netty.epoll.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

@Controller("/test")
public class TestController {

    @Get("/v1")
    public Mono<String> helloWorld() {
        return Mono.just("hello world");
    }
}
