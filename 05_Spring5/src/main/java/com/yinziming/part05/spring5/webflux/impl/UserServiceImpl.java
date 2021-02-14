package com.yinziming.part05.spring5.webflux.impl;

import com.yinziming.part05.spring5.webflux.User;
import com.yinziming.part05.spring5.webflux.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        users.put(1, new User("name1", "male", 20));
        users.put(2, new User("name2", "female", 30));
        users.put(3, new User("name3", "female", 40));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(users.get(id));
    }

    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
