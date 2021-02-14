package com.yinziming.part05.spring5.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUserById(int id);

    Flux<User> getAll();

    Mono<Void> saveUserInfo(Mono<User> userMono);
}
